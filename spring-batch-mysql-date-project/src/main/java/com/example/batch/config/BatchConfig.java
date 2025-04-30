package com.example.batch.config;



import com.example.batch.model.User;
import com.example.batch.processor.UserItemProcessor;
import com.example.batch.writer.ConsoleItemWriter;
import com.example.batch.writer.UserItemWriter;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public FlatFileItemReader<User> reader() {
        FlatFileItemReader<User> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("users.csv"));
        //reader.setLinesToSkip(1); // 🔍 Skip header line

        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("date", "month", "year");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(User.class);
            }});
        }});

        return reader;
    }





    @Bean
    public UserItemProcessor processor() {
        return new UserItemProcessor();
    }

    @Bean
    public ConsoleItemWriter consoleWriter() {
        return new ConsoleItemWriter();
    }

    @Bean
    public UserItemWriter dbWriter() {
        return new UserItemWriter();
    }

    @Bean
    public Job importUserJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        Step step1 = new StepBuilder("step1", jobRepository)
                .<User, User>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(dbWriter())
                .listener(consoleWriter())
                .build();

        return new JobBuilder("importUserJob", jobRepository)
                .start(step1)
                .listener(new JobCompletionNotificationListener())
                .build();
    }

    static class JobCompletionNotificationListener extends JobExecutionListenerSupport {
        @Override
        public void afterJob(JobExecution jobExecution) {
            if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                System.out.println("Job completed successfully!");
            } else {
                System.out.println("Job failed.");
            }
        }
    }
}
