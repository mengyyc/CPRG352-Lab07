package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Role;

public class RoleDB {
	
	public List<Role> getAll() throws SQLException {
		List<Role> roles = new ArrayList<>();
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection conn = cp.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM role";

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				int roleId = rs.getInt(1);
				String roleName = rs.getString(2);
				Role role = new Role(roleId, roleName);
				roles.add(role);
			}
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			cp.freeConnection(conn);
		}

		return roles;
	}	

	public Role get(int roleId) throws SQLException {
		Role role = null;
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection conn = cp.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM role WHERE role_id = ?";

		try {
			ps = conn.prepareStatement(query);
			ps.setInt(1, roleId);
			rs = ps.executeQuery();
			if (rs.next()) {
				String roleName = rs.getString(2);
				role = new Role(roleId, roleName);
			}
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			cp.freeConnection(conn);
		}
		return role;
	}

	public Role get(String roleName) throws SQLException {
		Role role = null;
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection conn = cp.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM role WHERE role_name = ?";

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, roleName);
			rs = ps.executeQuery();
			if (rs.next()) {
				int roleId = rs.getInt(1);
				role = new Role(roleId, roleName);
			}
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			cp.freeConnection(conn);
		}
		return role;
	}
}
