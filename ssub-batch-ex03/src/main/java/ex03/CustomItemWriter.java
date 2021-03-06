package ex03;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public class CustomItemWriter<T> implements ItemWriter<T> {

	private static final Logger log = LoggerFactory.getLogger( CustomItemWriter.class );

	@Override
	public void write(List<? extends T> items) throws Exception {
		log.info( "CustomItemWriter.write called[items size:" + items.size() + "]" );
//		for( T item : items ) {
//			log.info( "CustomItemWriter.write (" + item + ")" );
//		}
	}

}