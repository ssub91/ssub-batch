package ex06.stepfail;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public class CustomItemWriterFail<T> implements ItemWriter<T> {

	private static final Logger log = LoggerFactory.getLogger( CustomItemWriterFail.class );

	@Override
	public void write(List<? extends T> items) throws Exception {
		log.info( "CustomItemWriterFail.write called[items size:" + items.size() + "]" );
	}
}