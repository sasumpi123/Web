package com.bike.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.bike.db.JDBCTemplate;
import com.bike.dto.BikeDto;

public class BikeDao extends JDBCTemplate{
	
	public int insert(List<BikeDto> list) {
		System.out.println("시작");
		Connection con = getConnection();
		PreparedStatement pstm = null;
		String sql = " INSERT INTO BIKE_TB VALUES( ?,?,?,?,?,?,? )";
		int[] result = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(sql);
			for(int i = 0; i < list.size(); i++) {
				System.out.println(i);
				pstm.setString(1, list.get(i).getAddr_gu());
				pstm.setInt(2, list.get(i).getContent_id());
				pstm.setString(3, list.get(i).getContent_num());
				pstm.setString(4, list.get(i).getNew_addr());
				pstm.setInt(5, list.get(i).getCradle_count());
				pstm.setDouble(6, list.get(i).getLongitude());
				pstm.setDouble(7, list.get(i).getLatitude());
				
				pstm.addBatch();
				pstm.clearParameters();
			}
			result = pstm.executeBatch();
			System.out.println("탈출");
			
			for(int i = 0; i < result.length; i++) {
				if(result[i] == -2) {
					res++;
				}
			}
			if(res>0) {
				commit(con);
			} else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("[ERROR] : 3, 4");
			e.printStackTrace();
		} finally {
			if (pstm != null) try {pstm.close();pstm = null;} catch(SQLException ex){}
			if (con != null) try {con.close();con = null;} catch(SQLException ex){}
		}
		System.out.println(res);
		return res;
	}

}
