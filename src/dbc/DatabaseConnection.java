package dbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 该类负责数据库连接与关闭
 * @author zjx
 *
 */
public class DatabaseConnection {
	// Driver类的路径
	private static final String DRIVER_PATH="com.mysql.jdbc.Driver";
	
	//private static final String URL="jdbc:mysql://localhost:3306/在线课程学习系统?characterEncoding=utf-8&amp;serverTimezone=UTC&amp;useSSL=false";
	// 数据库URL
	private static final String URL="jdbc:mysql://localhost:3306/在线课程学习系统?useSSL=false";
	// 用户名
	private static final String USER="root";
	// 密码
	private static final String PASSWORD="mysql123456";
	// 连接对象
	private Connection conn=null;
	
	/**
	 * 该构造方法用来实例化连接对象，直接赋值到conn变量
	 */
	public DatabaseConnection() {
		try {
			Class.forName(DRIVER_PATH);
			conn=DriverManager.getConnection(URL, USER,PASSWORD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 取得一个连接对象
	 * @return 返回一个Connection对象
	 */
	public Connection getConnection()  {
		return conn;
	}
	
	/**
	 * 关闭连接
	 */
	public void close() {
		if(this.conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

