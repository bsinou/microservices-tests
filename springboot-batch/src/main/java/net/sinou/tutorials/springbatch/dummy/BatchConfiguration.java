package net.sinou.tutorials.springbatch.dummy;

import javax.sql.DataSource;

import org.junit.Assert;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import net.sinou.tutorials.springbatch.dummy.domain.Person;
import net.sinou.tutorials.springbatch.dummy.item.JobCompletionNotificationListener;
import net.sinou.tutorials.springbatch.dummy.item.MyLineMapper;
import net.sinou.tutorials.springbatch.dummy.item.PersonItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	// private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String resourcePath = "net/sinou/tutorials/springbatch/dummy/sample-data.csv";

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

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
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener) {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener)
				.flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<Person, Person>chunk(10).reader(reader()).processor(processor())
				.writer(writer()).build();
	}
}
