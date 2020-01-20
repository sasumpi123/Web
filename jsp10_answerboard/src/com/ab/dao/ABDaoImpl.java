package com.ab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static com.ab.db.JDBCTemplate.*;


import com.ab.dto.ABDto;



public class ABDaoImpl implements ABDao {

	@Override
	public List<ABDto> selectList() {
		Connection con = getConnection();
		ResultSet rs = null;
		List<ABDto> list = new ArrayList<>();
		Statement stmt = null;
		String sql = " SELECT * FROM ANSWERBOARD ORDER BY GROUPNO,groupseq asc ";

		try {
			stmt = con.createStatement();
			System.out.println("3.query문 준비");
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ABDto dto = new ABDto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDate(8));
				list.add(dto);
			}
			System.out.println("4. 실행 및 리턴");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public ABDto selectOne(int boardno) {
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		ABDto dto = new ABDto();
		String sql = " select * from answerboard where boardno = " + boardno;

		try {
			stmt = con.createStatement();
			System.out.println("쿼리문 준비");
			rs = stmt.executeQuery(sql);
			System.out.println("실행 및 결과 리턴");
			while (rs.next()) {
				dto = new ABDto(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getDate(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public int update(ABDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " update answerboard set title = ?, content = ? where boardno =" + dto.getBoardno();
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("쿼리준비");
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			res = pstm.executeUpdate();
			System.out.println("실행 및 결과 리턴");
			if (res > 0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public boolean delete(int groupno, int groupseq, int titletab) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		int[] cnt = null;
		int rescount = 0;

		String sql = " SELECT TITLETAB,groupseq FROM ANSWERBOARD WHERE GROUPNO = ? AND GROUPSEQ > ? ORDER BY groupseq asc ";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, groupno);
			pstm.setInt(2, groupseq);
			rs = pstm.executeQuery();
			while (rs.next()) {
				if(res<rs.getInt(1))
					res = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sql = " DELETE FROM answerboard WHERE groupno = ? and titletab = ?";
		try {
			pstm = con.prepareStatement(sql);

			for (int i = titletab; i <= res; i++) {
				pstm.setInt(1, groupno);
				pstm.setInt(3, i);
				pstm.addBatch();
			}
			cnt = pstm.executeBatch();

			for (int i = 0; i < cnt.length; i++) {
				if (cnt[i] == -2) { // 성공하면 -2 실패하면 -3
					rescount++;
				}
			}
			if (rescount == res - titletab + 1) {
				commit(con);
			} else {
				rollback(con);
				res = 0;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (res == cnt.length) ? true : false;
	}

	@Override
	public int insert(ABDto dto) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " insert into answerboard values(boardnoseq.nextval, groupnoseq.nextval,1,0,?,?,?,sysdate)";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setString(3, dto.getWriter());

			System.out.println("쿼리문 준비" + sql);
			res = pstm.executeUpdate();
			System.out.println("실행 및 결과 리턴" + res);
			commit(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int insertAnswer(ABDto dto, int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " update answerboard " + "set groupseq = groupseq+1 "
				+ "where groupno = (select groupno from answerboard where boardno = ?) "
				+ "and groupseq > (select groupseq from answerboard where boardno = ?)";
		try {
			pstm = con.prepareStatement(sql);

			pstm.setInt(1, boardno);
			pstm.setInt(2, boardno);
			System.out.println(" 현재 글들 update 쿼리문 준비" + sql);

			res = pstm.executeUpdate();
			System.out.println("실행 및 결과 리턴" + res);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (res >= 0) {
			System.out.println("글들 위치 변경후 댓글입력");
			sql = " insert into answerboard " + "values( " + "boardnoseq.nextval, "
					+ "(select groupno from answerboard where boardno = ?), "
					+ "(select groupseq from answerboard where boardno = ?)+1, "
					+ "(select titletab from answerboard where boardno = ?)+1, " + "?, " + "?, " + "?, " + "sysdate "
					+ ")";
			try {
				pstm = con.prepareStatement(sql);
				System.out.println("댓글 insert 쿼리문 준비");
				pstm.setInt(1, boardno);
				pstm.setInt(2, boardno);
				pstm.setInt(3, boardno);
				pstm.setString(4, dto.getTitle());
				pstm.setString(5, dto.getContent());
				pstm.setString(6, dto.getWriter());
				res = pstm.executeUpdate();
				System.out.println("실행 및 결과 리턴");
				commit(con);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return res;
	}

}
