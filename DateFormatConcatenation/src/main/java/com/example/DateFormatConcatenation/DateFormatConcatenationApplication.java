package com.example.DateFormatConcatenation;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
class DateFormatConcatenationApplication implements CommandLineRunner {

	@Autowired
	private JobLauncher jobLauncher;
	@Autowired private Job job;

	public static void main(String[] args) {
		SpringApplication.run(DateFormatConcatenationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JobParameters parameters = new JobParametersBuilder()
				.addLong("startAt", System.currentTimeMillis())
				.toJobParameters();

		JobExecution execution = jobLauncher.run(job, parameters);
		System.out.println("STATUS :: " + execution.getStatus());
	}
}

