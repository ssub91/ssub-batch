package ex03;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
/**
 * 
 * @author sblee
 * 
 * 
 * 이 테스트는 커스텀 ItemReader의 재실행에 관한 상태 정보 저장에 관한 테스트입니다.
 * 
 * 
 */
public class CustomItemReaderTest {
	
	@Test
	public void testReastart() throws Exception {

		// 스텝 실행(StepExecution) 환경
		ExecutionContext executionContext = new ExecutionContext();

		// 리더 하나 만들고
		ItemReader<Person> itemReader = new CustomItemReader();
		
		// reader 실행
		((ItemStream)itemReader).open(executionContext);
		assertEquals( "Jill", itemReader.read().getFirstName() );
		
		// reader 상태를 스텝실행환경(ExecutionContext)에 저장 
		((ItemStream)itemReader).update(executionContext);
		itemReader = new CustomItemReader();

		((ItemStream)itemReader).open(executionContext);
		assertEquals("Joe", itemReader.read().getFirstName());		
	}
}