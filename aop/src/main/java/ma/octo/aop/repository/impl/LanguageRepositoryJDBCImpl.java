package ma.octo.aop.repository.impl;


import ma.octo.aop.entity.Language;
import ma.octo.aop.entity.LanguageMapper;
import ma.octo.aop.repository.LanguageRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
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


    public LanguageRepositoryJDBCImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public Optional<Language> findByExtension(String extension) {

           Language language = (Language) jdbcTemplate.queryForObject(SQL_FIND_LANGUAGE_BY_EXTENSION, new Object[]{extension}, new LanguageMapper());

        return Optional.of(language);
    }

    @Override
    public Optional<Language> findById(String id) {
        Language language = (Language) jdbcTemplate.queryForObject(SQL_FIND_LANGUAGE_BY_ID, new Object[]{id}, new LanguageMapper());
        return Optional.of(language);
    }

    @Override
    public List<Language> findAll() {
        String sql = "SELECT * FROM language";
        return jdbcTemplate.query(sql, new LanguageMapper());
    }


}