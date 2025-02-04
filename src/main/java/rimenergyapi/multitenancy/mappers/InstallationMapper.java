package rimenergyapi.multitenancy.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import rimenergyapi.dto.InstallationAllDto;


public class InstallationMapper implements RowMapper<InstallationAllDto> {
	@Override
	public InstallationAllDto mapRow(ResultSet rs, int rowNum) throws SQLException {
		InstallationAllDto installation = new InstallationAllDto();
		installation.setId(rs.getInt("id"));
		installation.setName(rs.getString("name"));
		return installation;
	}
}
