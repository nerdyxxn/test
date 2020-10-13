package common;

import java.sql.*; 
import java.util.ArrayList;
//import java.sql.SQLException;
import java.util.List;

public class JDBCTemplate_test2 {
	public JDBCTemplate_test2() {
		
	}
	
	// DB연결
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
	
	// sql 호출
	public List<EmpVO> getEmps() {
		List<EmpVO> list1 = new ArrayList<EmpVO>();
		List<EmpVO> list2 = new ArrayList<EmpVO>();
		
		Connection conn = getConnection();
	}
}