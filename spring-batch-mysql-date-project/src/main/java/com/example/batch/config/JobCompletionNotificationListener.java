package com.example.batch.config;
import com.example.batch.repository.UserRepository;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;
@Component
public class JobCompletionNotificationListener implements JobExecutionListener {
    private final UserRepository repository;
    public JobCompletionNotificationListener(UserRepository repository) {
        this.repository = repository;
    }
    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("==== JOB FINISHED ====");
            repository.findAll().forEach(System.out::println);
        }
    }
}