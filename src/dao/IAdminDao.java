package dao;
import java.util.ArrayList;
import java.util.TreeSet;

import vo.Admin;
import vo.Course;
import vo.Courseware;
import vo.Student;
import vo.Video;

/**
 * 该接口定义了一些管理员操作数据库的方法
 * @author zjx
 *
 */
public interface IAdminDao {
	/**
	 * 该方法用来判断管理员是否存在
	 * @param admin 要判断的管理员
	 * @return 返回一个boolean值，代表是否存在
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean isExist(Admin admin) throws Exception;
	/**
	 * 该方法用来修改管理员的密码
	 * @param admin 修改密码的管理员
	 * @param newPassword 修改后的密码
	 * @return 返回一个boolean值，代表是否修改成功
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean updatePassword(Admin admin,String newPassword)throws Exception;
	/**
	 * 该方法用来判断文件中是否存在该学生
	 * @param stu 要判断的学生
	 * @return 返回一个boolean值，代表是否存在
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean isExist(Student stu)throws Exception;
	/**
	 * 该方法用来在文件中增加学生
	 * @param stu 要增加的学生
	 * @return 返回一个boolean值，代表是否增加成功
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean doAddStudent(Student stu)throws Exception;
	/**
	 * 该方法用来在文件中删除学生
	 * @param stu 要删除的学生
	 * @return 返回一个boolean值，代表是否删除成功
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean doRemoveStudent(Student stu)throws Exception;
	/**
	 * 该方法用来在文件中修改学生
	 * @param stu 要修改的学生
	 * @return 返回一个boolean值，代表是否修改成功
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean doUpdateStudent(Student stu)throws Exception;
	/**
	 * 该方法根据账号或者姓名在文件中查找学生
	 * @param str 要查找的账号或者姓名
	 * @param type 1代表账号，2代表姓名
	 * @return 返回一个学生集合对象
	 * @throws Exception 抛出一个Exception异常
	 */
	public TreeSet<Student> findStudent(String str,int type)throws Exception;
	/**
	 * 该方法用来查找文件中所有的用户信息
	 * @return 返回所有学生的集合
	 * @throws Exception 抛出一个Exception异常
	 */
	public TreeSet<Student> findAllStudent()throws Exception;
	/**
	 * 该方法用来判断课程是否在数据库中存在
	 * @param course 课程对象参数
	 * @return 返回一个boolean值，代表是否存在
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean isExist(Course course)throws Exception;
	/**
	 * 该方法用来在数据库中增加课程
	 * @param course 课程对象参数
	 * @return 返回一个boolean值，代表是否增加成功
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean doAddCourse(Course course)throws Exception;
	/**
	 * 该方法用来在数据库中删除课程
	 * @param course 要删除的课程
	 * @return 返回一个boolean值，代表是否删除成功
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean doRemoveCourse(Course course)throws Exception;
	/**
	 * 该方法用来在数据库中修改课程
	 * @param course 要修改的课程
	 * @return 返回一个boolean值，代表是否修改成功
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean doUpdateCourse(Course course)throws Exception;
	/**
	 * 该方法根据课程号或课程名在数据库中查找课程 
	 * @param str 要查找的字符串
	 * @param type 按什么查询，1代表按课程号查找，2代表按课程名查找
	 * @return 返回要查找的课程对象
	 * @throws Exception 抛出一个Exception异常
	 */
	public ArrayList<Course> findCourse(String str,int type)throws Exception;
	/**
	 * 该方法用来查找数据库中所有的课程对象
	 * @return 返回所有课程对象的集合
	 * @throws Exception 抛出一个Exception异常
	 */
	public ArrayList<Course> findAllCourse()throws Exception;
	/**
	 * 该方法用来判断课件是否存在
	 * @param courseware 要查找的课件
	 * @return 返回一个boolean值，代表是否存在
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean isExist(Courseware courseware)throws Exception;
	/**
	 * 该方法用来增加课件
	 * @param courseware 要增加的课件
	 * @return 返回一个boolean值，代表是否增加成功
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean doAddCourseware(Courseware courseware)throws Exception;
	/**
	 * 该方法用来删除课件
	 * @param courseware 要删除的课件
	 * @return 返回一个boolean值，代表是否删除成功
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean doRemoveCourseware(Courseware courseware)throws Exception;
	/**
	 * 该方法用来修改课件
	 * @param courseware 要修改的课件
	 * @return 返回一个boolean值，代表是否修改成功
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean doUpdateCourseware(Courseware courseware)throws Exception;
	/**
	 * 该方法可以根据课程号、课件号、课件名查找课件
	 * @param str 要查找的课程号或者课件号或者课件名
	 * @param type 1代表课程号，2代表课件号，3代表课件名
	 * @return 返回一个课件集合对象
	 * @throws Exception 抛出一个Exception异常
	 */
	public ArrayList<Courseware> findCourseware(String str,int type)throws Exception;
	/**
	 * 该方法用来查找所有的课件
	 * @return 返回一个课件集合
	 * @throws Exception 抛出一个Exception异常
	 */
	public ArrayList<Courseware> findAllCourseware()throws Exception;
	/**
	 * 该方法判断视频是否存在
	 * @param video 要判断的视频
	 * @return 返回一个boolean值，代表是否存在
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean isExist(Video video)throws Exception;
	/**
	 * 该方法用来增加视频
	 * @param video 要增加的视频
	 * @return 返回一个boolean值，代表是否增加成功
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean doAddVideo(Video video)throws Exception;
	/**
	 * 该方法用来删除视频
	 * @param video 要删除的视频
	 * @return 返回一个boolean值，代表是否删除成功
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean doRemoveVideo(Video video)throws Exception;
	/**
	 * 该方法用来修改视频
	 * @param video 要修改的视频
	 * @return 返回一个boolean值，代表是否修改成功
	 * @throws Exception 抛出一个Exception异常
	 */
	public boolean doUpdateVideo(Video video)throws Exception;
	/**
	 * 该方法根据课程号或者视频号或者视频名查找视频
	 * @param str 要查找的课程号或者视频号或者视频名
	 * @param type 1代表课程号，2代表视频号，3代表视频名
	 * @return 返回一个视频集合对象
	 * @throws Exception 抛出一个Exception异常
	 */
	public ArrayList<Video> findVideo(String str,int type)throws Exception;
	/**
	 * 该方法查找所有的视频
	 * @return 返回一个视频集合对象
	 * @throws Exception 抛出一个Exception异常
	 */
	public ArrayList<Video> findAllVideo()throws Exception;
}
