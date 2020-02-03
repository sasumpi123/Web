package com.paging.dao;

import static com.login.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PagingDaoImpl implements PagingDao {

	@Override
	public int totalPage() {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		int res = 0;
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(TOTAL_PAGE_SQL);
			while(rs.next()) {
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			close(stmt);
			close(con);
		}
		
		return res;
	}

}
