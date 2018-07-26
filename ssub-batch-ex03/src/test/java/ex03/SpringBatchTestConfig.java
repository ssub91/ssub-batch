package ex03;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class SpringBatchTestConfig {
    @Bean
    public JobLauncherTestUtils jobLauncherTestUtils( JobRepository jobRepository ) throws Exception {
        return new JobLauncherTestUtils();
    }
}
