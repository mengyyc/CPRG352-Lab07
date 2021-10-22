package services;

import dataaccess.RoleDB;
import java.sql.SQLException;
import java.util.List;
import models.Role;

public class RoleService {
	
	public List<Role> getAll() throws SQLException {
		RoleDB roleDB = new RoleDB();
		List<Role> roles = roleDB.getAll();
		return roles;
	}	

	public Role get(int roleId) throws SQLException {
		RoleDB roleDB = new RoleDB();
		Role role = roleDB.get(roleId) ;
		return role;
	}

	public Role get(String roleName) throws SQLException {
		RoleDB roleDB = new RoleDB();
		Role role = roleDB.get(roleName);
		return role;
	}
}
