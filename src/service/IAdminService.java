package service;

import java.util.ArrayList;
import java.util.TreeSet;

import vo.Admin;
import vo.Course;
import vo.Courseware;
import vo.Student;
import vo.Video;

/**
 * 该接口定义Admin的业务层的执行标准
 * @author zjx
 *
 */
public interface IAdminService {
	/**
	 * 该方法用来登陆账号
	 * @param admin 要登陆的管理员
	 * @return 返回一个boolean代表是否登陆成功
	 */
	public boolean login(Admin admin);
	/**
	 * 该方法用来修改自己的密码
	 * @param admin 要修改密码的管理员
	 * @param newPassword 修改后的密码
	 * @return 返回一个boolean值，代表是否修改成功
	 */
	public boolean updatePassword(Admin admin, String newPassword);
	/**
	 * 该方法用来判断学生是否存在
	 * @param stu 要判断的学生
	 * @return 返回一个boolean值，代表是否存在
	 */
	public boolean isExist(Student stu);
	/**
	 * 该方法用来增加学生信息
	 * @param stu 要增加的学生
	 * @return 返回一个boolean值，代表是否增加成功
	 */
	public boolean addStudent(Student stu);
	/**
	 * 该方法用来删除学生
	 * @param stu 要删除的学生
	 * @return 返回一个boolean值，代表是否删除成功
	 */
	public boolean removeStudent(Student stu);
	/**
	 * 该方法用来修改学生
	 * @param stu 修改前的学生
	 * @return 返回一个boolean值，代表是否修改成功
	 */
	public boolean updateStudent(Student stu);
	/**
	 * 该方法根据学生账号或者学生姓名来查找学生
	 * @param str 要查找的学生账号或者学生姓名
	 * @param type 1代表学生账号，2代表学生姓名
	 * @return 返回一个学生集合对象
	 */
	public TreeSet<Student> searchStudent(String str,int type);
	/**
	 * 该方法用来查找所有学生
	 * @return 返回一个学生集合对象
	 */
	public TreeSet<Student> searchAllStudent();
	/**
	 * 该方法用来判断课程是否存在
	 * @param course 要判断的课程
	 * @return 返回一个boolean值，代表是否存在
	 */
	public boolean isExist(Course course);
	/**
	 * 该方法用来增加课程信息
	 * @param course 要增加的课程
	 * @return 返回一个boolean值，代表是否增加成功
	 */
	public boolean addCourse(Course course);
	/**
	 * 该方法用来删除课程
	 * @param course 要删除的课程
	 * @return 返回一个boolean值，代表是否删除成功
	 */
	public boolean removeCourse(Course course);
	/**
	 * 该方法用来修改课程
	 * @param course 要修改的课程
	 * @return 返回一个boolean值，代表是否修改成功
	 */
	public boolean updateCourse(Course course);
	/**
	 * 该方法根据课程号或者课程名来查找课程
	 * @param str 要查找的课程号或者课程名
	 * @param type 1代表课程号，2代表课程名
	 * @return 返回一个课程集合对象
	 */
	public ArrayList<Course> searchCourse(String str,int type);
	/**
	 * 该方法用来查找所有课程
	 * @return 返回一个课程集合对象
	 */
	public ArrayList<Course> searchAllCourse();
	/**
	 * 该方法用来判断课件是否存在
	 * @param courseware 要判断的课件
	 * @return 返回一个boolean值，代表是否存在
	 */
	public boolean isExist(Courseware courseware);
	/**
	 * 该方法用来增加课件信息
	 * @param courseware 要增加的课件
	 * @return 返回一个boolean值，代表是否增加成功
	 */
	public boolean addCourseware(Courseware courseware);
	/**
	 * 该方法用来删除课件
	 * @param courseware 要删除的课件
	 * @return 返回一个boolean值，代表是否删除成功
	 */
	public boolean removeCourseware(Courseware courseware);
	/**
	 * 该方法用来修改课件
	 * @param courseware 要修改的课件
	 * @return 返回一个boolean值，代表是否修改成功
	 */
	public boolean updateCourseware(Courseware courseware);
	/**
	 * 该方法根据课程号、课件号或课件名来查找课程
	 * @param str 要查找的课程号、课件号或课件名
	 * @param type 1代表课程号，2代表课件号，3代表课件名
	 * @return 返回一个课件集合对象
	 */
	public ArrayList<Courseware> searchCourseware(String str,int type);
	/**
	 * 该方法用来查找所有课程
	 * @return 返回一个课程集合对象
	 */
	public ArrayList<Courseware> searchAllCourseware();
	/**
	 * 该方法用来判断视频是否存在
	 * @param video 要判断的视频
	 * @return 返回一个boolean值，代表是否存在
	 */
	public boolean isExist(Video video);
	/**
	 * 该方法用来增加视频信息
	 * @param video 要增加的视频
	 * @return 返回一个boolean值，代表是否增加成功
	 */
	public boolean addVideo(Video video);
	/**
	 * 该方法用来删除视频
	 * @param video 要删除的视频
	 * @return 返回一个boolean值，代表是否删除成功
	 */
	public boolean removeVideo(Video video);
	/**
	 * 该方法用来修改视频
	 * @param video 要修改的视频
	 * @return 返回一个boolean值，代表是否修改成功
	 */
	public boolean updateVideo(Video video);
	/**
	 * 该方法根据课程号、视频号或视频名来查找课程
	 * @param str 要查找的课程号、视频号或视频名
	 * @param type 1代表课程号，2代表视频号，3代表视频名
	 * @return 返回一个视频集合对象
	 */
	public ArrayList<Video> searchVideo(String str,int type);
	/**
	 * 该方法用来查找所有视频
	 * @return 返回一个视频集合对象
	 */
	public ArrayList<Video> searchAllVideo();
}
