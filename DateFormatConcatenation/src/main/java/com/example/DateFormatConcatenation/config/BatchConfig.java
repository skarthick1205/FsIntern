package com.example.DateFormatConcatenation.config;
import com.example.DateFormatConcatenation.model.LoanDetails;
import com.example.DateFormatConcatenation.processor.LoanItemProcessor;
import com.example.DateFormatConcatenation.writer.ConsoleItemWriter;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public FlatFileItemReader<LoanDetails> reader() {
        FlatFileItemReader<LoanDetails> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("loan_data.csv"));
        reader.setLinesToSkip(1); // header

        DefaultLineMapper<LoanDetails> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("loanId", "principal", "interestRate", "durationMonths");

        BeanWrapperFieldSetMapper<LoanDetails> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(LoanDetails.class);

        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        reader.setLineMapper(lineMapper);

        return reader;
    }

    @Bean
    public LoanItemProcessor processor() {
        return new LoanItemProcessor();
    }

    @Bean
    public ConsoleItemWriter writer() {
        return new ConsoleItemWriter();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .<LoanDetails, List<com.example.DateFormatConcatenation.model.LoanEmiSchedule>>chunk(1, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("loanEmiJob", jobRepository)
                .start(step)
                .build();
    }
}
