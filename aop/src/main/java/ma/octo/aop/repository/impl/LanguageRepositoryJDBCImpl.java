package ma.octo.aop.repository.impl;


import ma.octo.aop.entity.Language;
import ma.octo.aop.repository.LanguageRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Component
@Primary
public class LanguageRepositoryJDBCImpl implements LanguageRepository {

    private JdbcTemplate jdbcTemplate;
    private final String SQL_FIND_ALL_LANGUAGE= "select * from language";
    private final String SQL_FIND_LANGUAGE_BY_ID= "select * from language where id=?";
    private final String SQL_FIND_LANGUAGE_BY_EXTENSION= "select * from language where extension=?";


    public LanguageRepositoryJDBCImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    RowMapper<Language> rowMapper = (rs, rowNum) -> {
        return new Language(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("author"),
                rs.getString("fileExtension")
        );
    };

    @Override
    public Optional<Language> findByExtension(String extension) {
        String sql = "SELECT * FROM language WHERE fileExtension = ?";
        Language language = null;
        try {
            language = jdbcTemplate.queryForObject(sql, new Object[]{extension}, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(language);
    }

    @Override
    public Optional<Language> findById(String id) {
        String sql = "SELECT * FROM language WHERE id = ?";
        Language language = null;
        try {
            language = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
        return Optional.ofNullable(language);
    }

    @Override
    public List<Language> findAll() {
        String sql = "SELECT * FROM language";
        return jdbcTemplate.query(sql, rowMapper);
    }


}