package ex03;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringBatchConfig.class)
/**
 * 
 * @author sblee
 * 
 * 이 테스트는 chunk 사이즈 즉 태스크릿의 commit-interval 값에 대한 테스트 입니다.
 *  
 */
public class Ex03BatchJobTest {
	
	@Autowired
	private SimpleJobLauncher jobLauncher;
	
	@Autowired
	@Qualifier( "job1" ) 
	private Job job1;
	
	@Test
	public void testJob1Run() throws Exception {
		JobExecution jobExecution = jobLauncher.run( job1, new JobParameters() );
		assertEquals( ExitStatus.COMPLETED, jobExecution.getExitStatus() );
	}
}