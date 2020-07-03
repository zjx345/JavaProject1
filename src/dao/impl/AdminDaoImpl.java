package dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.TreeSet;

import dao.IAdminDao;
import tool.MyComparator;
import vo.Admin;
import vo.Course;
import vo.Courseware;
import vo.Student;
import vo.Video;

/**
 * 该类实现了IAdminDao，实现了管理操作数据库的方法
 * @author zjx
 *
 */
public class AdminDaoImpl implements IAdminDao {
	private PreparedStatement pst;
	private Connection conn;
	private File file;
	
	/**
	 * 该方法是带连接对象参数的构造方法
	 * @param conn 连接对象参数
	 */
	public AdminDaoImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	/**
	 * 该方法是带学生文件对象参数的构造方法
	 * @param file 文件对象参数
	 */
	public AdminDaoImpl(File file) {
		this.file=file;
	}
	
	// 以下方法为个人管理模块
	@Override
	public boolean isExist(Admin admin) throws Exception {
		boolean flag=false;
		// 初始化sql语句
		String sql="select count(*) from admin where adminAccount=? and adminPassword=?";
		// 执行语句
		pst=conn.prepareStatement(sql);
		pst.setString(1, admin.getAccount());
		pst.setString(2, admin.getPassword());
		ResultSet rs=pst.executeQuery();
		rs.next();
		if(rs.getInt(1)>0) {
			flag=true;
		}
		return flag;
	}
	
	@Override
	public boolean updatePassword(Admin admin,String newPassword)throws Exception {
		int flag=0;
		String sql="update admin set adminPassword=? where adminAccount=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, newPassword);
		pst.setString(2, admin.getAccount());
		flag=pst.executeUpdate();
		if(flag>0)
			return true;
		return false; 	
	}
	
	// 以下方法为管理学生模块
	@Override
	public boolean isExist(Student stu) throws Exception{
		boolean flag=false;
		BufferedReader br=new BufferedReader(new FileReader(file));
		String str=null;
		while((str=br.readLine())!=null) {
			String[] strArr=str.split(" ");
			if(strArr[0].equals(stu.getAccount())) {
				flag=true;
			}
		}
		return flag;
	}
	
	@Override
	public boolean doAddStudent(Student stu) throws Exception{
		BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
		bw.write(stu.toString());
		bw.newLine();
		bw.flush();
		return true;
	}
	
	@Override
	public boolean doRemoveStudent(Student stu)throws Exception {
		BufferedReader br=new BufferedReader(new FileReader(file));
		TreeSet<Student> ts=new TreeSet<Student>(new MyComparator());
		String str=null;
		while((str=br.readLine())!=null) {
			String[] strArr=str.split(" ");
			if(!strArr[0].equals(stu.getAccount()))
				ts.add(new Student(strArr[0],strArr[1],strArr[2],strArr[3],strArr[4],strArr[5]));
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter(file));
		for(Student s :ts) {
			bw.write(s.toString());
			bw.newLine();
			bw.flush();
		}
		return true;
	}

	@Override
	public boolean doUpdateStudent(Student stu)throws Exception {
		BufferedReader br=new BufferedReader(new FileReader(file));
		TreeSet<Student> ts=new TreeSet<Student>(new MyComparator());
		String str=null;
		while((str=br.readLine())!=null) {
			String[] strArr=str.split(" ");
			if(!strArr[0].equals(stu.getAccount())) {
				ts.add(new Student(strArr[0],strArr[1],strArr[2],strArr[3],strArr[4],strArr[5]));
			}else {
				ts.add(stu);
			}
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter(file));
		for(Student s :ts) {
			bw.write(s.toString());
			bw.newLine();
			bw.flush();
		}
		return true;
	}

	@Override
	public TreeSet<Student> findStudent(String str,int type)throws Exception {
		BufferedReader br=new BufferedReader(new FileReader(file));
		TreeSet<Student> ts=new TreeSet<Student>(new MyComparator());
		String str2=null;
		while((str2=br.readLine())!=null) {
			String[] strArr=str2.split(" ");
			if(type==1 && strArr[0].equals(str)) {
				ts.add(new Student(strArr[0],strArr[1],strArr[2],strArr[3],strArr[4],strArr[5]));
			}else if(type==2 && strArr[2].equals(str)) {
				ts.add(new Student(strArr[0],strArr[1],strArr[2],strArr[3],strArr[4],strArr[5]));
			}
		}
		return ts;
	}

	@Override
	public TreeSet<Student> findAllStudent()throws Exception {
		BufferedReader br=new BufferedReader(new FileReader(file));
		TreeSet<Student> ts=new TreeSet<Student>(new MyComparator());
		String str=null;
		while((str=br.readLine())!=null) {
			String[] strArr=str.split(" ");
			ts.add(new Student(strArr[0],strArr[1],strArr[2],strArr[3],strArr[4],strArr[5]));
		}
		return ts;
	}

	// 以下方法为管理课程模块 
	@Override
	public boolean isExist(Course course) throws Exception{
		int flag=0; // 用来标记是否存在
		String sql="select count(*) from course_info where cid=?";
		pst=conn.prepareStatement(sql);	
		pst.setString(1, course.getCno());
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			flag=rs.getInt(1);
		}
		if(flag>0)
			return true;
		return false;
	}
	
	@Override
	public boolean doAddCourse(Course course) throws Exception{
		boolean flag=false;
		String sql="insert into course_info(cid,cname,presentation,syllabus,plan) values(?,?,?,?,?)";
		pst=conn.prepareStatement(sql);
		pst.setString(1, course.getCno());
		pst.setString(2, course.getCname());
		pst.setString(3, course.getPresentation());
		pst.setString(4, course.getSyllabus());
		pst.setString(5, course.getPlan());
		flag=pst.execute();
		return flag;
	}

	@Override
	public boolean doRemoveCourse(Course course) throws Exception{
		boolean flag=false;
		String sql="delete from course_info where cid=?";
		pst=conn.prepareStatement(sql);
		pst.setString(1, course.getCno());
		flag=pst.execute();
		return flag;
	}

	@Override
	public boolean doUpdateCourse(Course course) throws Exception{
		int flag=0;
		String sql="update course_info set cname=?,presentation=?,syllabus=?,plan=? where cid=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, course.getCname());
		pst.setString(2, course.getPresentation());
		pst.setString(3, course.getSyllabus());
		pst.setString(4, course.getPlan());
		pst.setString(5, course.getCno());
		flag=pst.executeUpdate();
		if(flag>0)
			return true;
		return false;
	}

	@Override
	public ArrayList<Course> findCourse(String str, int type)throws Exception {
		ArrayList<Course> al=new ArrayList<Course>();
		PreparedStatement pst;
		String sql;
		if(type==1) {
			sql="select * from course_info where cid=? order by cname asc";
		}else {
			sql="select * from course_info where cname=? order by cname asc";
		}
		pst=conn.prepareStatement(sql);
		pst.setString(1, str);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			al.add(new Course(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
		}
		return al;
	}

	@Override
	public ArrayList<Course> findAllCourse() throws Exception{
		ArrayList<Course> al=new ArrayList<Course>();
		String sql="select * from course_info order by cname asc";
		PreparedStatement pst=conn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			al.add(new Course(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
		}
		return al;
	}

	@Override
	public boolean isExist(Courseware courseware) throws Exception{
		int flag=0; // 用来标记是否存在
		String sql="select count(*) from Courseware_info where cnum=?";
		pst=conn.prepareStatement(sql);
		pst.setString(1, courseware.getCnum());
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			flag=rs.getInt(1);
		}
		if(flag>0)
			return true;
		return false;
	}

	@Override
	public boolean doAddCourseware(Courseware courseware) throws Exception{
		boolean flag=false;
		String sql="insert into courseware_info(cid,cnum,kname,cpath,size) values(?,?,?,?,?)";
		pst=conn.prepareStatement(sql);
		pst.setString(1, courseware.getCid());
		pst.setString(2, courseware.getCnum());
		pst.setString(3, courseware.getKname());
		pst.setString(4, courseware.getCpath());
		pst.setString(5, courseware.getSize());
		flag=pst.execute();
		return flag;
	}

	@Override
	public boolean doRemoveCourseware(Courseware courseware) throws Exception{
		boolean flag=false;
		String sql="delete from courseware_info where cnum=?";
		pst=conn.prepareStatement(sql);
		pst.setString(1, courseware.getCnum());
		flag=pst.execute();
		return flag;
	}

	@Override
	public boolean doUpdateCourseware(Courseware courseware) throws Exception{
		int flag=0;
		String sql="update courseware_info set cid=?,kname=?,cpath=?,size=? where cnum=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, courseware.getCid());
		pst.setString(2, courseware.getKname());
		pst.setString(3, courseware.getCpath());
		pst.setString(4, courseware.getSize());
		pst.setString(5, courseware.getCnum());
		flag=pst.executeUpdate();
		if(flag>0)
			return true;
		return false;
	}

	@Override
	public ArrayList<Courseware> findCourseware(String str,int type) throws Exception{
		ArrayList<Courseware> al=new ArrayList<Courseware>();
		String sql=null;
		PreparedStatement pst;
		if(type==1) {
			sql="select * from courseware_info where cid=? order by kname asc";
		}else if(type==2){
			sql="select * from courseware_info where cnum=? order by kname asc";
		}else if(type==3) {
			sql="select * from courseware_info where kname=? order by kname asc";
		}
		pst=conn.prepareStatement(sql);
		pst.setString(1, str);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			al.add(new Courseware(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
		}
		return al;
	}

	@Override
	public ArrayList<Courseware> findAllCourseware() throws Exception{
		ArrayList<Courseware> al=new ArrayList<Courseware>();
		String sql="select * from courseware_info order by kname asc";
		PreparedStatement pst=conn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			al.add(new Courseware(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
		}
		return al;
	}

	@Override
	public boolean isExist(Video video) throws Exception{
		int flag=0; // 用来标记是否存在
		String sql="select count(*) from video_info where vnum=?";
		pst=conn.prepareStatement(sql);
		pst.setString(1, video.getVnum());
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			flag=rs.getInt(1);
		}
		if(flag>0)
			return true;
		return false;
	}

	@Override
	public boolean doAddVideo(Video video) throws Exception{
		boolean flag=false;
		String sql="insert into video_info(Cid,vnum,vname,vpath) values(?,?,?,?)";
		pst=conn.prepareStatement(sql);
		pst.setString(1, video.getCid());
		pst.setString(2, video.getVnum());
		pst.setString(3, video.getVname());
		pst.setString(4, video.getVpath());
		flag=pst.execute();
		return flag;
	}

	@Override
	public boolean doRemoveVideo(Video video) throws Exception{
		boolean flag=false;
		String sql="delete from video_info where Vnum=?";
		pst=conn.prepareStatement(sql);
		pst.setString(1, video.getVnum());
		flag=pst.execute();
		return flag;
	}

	@Override
	public boolean doUpdateVideo(Video video) throws Exception{
		int flag=0;
		String sql="update video_info set cid=?,vname=?,vpath=? where vnum=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.setString(1, video.getCid());
		pst.setString(2, video.getVname());
		pst.setString(3, video.getVpath());
		pst.setString(4, video.getVnum());
		flag=pst.executeUpdate();
		if(flag>0)
			return true;
		return false;
	}

	@Override
	public ArrayList<Video> findVideo(String str,int type) throws Exception{
		ArrayList<Video> al=new ArrayList<Video>();
		String sql=null;
		PreparedStatement pst;
		if(type==1) {
			sql="select * from video_info where cid=? order by vname asc";
		}else if(type==2){
			sql="select * from video_info where vnum=? order by vname asc";
		}else if(type==3) {
			sql="select * from video_info where kname=? order by vname asc";
		}
		pst=conn.prepareStatement(sql);
		pst.setString(1, str);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			al.add(new Video(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
		}
		return al;
	}

	@Override
	public ArrayList<Video> findAllVideo() throws Exception{
		ArrayList<Video> al=new ArrayList<Video>();
		String sql="select * from video_info order by vname asc";
		PreparedStatement pst=conn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			al.add(new Video(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
		}
		return al;
	}
}
