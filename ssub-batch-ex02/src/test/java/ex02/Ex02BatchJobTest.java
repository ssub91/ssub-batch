package ex02;

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

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes= { SpringBatchConfig.class, SpringBatchTestConfig.class } )
public class Ex02BatchJobTest {

	@Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;	
	
	@Autowired
	@Qualifier("job1")
	private Job job1;
	
	@Test
	public void testJob1Run() throws Exception {
		jobLauncherTestUtils.setJob(job1);
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();
		
		assertEquals( ExitStatus.COMPLETED, jobExecution.getExitStatus() );
	}

}
