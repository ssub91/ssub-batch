package ex06.step2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import ex06.dto.Person;

public class CustomItemReader2 implements ItemReader<Person> {
	private List<Person> items;
    private int currentIndex = 0;

    public CustomItemReader2() {
    	initialize();
    }
    
	@Override
	public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (currentIndex < items.size()) {
            return items.get( currentIndex++ );
        }
        
		return null;
	}
    
    private void initialize() {
		// 테스트 데이터
		items = new ArrayList<Person>();
		items.add( new Person( "Jill", "Doe" ) );
		items.add( new Person( "Joe", "Doe" ) );
		items.add( new Person( "Justin", "Doe" ) );    	
    }	
}