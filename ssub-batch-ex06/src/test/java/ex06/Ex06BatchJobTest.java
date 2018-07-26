package ex06;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ex06.config.SpringBatchConfig;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes= { SpringBatchConfig.class, SpringBatchTestConfig.class } )
public class Ex06BatchJobTest {
	
	@Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;	
	
	@Autowired
	@Qualifier( "dispatchJob" )
	private Job dispatchJob;
	
	@Test
	public void testJob1Run() throws Exception {
		jobLauncherTestUtils.setJob( dispatchJob );
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		assertEquals( ExitStatus.COMPLETED, jobExecution.getExitStatus() );
	}

}
