package dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeSet;

import dao.IStudentDao;
import tool.MyComparator;
import vo.Course;
import vo.Courseware;
import vo.Student;
import vo.Video;

/**
 * 该类实现了IStudentDao接口 ，具体实现了修改密码的方法
 * @author zjx
 *
 */
public class StudentDaoImpl implements IStudentDao {

	private PreparedStatement pst;
	private Connection conn;
	private File file;
	/**
	 * 该方法是带连接对象参数的构造方法
	 * @param conn 连接对象参数
	 */
	public StudentDaoImpl(Connection conn) {
		this.conn=conn;
	}
	/**
	 * 该方法是带学生文件对象参数的构造方法
	 * @param file 文件对象参数
	 */
	public StudentDaoImpl(File file) {
		this.file=file;
	}
	@Override
	public boolean doUpdatePassword(Student stu,String newPassword) throws Exception {
		boolean flag=false;
		if(isExist(stu))
			flag=true;
		BufferedReader br=new BufferedReader(new FileReader(file));
		TreeSet<Student> ts=new TreeSet<Student>(new MyComparator());
		String str=null;
		while((str=br.readLine())!=null) {
			String[] strArr=str.split(" ");
			if(!strArr[0].equals(stu.getAccount())) {
				ts.add(new Student(strArr[0],strArr[1],strArr[2],strArr[3],strArr[4],strArr[5]));
			}else {
				stu.setPassword(newPassword);
				ts.add(stu);
			}
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter(file));
		for(Student s :ts) {
			bw.write(s.toString());
			bw.newLine();
			bw.flush();
		}
		return flag;
	}
	@Override
	public boolean doInsert(Student stu) throws Exception{
		if(isExist(stu))
			return false;
		else {
			BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
			bw.write(stu.toString());
			bw.newLine();
			bw.flush();
			return true;
		}
	}
	@Override
	public boolean isExist(Student stu)throws Exception {
		BufferedReader br=new BufferedReader(new FileReader(file));
		String str=null;
		while((str=br.readLine())!=null) {
			String[] strArr=str.split(" ");
			if(strArr[0].equals(stu.getAccount()) && strArr[1].equals(stu.getPassword())) {
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean isExist(String cname) throws Exception{
		boolean flag=false;
		try {
			// 初始化sql语句
			String sql="select count(*) from course_info where cname=?";
			// 执行语句
			pst=conn.prepareStatement(sql);
			pst.setString(1, cname);
			ResultSet rs=pst.executeQuery();
			rs.next();
			if(rs.getInt(1)>0)
				flag=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public Course findCourse(String cname)throws Exception {
		Course course=null;
		String sql="select cid,cname,presentation,syllabus,plan from course_info where cname=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, cname);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			course=new Course(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
		}
		return course;
	}
	@Override
	public ArrayList<Courseware> findAllCourseware(String cid) throws Exception{
		ArrayList<Courseware> al=new ArrayList<Courseware>();
		String sql="select * from courseware_info where cid=? order by kname asc";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, cid);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			al.add(new Courseware(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
		}
		return al;
	}
	@Override
	public ArrayList<Video> findAllVideo(String cid) throws Exception{
		ArrayList<Video> al=new ArrayList<Video>();
		String sql="select * from video_info where cid=? order by vname asc";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, cid);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			al.add(new Video(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
		}
		return al;
	}
}

