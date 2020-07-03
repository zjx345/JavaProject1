package dao;

import java.util.ArrayList;

import vo.Course;
import vo.Courseware;
import vo.Student;
import vo.Video;

/**
 * 该接口定义了一些学生操作数据库的方法
 * @author zjx
 *
 */

public interface IStudentDao {
	/**
	 * 学生修改文件中的密码
	 * @param stu 需要修改密码的学生
	 * @param newPassword 需要修改的新密码
	 * @return 返回一个boolean值，代表是否修改成功
	 * @throws Exception
	 */
	public boolean doUpdatePassword(Student stu,String newPassword)throws Exception;
	/**
	 * 学生插入数据到文件
	 * @param stu 要插入的数据
	 * @return 返回一个boolean值，代表是否插入成功
	 * @throws Exception
	 */
	public boolean doInsert(Student stu)throws Exception;
	/**
	 * 判断学生是否在文件中存在
	 * @param stu 要判断的学生
	 * @return 如果存在，返回true，否则返回false
	 * @throws Exception
	 */
	public boolean isExist(Student stu)throws Exception;
	/**
	 * 判断课程是否在数据库中存在
	 * @param cname 要判断的课程
	 * @return 如果存在，返回true，否则返回false
	 * @throws Exception
	 */
	public boolean isExist(String cname)throws Exception;
	/**
	 * 学生根据课程名查找课程
	 * @param cname 要查找的课程名
	 * @return 返回查找的课程对象
	 * @throws Exception
	 */
	public Course findCourse(String cname)throws Exception;
	/**
	 * 该方法用来查找所有的课件
	 * @param cid 要查找的课程号
	 * @return 返回一个课件集合
	 * @throws Exception
	 */
	public ArrayList<Courseware> findAllCourseware(String cid)throws Exception;
	/**
	 * 该方法用来查找所有的视频
	 * @param cid 要查找的课程号
	 * @return 返回一个视频集合
	 * @throws Exception
	 */
	public ArrayList<Video> findAllVideo(String cid) throws Exception;
}
