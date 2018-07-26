package ex06.step1;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;


public class ConditionalTasklet1 implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
    	chunkContext.getStepContext().getStepExecution().setStatus( ( Math.random() > .5 ) ? BatchStatus.FAILED : BatchStatus.COMPLETED  );
//    	chunkContext.getStepContext().getStepExecution().setStatus( BatchStatus.FAILED );
    	
    	return RepeatStatus.FINISHED;
	}
}