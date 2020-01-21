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

	// 리스트 전체선택
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
						rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9));
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

	// 해당 boardno(글번호)에 맞는 글 뽑아오기(detail)
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
						rs.getString(6), rs.getString(7), rs.getDate(8), rs.getString(9));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	// 글 수정
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

	// 글 작성
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

	// 답글 작성
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
				pstm.setString(4, "RE:" + dto.getTitle());
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

	// 삭제 첫번째 방법 (해당글을 삭제하면 '삭제된 글입니다'라고 표시해준후 링크를 없앤다)
	@Override
	public int deleteFirst(int boardno) {
		Connection con = getConnection();
		Statement stmt = null;
		int res = 0;
		String sql = " update answerboard set title = '삭제된 글입니다', content = '삭제된 글입니다', deletecheck = 'N' where boardno ="
				+ boardno;
		// 삭제할 글의 번호를 받아와 해당 글의 제목과 내용을 삭제된 글입니다로 변경해주고 deletecheck 값을 'N'으로 변경한다
		// 접근하는 링크는 mylist페이지 에서 deletecheck 값으로 판별한다
		// deletecheck == 'N' 삭제된글
		// deletecheck == 'Y' 삭제되지 않은글
		try {
			stmt = con.createStatement();
			res = stmt.executeUpdate(sql);
			// 삭제(수정) 쿼리문 실행후 결과값 리턴
			if (res > 0) {
				// 삭제성공하면 commit
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}

	// 삭제 두번째 방법(글을 삭제하면 자식들까지 모두 삭제후 글 순서 update)
	@Override
	public boolean deleteSecond(int groupno, int groupseq, int titletab) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int checkGroupNo = groupno;					// 그룹번호 체크
		int checkTitleTab = titletab;				// 타이틀탭 체크(글제목 공백개수)
		int startSeq = groupseq;					// 삭제할 글들의 글번호 시작
		int[] cnt = null;							// 다중쿼리문 실행후 결과값들을 담을 배열
		int endSeq = groupseq;						// 삭제할 글들의 끝번호 (자기자신만 있을수도 있으므로 초기값을 시작번호와 같게한다)
		int res = 0;								// 실행 결과 확인하기위한 변수

		String sql = " SELECT TITLETAB,groupseq FROM ANSWERBOARD WHERE groupno = ? and GROUPSEQ > ? ORDER BY groupno,groupseq asc ";
					// 삭제하려는 글과 같은 그룹에 있으며 현재 글보다 순서가큰 값들의 titletab, groupseq, groupno 를 내림차순으로 정렬하여 가져온다 
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, groupno);	// controller에서 넘겨받은 데이터를 쿼리문에 넣어준다(삭제하려는 글의 그룹 번호)
			pstm.setInt(2, groupseq);	// controller에서 넘겨받은 데이터를 쿼리문에 넣어준다(삭제하려는 글의 그룹 순서)
			rs = pstm.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) <= checkTitleTab ) {
					// db에서 조회한 데이터를 처음부터 훑으며 titletab이 삭제하려는 글의 titletab보다 작거나 같으면 반복문을 빠져나간다
					break;
				} else {
					endSeq = rs.getInt(2); 	// 같지 않다면 삭제할 글번호의 끝번호에 조회한 데이터의 그룹번호를 담아놓는다
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		
		con = getConnection();
		pstm = null;
		System.out.println(startSeq + " " + endSeq);
		sql = " DELETE FROM answerboard WHERE groupno = ? and groupseq = ?";
		// 위에서 검색한 결과를 토대로 삭제할 데이터들의 그룹순서값을 위 쿼리문에 대입한다
		// answerboard 테이블에서 그룹번호 ? 이며 그룹순서가 ? 인 데이터를 삭제
		try {
			pstm = con.prepareStatement(sql);
			for (int i = startSeq; i <= endSeq; i++) {
				// 위에서 얻은 삭제할 글들의 글번호 시작과 끝으로 i값을 설정
				pstm.setInt(1, groupno);
				pstm.setInt(2, i);
				// 해당 값들을 대입한 쿼리문들을 메모리에 적재
				pstm.addBatch();
			}
			cnt = pstm.executeBatch();
			for (int i = 0; i < cnt.length; i++) {
				// 메모리에 적재한 쿼리문들을 실행하면서 결과값들을 cnt배열에 답고
				// 삭제후 나머지 글들의 글순서를 update하기위해 삭제된 글의 개수를 가져옴
				if (cnt[i] == -2) { // 성공하면 -2 실패하면 -3
					res++;
				}
			}
			if (res == endSeq - startSeq + 1) {
				// 모두 삭제되었으면 commit
				commit(con);
			} else {
				rollback(con);
				res = 0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		con = getConnection();
		pstm = null;
		// 삭제된 글을이 해당 그룹의 마지막 글들이었다면 update할 필요가없음
		// 예외처리 작성할것
		int finallRes = 0;
		sql = " update answerboard set groupseq = groupseq-? WHERE groupno = ? and groupseq > ?";
		// 글들이 삭제되면 삭제된 글 뒤에있던 글들의 순서를 삭제된 글들 개수만큼 앞으로 당겨와주는 작업
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, res);
			pstm.setInt(2, groupno);
			pstm.setInt(3, endSeq);
			finallRes = pstm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}

	// 삭제 세번째 방법(해당글이 자식요소를 가지고있는지 확인하고 자식이 있으면 삭제하지 못한다)
	@Override
	public int deleteThird(int boardno, int groupno, int groupseq) {
		Connection con = getConnection();
		Statement stmt = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		String sql = " SELECT * FROM ANSWERBOARD WHERE groupno = ? and GROUPSEQ > ? ";
		// 해당 그룹에 있으면서 삭제하려는 글보다 순서가 큰값들이 있는지 확인

		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, groupno);
			pstm.setInt(2, groupseq);
			rs = pstm.executeQuery();
			// controller에서 가져온 groupno, groupseq을 쿼리문에 대입 
			while (rs.next()) {
				res++;
				// 있으면 res값 증가
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(pstm);
			close(con);
		}
		// 만약 res==0 이라면 즉 삭제하려는 글과 같은 그룹에 있으면서 그룹순서가 큰 값들이 없다면
		if (res == 0) {
			con = getConnection();
			stmt = null;
			sql = " delete from answerboard where boardno = " + boardno;
			// 해당글 삭제하는 쿼리문
			try {
				stmt = con.createStatement();
				res = stmt.executeUpdate(sql);
				// 쿼리문 실행후 결과값 res로 리턴
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				commit(con);
			}
			// 해당글 삭제후 결과값 리턴
			return res;
		} else {
			// 해당글이 자식요소를 가지고있다면 삭제실패 즉 0을 리턴
			return 0;
		}
	}

}
