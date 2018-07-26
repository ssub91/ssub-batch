package ex04;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Ex04BatchJobRunner {

	public static void main(String[] args) {
		try {
	
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringBatchConfig.class);
	
			JobLauncher jobLauncher = context.getBean(JobLauncher.class);
			Job job1 = context.getBean("job1", Job.class);
	
			JobParameters jobParameters = new JobParametersBuilder().
				toJobParameters();
	
			jobLauncher.run(job1, jobParameters);

			context.close();
		} catch(JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
}
