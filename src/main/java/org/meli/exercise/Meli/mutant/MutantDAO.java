package org.meli.exercise.Meli.mutant;

import static org.meli.exercise.Meli.transformers.StringArrayTransformer.TRANSFORMER;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class MutantDAO {
    private static final String INSERT_MUTANT= "insert into dna (is_mutant, dna) " +
            "select * from (select cast(? as boolean), cast(? as varchar(255)) AS tmp " +
            "where not exists ( " +
            "    select dna from dna where dna = cast(? as varchar(255) " +
            "))) limit 1;";

    private static final String STAT_QUERY=  "SELECT " +
            "(select COUNT(1) from dna where is_mutant=false GROUP BY is_mutant) as not_mutants," +
            "(select COUNT(1) from dna where is_mutant=true GROUP BY is_mutant) as mutants";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addDna(String[] dna, boolean isMutant){
        jdbcTemplate.update(INSERT_MUTANT,
                new Object[] {isMutant, TRANSFORMER.stringFromStringArray(dna), TRANSFORMER.stringFromStringArray(dna)});
    }

    public Stat getStats() {
        return jdbcTemplate.queryForObject(STAT_QUERY, new StatRowMapper());
    }

    private class StatRowMapper implements RowMapper<Stat> {
        @Override
        public Stat mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Stat(rs.getInt("not_mutants"), rs.getInt("mutants"));
        }
    }
}
