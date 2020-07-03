package factory;
import java.io.File;
import java.sql.Connection;

import dao.IAdminDao;
import dao.IStudentDao;
import dao.impl.AdminDaoImpl;
import dao.impl.StudentDaoImpl;

/**
 * 该类作为一个操作对象工厂类，用来实例化一些操作对象类
 * @author zjx
 *
 */
public class DaoFactory {
	/**
	 * 取到用户操作数据库类
	 * @param conn 该参数为接受一个数据库连接对象
	 * @return 返回用户操作类对象
	 */
	public static IStudentDao getIStudentDaoInstance(Connection conn) {
		return new StudentDaoImpl(conn);
	}
	/**
	 * 取到用户操作文件类
	 * @param conn 该参数为接受一个文件对象
	 * @return 返回学生操作类对象
	 */
	public static IStudentDao getIStudentDaoInstance(File file) {
		return new StudentDaoImpl(file);
	}
	/**
	 * 取到管理员操作数据库类
	 * @param conn 该参数为接受一个数据库连接对象
	 * @return 返回管理员操作类对象
	 */
	public static IAdminDao getIAdminDaoInstance(Connection conn) {
		return new AdminDaoImpl(conn);
	}
	/**
	 * 取到管理员操作文件类
	 * @param conn 该参数为接受一个文件对象
	 * @return 返回管理员操作类对象
	 */
	public static IAdminDao getIAdminDaoInstance(File file) {
		return new AdminDaoImpl(file);
	}
}
