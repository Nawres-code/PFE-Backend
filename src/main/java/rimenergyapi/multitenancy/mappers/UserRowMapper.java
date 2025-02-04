package rimenergyapi.multitenancy.mappers;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import rimenergyapi.dto.accounts.UserInfoDto;


public class UserRowMapper implements RowMapper<UserInfoDto>
{
    @Override
    public UserInfoDto mapRow(ResultSet rs, int rowNum) throws SQLException
    {
    	UserInfoDto user = new UserInfoDto();
        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setDisplayName(rs.getString("display_name"));
        return user;
    }
}