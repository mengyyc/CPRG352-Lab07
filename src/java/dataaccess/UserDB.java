package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Role;
import models.User;
import dataaccess.RoleDB;

/**
 *
 * @author lixia
 */
public class UserDB {

	public List<User> getAll() throws SQLException {
		RoleDB roleDB = new RoleDB();
		List<User> users = new ArrayList<>();
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection conn = cp.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM user";

		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

			while(rs.next()) {
				String email = rs.getString(1);	
				boolean active = rs.getBoolean(2);
				String firstName = rs.getString(3);
				String lastName = rs.getString(4);
				String password = rs.getString(5);
				int roleId = rs.getInt(6);

				Role role = roleDB.get(roleId);
				User user = new User(email, active, firstName, lastName, password, role);
				users.add(user);
			}
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			cp.freeConnection(conn);
		}
		
		return users;
	}

	public User get(String email)throws SQLException {
		RoleDB roleDB = new RoleDB();
		User user = null;
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection conn = cp.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String query = "SELECT * FROM user WHERE email = ?";

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				boolean active = rs.getBoolean(2);
				String firstName = rs.getString(3);
				String lastName = rs.getString(4);
				String password = rs.getString(5);
				int roleId = rs.getInt(6);

				Role role = roleDB.get(roleId);

				user = new User(email, active, firstName, lastName, password, role);

			}
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			cp.freeConnection(conn);
		}

		return user;
	}

	public void insert(User user) throws SQLException {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection conn = cp.getConnection();
		PreparedStatement ps = null;

		String query = "INSERT INTO user (email, active, first_name, last_name, password, role) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setBoolean(2, user.isActive());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getPassword());
			Role role = user.getRole();
			System.out.println(role);
			ps.setInt(6, role.getRoleId());
			ps.executeUpdate();
		} finally {
			DBUtil.closePreparedStatement(ps);
			cp.freeConnection(conn);
		}
	}

	public void delete(User user) throws SQLException {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection conn = cp.getConnection();
		PreparedStatement ps = null;

		String query = "DELETE FROM user WHERE email=?";

		try {
			ps = conn.prepareStatement(query);
			System.out.println(user.getEmail());
			ps.setString(1, user.getEmail());
			ps.executeUpdate();
		} finally {
			DBUtil.closePreparedStatement(ps);
			cp.freeConnection(conn);
		}
	}

	public void update(User user) throws SQLException {
		ConnectionPool cp = ConnectionPool.getInstance();
		Connection conn = cp.getConnection();
		PreparedStatement ps = null;

		String query = "UPDATE user SET email=?, active=?, first_name=?, last_name=?, password=?, role=? WHERE email=?";

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, user.getEmail());
			ps.setBoolean(2, user.isActive());
			ps.setString(3, user.getFirstName());
			ps.setString(4, user.getLastName());
			ps.setString(5, user.getPassword());
			ps.setInt(6, user.getRole().getRoleId());
			ps.setString(7, user.getEmail());
			ps.executeUpdate();
		} finally {
			DBUtil.closePreparedStatement(ps);
			cp.freeConnection(conn);
		}
	}
}
