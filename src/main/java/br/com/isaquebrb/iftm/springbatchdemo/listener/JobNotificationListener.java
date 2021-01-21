package br.com.isaquebrb.iftm.springbatchdemo.listener;

import br.com.isaquebrb.iftm.springbatchdemo.model.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class JobNotificationListener extends JobExecutionListenerSupport {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("### Job finished! Time to verify results");

            jdbcTemplate.query("SELECT first_name, last_name FROM people",
                    (result, row) -> new Person(
                            result.getString(1),
                            result.getString(2)))
                    .forEach(person ->
                            log.info("Found <" + person + "> in the database."));
        }
    }
}
