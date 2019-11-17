package utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class JDBCUtil {

	static Properties pros = null;

	static {//只加载一次
		pros = new Properties();

		try {
			pros.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("dataBase.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection(){
		try {
			Class.forName(pros.getProperty("mysqlDriver"));
			return DriverManager.getConnection(pros.getProperty("mysqlURL"),
					pros.getProperty("mysqlUser"),pros.getProperty("mysqlPassword"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void close(ResultSet rs, PreparedStatement ps, Connection conn){
		try{
			if(ps!=null){
				ps.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}

		try{
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}

		try{
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs, PreparedStatement ps){
		try{
			if(ps!=null){
				ps.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}

		try{
			if(rs!=null){
				rs.close();
			}
		} catch (SQLException e){
			e.printStackTrace();
		}

	}
	
}
