package net.sinou.sandbox.springbatch.minimal.item;

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

import net.sinou.sandbox.springbatch.minimal.domain.Person;

public class MyLineMapper extends DefaultLineMapper<Person> {
	// Initialize the line tokenizer and fieldSetMapper
	// before the after property set method is called.
	{
		setLineTokenizer(new DelimitedLineTokenizer() {
			{
				setNames(new String[] { "firstName", "lastName" });
			}
		});
		setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
			{
				setTargetType(Person.class);
			}
		});
	}
}
