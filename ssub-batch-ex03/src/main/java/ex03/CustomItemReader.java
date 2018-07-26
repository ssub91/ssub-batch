package ex03;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class CustomItemReader implements ItemReader<Person>, ItemStream  {
    private static final String CURRENT_INDEX = "current.index";	
	private List<Person> items;
    private int currentIndex = 0;

    public CustomItemReader() {
    	initialize();
    }
    
	@Override
	public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (currentIndex < items.size()) {
            return items.get( currentIndex++ );
        }
        
		return null;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
            currentIndex = executionContext.containsKey( CURRENT_INDEX ) ? new Long( executionContext.getLong( CURRENT_INDEX ) ).intValue() : 0;
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		executionContext.putLong( CURRENT_INDEX, new Long( currentIndex ).longValue() );
	}

	@Override
	public void close() throws ItemStreamException {
	}
	
    
    private void initialize() {
		// 테스트 데이터
		items = new ArrayList<Person>();
		items.add( new Person( "Jill", "Doe" ) );
		items.add( new Person( "Joe", "Doe" ) );
		items.add( new Person( "Justin", "Doe" ) );    	
    }	
}