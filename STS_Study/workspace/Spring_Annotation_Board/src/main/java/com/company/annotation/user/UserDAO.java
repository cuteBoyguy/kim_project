package com.company.annotation.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.company.annotation.common.JDBCUtil;

public class UserDAO {
	private Connection			conn = null;
	private PreparedStatement	pstmt = null;
	private ResultSet			rs = null;
	
	private final String USER_GET = "select * from users where id=? and password=?";
	
	public UserDO getUser(UserDO userDO) {
		UserDO user = null;
		
		try {
			System.out.println("===> JDBC�� getUser() ó��");
			
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);
			pstmt.setString(1, userDO.getId());
			pstmt.setString(2, userDO.getPassword());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				user = new UserDO();
				user.setId(rs.getString("id"));
				user.setPassword(rs.getString("password"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(rs, pstmt, conn);
		}
		return user;
	}
}





