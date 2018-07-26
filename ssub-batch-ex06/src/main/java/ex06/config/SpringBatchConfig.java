package ex06.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.configuration.support.MapJobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import ex06.dto.Person;
import ex06.step1.ConditionalTasklet1;
import ex06.step1.CustomItemReader1;
import ex06.step1.CustomItemWriter1;
import ex06.step1.StepExecutionListener1;
import ex06.step2.CustomItemReader2;
import ex06.step2.CustomItemWriter2;
import ex06.stepfail.CustomItemReaderFail;
import ex06.stepfail.CustomItemWriterFail;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilders;

    @Autowired
    private StepBuilderFactory stepBuilders;
    
	@Bean
	public DataSource dataSource() {
	    DataSource dataSource = new DataSource();
	    
	    dataSource.setDriverClassName( "oracle.jdbc.driver.OracleDriver" );
	    dataSource.setUrl( "jdbc:oracle:thin:@localhost:1521:xe" );
	    dataSource.setUsername( "hone4" );
	    dataSource.setPassword( "hone4" );
	    
	    return dataSource;
	}

	@Bean
	public PlatformTransactionManager platformTransactionManager( DataSource dataSource ) {
		return new DataSourceTransactionManager( dataSource );
	}

	@Bean
	public JobRepository jobRepository( PlatformTransactionManager platformTransactionManager ) throws Exception {
		return new MapJobRepositoryFactoryBean( platformTransactionManager ).getObject();
	}

    @Bean
    public JobLauncher jobLauncher( JobRepository jobRepository ) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository( jobRepository );
        return jobLauncher;
    }
	
    @Bean
    public JobRegistry jobRegistry() throws Exception {
    	return new MapJobRegistry();
    }

    @Bean
    public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor( JobRegistry jobRegistry ) throws Exception {
    	JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
    	jobRegistryBeanPostProcessor.setJobRegistry( jobRegistry );
        return jobRegistryBeanPostProcessor;
    }
    
    @Bean( "dispatchJob" )
    public Job importUserJob( @Qualifier( "step1" ) Step step1, @Qualifier( "step2" ) Step step2, @Qualifier( "stepFail" ) Step stepFail ) {
    	return jobBuilders.
    			get( "dispatchJob" ).
    			start( step1 ).
    				on( "FAILED" ).to( stepFail ).
    			from( step1 ).
    				next( step2 ).
    			end().
    			build();
    }

    
    /** stepFail **/
    
    @Bean( "itemReaderFail" )
    public ItemReader<Person> readerFail() {
    	return new CustomItemReaderFail();
    }    

	@Bean( "itemWriterFail" )
    public ItemWriter<Person> writerFail() {
    	return new CustomItemWriterFail<Person>();
    }
	
    @Bean( "stepFail" )
    public Step stepFail( 
    	@Qualifier( "itemReaderFail" ) ItemReader<Person> reader, 
    	@Qualifier( "itemWriterFail" ) ItemWriter<Person> writer) {
    	
    	return stepBuilders.get( "stepFail" ).
    			<Person, Person> chunk( 1 ).
    			reader( reader ).
    			writer( writer ).
    			build();
    }     
    
    
    /** step1 **/
    
    @Bean( "itemReader1" )
    public ItemReader<Person> reader1() {
    	return new CustomItemReader1();
    }    

	@Bean( "itemWriter1" )
    public ItemWriter<Person> writer1() {
    	return new CustomItemWriter1<Person>();
    }

    @Bean( "stepExecutionListener" )
    public StepExecutionListener stepExecutionListener() {
    	return new StepExecutionListener1();
    } 

    @Bean( "conditionalTasklet1" )
    public Tasklet conditionalTasklet1() {
    	return new ConditionalTasklet1();
    }
    
    @Bean( "step1" )
    public Step step1( 
    	@Qualifier( "itemReader1" )  ItemReader<Person> reader, 
    	@Qualifier( "itemWriter1" )ItemWriter<Person> writer,  
    	@Qualifier( "conditionalTasklet1" ) Tasklet tasklet,
    	StepExecutionListener stepExecutionListener ) {
    	
    	Step step = stepBuilders.get( "step1" ).
    			<Person, Person> chunk( 1 ).
    			reader( reader ).
    			writer( writer ).
    			listener( stepExecutionListener ).
    			build();

//    	return stepBuilders.
//    			get( "step1" ).
//    			tasklet( null ).
//    			build();
    	
    	return step;
    } 
    

    /** step2 **/
    
    @Bean( "itemReader2" )
    public ItemReader<Person> reader2() {
    	return new CustomItemReader2();
    }    

	@Bean( "itemWriter2" )
    public ItemWriter<Person> writer2() {
    	return new CustomItemWriter2<Person>();
    }
    
    @Bean( "step2" )
    public Step step2( 
    	@Qualifier( "itemReader2" ) ItemReader<Person> reader, 
    	@Qualifier( "itemWriter2" ) ItemWriter<Person> writer) {
    	
    	return stepBuilders.get( "step2" ).
    			<Person, Person> chunk( 1 ).
    			reader( reader ).
    			writer( writer ).
    			build();
    }        
}
