package service;

import java.util.ArrayList;

import vo.Course;
import vo.Courseware;
import vo.Student;
import vo.Video;

/**
 * 该接口定义Student的业务层的执行标准
 * @author zjx
 *
 */
public interface IStudentService {
	/**
	 * 该方法用来修改学生密码
	 * @param stu 需要修改的学生
	 * @param newPassword 需要修改的新密码
	 * @return 返回一个boolean代表是否修改成功
	 */
	public boolean updatePassword(Student stu,String newPassword);
	/**
	 * 该方法用来注册
	 * @param stu 需要注册的学生
	 * @return 返回一个boolean代表是否注册成功
	 */
	public boolean register(Student stu);
	/**
	 * 该方法用来登陆账号
	 * @param stu 要登陆的学生
	 * @return 返回一个boolean代表是否登陆成功
	 */
	public boolean login(Student stu);
	/**
	 * 该方法用来查找课程
	 * @param cname 要查找的课程名
	 * @return 返回查找的课程对象
	 */
	public Course findCourse(String cname);
	/**
	 * 该方法根据课程号查找所有课件
	 * @param cid 要查找的课程号
	 * @return 返回一个课件集合对象
	 */
	public ArrayList<Courseware> findAllCourseware(String cid);
	/**
	 * 该方法用来下载课件
	 * @param cpath 课件路径字符串参数
	 * @return 返回一个boolean值，代表是否下载成功
	 */
	public boolean downloadCourseware(String cpath);
	/**
	 * 该方法根据课程号查找所有的视频
	 * @param cid 要查找的课程号
	 * @return 返回一个视频集合
	 */
	public ArrayList<Video> findAllVideo(String cid);
	/**
	 * 该方法用来观看视频
	 * @param vpath 视频路径字符串参数
	 */
	public void watchVideo(String vpath);
}
