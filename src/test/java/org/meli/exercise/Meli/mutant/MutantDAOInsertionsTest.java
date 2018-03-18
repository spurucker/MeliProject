package org.meli.exercise.Meli.mutant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.meli.exercise.Meli.transformers.StringArrayTransformer.TRANSFORMER;

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
public class MutantDAOInsertionsTest {

    @InjectMocks
    private MutantDAO dao;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("test_schema.sql")
                .build();
        jdbcTemplate= new JdbcTemplate(db);
        ReflectionTestUtils.setField(dao, "jdbcTemplate", jdbcTemplate);
    }

    @Test
    public void addNewMutantDna(){
        String[] dna= new String[]{"AAAA","AAAA","AAAA","AAAA"};
        dao.addDna(dna, true);
        /** Inserted twice to validate it does not insert it twice on the DB */
        dao.addDna(dna, true);
        assertMutantInDB(dna, true);
    }

    @Test
    public void addNewNoMutantDna(){
        String[] dna= new String[]{"AAAG","AGAA","TAAA","AAGA"};
        dao.addDna(dna, false);
        assertMutantInDB(dna, false);
    }

    private void assertMutantInDB(String[] dna, boolean isMutant){
        String dnaSaved= jdbcTemplate.queryForObject("select dna from dna where is_mutant=" + isMutant, String.class);
        assertThat(dnaSaved, is(TRANSFORMER.stringFromStringArray(dna)));
    }
}
