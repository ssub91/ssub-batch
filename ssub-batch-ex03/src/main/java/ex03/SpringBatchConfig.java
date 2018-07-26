package ex03;



import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
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
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

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

	@Bean
    public CustomItemReader reader() {
		CustomItemReader reader = new CustomItemReader();
    	return reader;
    }

	@Bean
    public CustomItemWriter<Person> writer() {
		CustomItemWriter<Person> writer = new CustomItemWriter<Person>();
    	return writer;
    }
	
    @Bean( "job1" )
    public Job importUserJob( Step step ) {
    	return jobBuilders.
    			get( "importUserJob" ).
    			start( step ).
    			build();
    }

    @Bean
    public Step step1( ItemReader<Person> reader, ItemWriter<Person> writer ) {
        return stepBuilders.get( "step1" )
                .<Person, Person> chunk( 2 )
                .reader( reader )
                .writer( writer )
                .build();
    }    
}