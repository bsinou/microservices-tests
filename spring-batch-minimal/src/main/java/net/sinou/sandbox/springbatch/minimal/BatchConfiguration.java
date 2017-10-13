package net.sinou.sandbox.springbatch.minimal;

import org.junit.Assert;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import net.sinou.sandbox.springbatch.minimal.domain.Person;
import net.sinou.sandbox.springbatch.minimal.item.MyLineMapper;
import net.sinou.sandbox.springbatch.minimal.item.PersonItemProcessor;
import net.sinou.sandbox.springbatch.minimal.listener.BasicListener;

@Configuration
@Import({ AdvancedInfrastructureConfiguration.class, SimpleInfrastructureConfiguration.class })
public class BatchConfiguration {
	// private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String resourcePath = "net/sinou/sandbox/springbatch/minimal/sample-data.csv";

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public InfrastructureConfiguration infrastructureConfiguration;

	@Bean
	public FlatFileItemReader<Person> reader() {
		Resource resource = new ClassPathResource(resourcePath);
		Assert.assertTrue("Resource not found at " + resourcePath, resource.exists());

		FlatFileItemReader<Person> reader = new FlatFileItemReader<Person>();
		reader.setLineMapper(new MyLineMapper());
		reader.setResource(resource);
		return reader;
	}

	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<Person> writer() {
		JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<Person>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
		writer.setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
		writer.setDataSource(infrastructureConfiguration.dataSource());
		return writer;
	}

	@Bean
	public Job importUserJob(JobExecutionListener listener) {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Person, Person>chunk(10).reader(reader()).processor(processor())
				.writer(writer()).build();
	}

	@Bean
	public BasicListener basicListener() {
		return new BasicListener();
	}

	// @Bean
	// public PropertySourcesPlaceholderConfigurer properties() {
	// PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer =
	// new PropertySourcesPlaceholderConfigurer();
	// YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
	// yaml.setResources(new ClassPathResource("application-default.yml"));
	// propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
	//
	// logger.info("===== Place holder configured");
	// return propertySourcesPlaceholderConfigurer;
	// }
}
