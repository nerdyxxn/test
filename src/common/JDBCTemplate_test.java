package common;

import java.sql.*;
import java.util.ArrayList;
//import java.sql.SQLException;
import java.util.List;

public class JDBCTemplate_test {
	public JDBCTemplate_test() { // 생성자 메소드
	}

	// DB 연결
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		} catch (Exception e) {
			System.out.println("!!!실패!!!");
			e.printStackTrace();
		}
		return conn;
	}

	// sql문 호출
	public List<EmpVO> getEmps() {
		List<EmpVO> list1 = new ArrayList<EmpVO>();
		List<EmpVO> list2 = new ArrayList<EmpVO>();

		Connection conn = getConnection();	//...?

		String sql = "select * from emp";
		String sql1 = "";
		String sql2 = "";
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String minValue = "2000";

		try {
			// 1. statement
			sql1 = sql + " where sal > " + minValue;
			stmt = conn.createStatement();

			System.out.println("******** Statement 결과");
			rs = stmt.executeQuery(sql1);
			if (rs.next()) {
				do {
					EmpVO vo = new EmpVO();
					vo.setEmpno(rs.getInt(1));
					vo.setEname(rs.getString("Ename"));
					vo.setJob(rs.getString("Job"));

					list1.add(vo);
				} while (rs.next());
			} else {
				System.out.println("찾는 데이터가  없습니다.");
			}

			// 2.PreparedStatement
			sql2 = sql + " where sal > ?";
			pstmt = conn.prepareStatement(sql2);

			System.out.println("******* PreparedStatement 결과");
			pstmt.setString(1, minValue);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				do {
					EmpVO vo = new EmpVO();
					vo.setEmpno(rs.getInt(1));
					vo.setEname(rs.getString("Ename"));
					vo.setJob(rs.getNString("Job"));

					list2.add(vo);
				} while (rs.next());
			} else {
				System.out.println("찾는 데이터가 없습니다.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list1;
	}
}