package ex06.step1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class StepExecutionListener1 implements StepExecutionListener  {
	
	private static final Logger log = LoggerFactory.getLogger( StepExecutionListener1.class );
	
	public ExitStatus afterStep( StepExecution stepExecution ) {
		
		log.info( "StepExecutionListener.afterStep called" );
		
		return ExitStatus.FAILED;
		//return ExitStatus.COMPLETED;
    }

	@Override
	public void beforeStep(StepExecution stepExecution) {
	}
}
