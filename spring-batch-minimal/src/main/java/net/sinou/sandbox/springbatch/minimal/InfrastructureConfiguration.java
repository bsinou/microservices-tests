package net.sinou.sandbox.springbatch.minimal;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

/**
 * Best practice to provide an easy to configure context based on this &lt;a
 * href="https://blog.codecentric.de/en/2013/06/spring-batch-2-2-javaconfig-part-1-a-comparison-to-xml"&gt;
 * blog post &lt;/a&gt;
 * 
 * Concrete beans should be provided by implementing classes
 */
public interface InfrastructureConfiguration {

	@Bean
	public abstract DataSource dataSource();

}