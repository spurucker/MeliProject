package org.meli.exercise.Meli.mutant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class MutantDAOQueriesTest {

    @InjectMocks
    private MutantDAO dao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void statWithValuesInTheDB(){
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("test_schema.sql")
                .addScript("insert_test_values.sql")
                .build();
        jdbcTemplate= new JdbcTemplate(db);
        ReflectionTestUtils.setField(dao, "jdbcTemplate", jdbcTemplate);

        Stat stat= dao.getStats();

        assertThat(stat.getCountHuman(), is(3));
        assertThat(stat.getCountMutant(), is(2));
        assertThat(stat.getRatio(), is(0.4F));
    }

    @Test
    public void statWithoutValuesInTheDB(){
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("test_schema.sql")
                .build();
        jdbcTemplate= new JdbcTemplate(db);
        ReflectionTestUtils.setField(dao, "jdbcTemplate", jdbcTemplate);

        Stat stat= dao.getStats();

        assertThat(stat.getCountHuman(), is(0));
        assertThat(stat.getCountMutant(), is(0));
        assertThat(stat.getRatio(), is(0.00F));
    }
}
