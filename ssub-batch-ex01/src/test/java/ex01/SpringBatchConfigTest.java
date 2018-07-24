package ex01;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;

import ex01.SpringBatchConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringBatchConfig.class)
public class SpringBatchConfigTest {
	
	@Autowired
	private DataSource dataSource;

	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	
	@Autowired
	private JobRegistry jobRegistry;
	
	@Autowired
	private JobLauncher jobLauncher;	
	
	@Autowired
	private JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor;
	
	@Autowired
	JobRepository jobRepository;
	
	@Test
	public void dataSourceNotBeNull() {
		assertNotNull( dataSource );
	}

	@Test
	public void platformTransactionManagerNotBeNull() {
		assertNotNull( platformTransactionManager );
	}
	
	@Test
	public void jobRegistryNotBeNull() {
		assertNotNull( jobRegistry );
	}

	@Test
	public void jobLauncherNotBeNull() {
		assertNotNull( jobLauncher );
	}	
	
	@Test
	public void jobRegistryBeanPostProcessorNotBeNull() {
		assertNotNull( jobRegistryBeanPostProcessor );
	}	

	@Test
	public void jobRepositoryNotBeNull() {
		assertNotNull( jobRepository );
	}	
	
}
