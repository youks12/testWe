package user;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Connection;

public class UserDAO {
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDAO(){
		try {
			//String dbURL = "jdbc:mysql://localhost:3306/BBS";
			//String dbURL = "jdbc:mysql://localhost:3306/BBS?useSSL=false";
			String dbURL = "jdbc:mysql://localhost:3306/BBS?serverTimezone=UTC&useSSL=false";
			String dbID = "root";
			String dbPassword = "oracle";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
			System.out.println(">>>>>>>>>>>>>>>> 111");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int login(String userID, String userPassword){
		System.out.println("login >>>>>>>>");
		String SQL = "SELECT userPassword FROM USER WHERE userID=?";
		try {
			System.out.println("try >>>>>>>>");
			pstmt = conn.prepareStatement(SQL);
			System.out.println("pstmt >>>>>>>>");
			pstmt.setString(1, userID);
			System.out.println("pstmt.setString(1, userID); >>>>>>>>");
			rs = pstmt.executeQuery();
			System.out.println("rs = pstmt.executeQuery(); >>>>>>>>");
			if(rs.next()){
				if(rs.getString(1).equals(userPassword))
					return 1;//로그인성공
				else
					return 0;//비밀번호 불일치
				
			}
			return -1; //아이디없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;//데이터베이스오류
	}
}
