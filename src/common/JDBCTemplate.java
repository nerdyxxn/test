package common;

import java.sql.*;
import java.util.ArrayList;
//import java.sql.SQLException;
import java.util.List;

public class JDBCTemplate {
	public JDBCTemplate() {	} // 생성자 메소드 만들기
	
	public static Connection getConnection() { // 데이터베이스랑 연결하는 것!
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");	// 괄호안의 클래스를 가지고 올거다! 자바에서 제공하는 메소드.
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			// 연결된 정보는 모두 conn이 가지고 있어. 여기에 sql을 넣고 실행하는 뭐 그런거야
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}catch(ClassNotFoundException e) {
//			e.printStackTrace();
		}catch(Exception e) {	// try 내에 예외처리할 부분 2개이므로 Exception으로 수정했어!
			System.out.println("!!!실패!!!");
			e.printStackTrace();
		}
		return conn;
	}
	
	public List<EmpVO> getEmps() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		Connection conn = getConnection();
		
		String sql ="select * from emp";
		String sql1 ="";
		String sql2 ="";
		Statement stmt = null;
		PreparedStatement pstmt = null;
		int rsInt = 0;
		ResultSet rs = null;
		String minValue = "2000";
		
		
		try {
		// ********************* sql 문을 실행하기 위한 2가지 statement  교재 42~43 *********************
		// 1. Statement
		sql1= sql+" where sal > " + minValue;
		stmt = conn.createStatement();
		// 2. PreparedStatement
		sql2 = sql + " where sal > ?";   // ? 를 사용함.
		pstmt = conn.prepareStatement(sql2);
		
		// sql 문이 실행된 결과 2가지
		// 1. int 형    - insert/ update / delete  1~정상동작, 0또는음수인 경우 비정상동작
		// 2. ResultSet 형 (oracle.jdbc.OracleDriver에서 제공해주는 자료형)  - select문의 결과로 행열이 존재함.
		
		//Statement
		System.out.println("********* Statement 결과");
		rs = stmt.executeQuery(sql1);	// 결국 rs에 담기는 값은 두 방법 다 같아. 그래서 아래 코드가 똑같은 거지!
		if(rs.next()) {
			do {
				EmpVO vo = new EmpVO();
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString("Ename"));
				vo.setJob(rs.getString("Job"));
				System.out.println(
						"empno: " + rs.getInt(1)   // 1번째 colume의 데이터를 int형태로 꺼내올거다.
						+ "  eName: " + rs.getString(2) // 2번째 colume의 데이터를 String 형태로 꺼내올거임.
						+ "  eJob: " + rs.getString("job") // colume label을 이용해 데이터를 String 형태로 꺼내올거임.
				);
				list.add(vo);
			} while(rs.next());		// rs안에는 데이터가 arraylist 형태로 들어가 있을거야. 그 데이터들은 주소를 가지고 있지. rs.next()는 rs의 첫번째
		} else {
			System.out.println("찾는 데이터가 없습니다.");
		}
		
		//PreparedStatement : 미리 sql문을 던져놓고 시작?
		System.out.println("********* PreparedStatement 결과");
		pstmt.setString(1, minValue);   // pstmt에 세팅을 해줄거야~ setString 또는 setInt (?의 순서대로 1~n까지, 값) *************
		rs = pstmt.executeQuery();		// 이미 위에서 pstmt에 conn.prepareStatement(sql2); sql2를 넣었어
		if(rs.next()) {
			do {
				System.out.println(
						"empno: " + rs.getInt(1)   // 1번째 colume의 데이터를 int형태로 꺼내올거다.
						+ "  eName: " + rs.getString(2) // 2번쨰 colume의 데이터를 String 형태로 꺼내올거임.
						+ "  eJob: " + rs.getString("job") // colume label을 이용해 데이터를 String 형태로 꺼내올거임.
				);
			} while(rs.next());
		} else {
			System.out.println("찾는 데이터가 없습니다.");
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
//	public static void main(String[] args) {
//		Connection conn = getConnection();
//		if(conn!=null) {
//			System.out.println("JDBC 연결 성공 - b");
//		}
//		else {
//			System.out.println("!!!실패!!! - b");
//		}
//	}
}