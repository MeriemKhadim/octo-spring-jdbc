package ma.octo.aop.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
             return new Language(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("author"),
                    rs.getString("fileExtension")
            );
        };
    
}
