package ex02;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ex02.SpringBatchConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringBatchConfig.class)
public class SpringBatchConfigTest {
	
    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;
	
	@Test
	public void jobBuilders() {
		assertNotNull( jobBuilders );
	}

	@Test
	public void stepBuildersNotBeNull() {
		assertNotNull( stepBuilders );
	}
}
