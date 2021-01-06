package br.com.isaquebrb.iftm.springbatchdemo.processor;

import br.com.isaquebrb.iftm.springbatchdemo.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(final Person person) throws Exception {
        final String firstName = person.getFirstName();
        final String lastName = person.getLastName();

        final Person transformedPerson = new Person(firstName.toUpperCase(), lastName.toUpperCase());

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");
        return transformedPerson;
    }
}
