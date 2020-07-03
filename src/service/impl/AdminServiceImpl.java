package service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.TreeSet;

import dbc.DatabaseConnection;
import factory.DaoFactory;
import service.IAdminService;
import vo.Admin;
import vo.Course;
import vo.Courseware;
import vo.Student;
import vo.Video;

public class AdminServiceImpl implements IAdminService{
	
	private DatabaseConnection dbc=new DatabaseConnection();
	private File file=new File("E:\\Student.txt");

	@Override
	public boolean login(Admin admin) {
		try {
			if(DaoFactory.getIAdminDaoInstance(dbc.getConnection()).isExist(admin))
				return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return false;
	}

	@Override
	public boolean updatePassword(Admin admin, String newPassword) {
		try {
			if(DaoFactory.getIAdminDaoInstance(dbc.getConnection()).updatePassword(admin, newPassword))
				return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return false;
	}
	
	@Override
	public boolean isExist(Student stu) {
		boolean flag=false;
		try {
			flag=DaoFactory.getIAdminDaoInstance(file).isExist(stu);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@Override
	public boolean addStudent(Student stu) {
		boolean flag=false;
		try {
			flag=DaoFactory.getIAdminDaoInstance(file).doAddStudent(stu);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean removeStudent(Student stu) {
		try {
			if(DaoFactory.getIAdminDaoInstance(file).doRemoveStudent(stu))
				return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateStudent(Student stu) {
		boolean flag=false;
		try {
			flag=DaoFactory.getIAdminDaoInstance(file).doUpdateStudent(stu);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public TreeSet<Student> searchStudent(String str, int type) {
		TreeSet<Student> ts=null;
		try {
			ts=DaoFactory.getIAdminDaoInstance(file).findStudent(str, type);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	@Override
	public TreeSet<Student> searchAllStudent() {
		TreeSet<Student> ts=null;
		try {
			ts=DaoFactory.getIAdminDaoInstance(file).findAllStudent();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ts;
	}

	@Override
	public boolean isExist(Course course) {
		boolean flag=false;
		try {
			flag=DaoFactory.getIAdminDaoInstance(dbc.getConnection()).isExist(course);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean addCourse(Course course) {
		boolean flag=false;
		try {
			flag=DaoFactory.getIAdminDaoInstance(dbc.getConnection()).doAddCourse(course);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean removeCourse(Course course) {
		try {
			if(DaoFactory.getIAdminDaoInstance(dbc.getConnection()).doRemoveCourse(course))
				return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return false;
	}

	@Override
	public boolean updateCourse(Course course) {
		boolean flag=false;
		try {
			flag=DaoFactory.getIAdminDaoInstance(dbc.getConnection()).doUpdateCourse(course);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return flag;
	}
	
	@Override
	public ArrayList<Course> searchCourse(String str, int type) {
		ArrayList<Course> al=null;
		try {
			al=DaoFactory.getIAdminDaoInstance(dbc.getConnection()).findCourse(str, type);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return al;
	}

	@Override
	public ArrayList<Course> searchAllCourse() {
		ArrayList<Course> al=null;
		try {
			al=DaoFactory.getIAdminDaoInstance(dbc.getConnection()).findAllCourse();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return al;
	}

	@Override
	public boolean isExist(Courseware courseware) {
		boolean flag=false;
		try {
			flag=DaoFactory.getIAdminDaoInstance(dbc.getConnection()).isExist(courseware);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean addCourseware(Courseware courseware) {
		boolean flag=false;
		try {
			flag=DaoFactory.getIAdminDaoInstance(dbc.getConnection()).doAddCourseware(courseware);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean removeCourseware(Courseware courseware) {
		try {
			if(DaoFactory.getIAdminDaoInstance(dbc.getConnection()).doRemoveCourseware(courseware))
				return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return false;
	}

	@Override
	public boolean updateCourseware(Courseware courseware) {
		boolean flag=false;
		try {
			flag=DaoFactory.getIAdminDaoInstance(dbc.getConnection()).doUpdateCourseware(courseware);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return flag;
	}
	
	@Override
	public ArrayList<Courseware> searchCourseware(String str, int type) {
		ArrayList<Courseware> al=null;
		try {
			al=DaoFactory.getIAdminDaoInstance(dbc.getConnection()).findCourseware(str, type);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return al;
	}

	@Override
	public ArrayList<Courseware> searchAllCourseware() {
		ArrayList<Courseware> al=null;
		try {
			al=DaoFactory.getIAdminDaoInstance(dbc.getConnection()).findAllCourseware();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return al;
	}

	@Override
	public boolean isExist(Video video) {
		boolean flag=false;
		try {
			flag=DaoFactory.getIAdminDaoInstance(dbc.getConnection()).isExist(video);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean addVideo(Video video) {
		boolean flag=false;
		try {
			flag=DaoFactory.getIAdminDaoInstance(dbc.getConnection()).doAddVideo(video);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public boolean removeVideo(Video video) {
		try {
			if(DaoFactory.getIAdminDaoInstance(dbc.getConnection()).doRemoveVideo(video))
				return true;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return false;
	}

	@Override
	public boolean updateVideo(Video video) {
		boolean flag=false;
		try {
			flag=DaoFactory.getIAdminDaoInstance(dbc.getConnection()).doUpdateVideo(video);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return flag;
	}

	@Override
	public ArrayList<Video> searchVideo(String str, int type) {
		ArrayList<Video> al=null;
		try {
			al=DaoFactory.getIAdminDaoInstance(dbc.getConnection()).findVideo(str, type);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return al;
	}

	@Override
	public ArrayList<Video> searchAllVideo() {
		ArrayList<Video> al=null;
		try {
			al=DaoFactory.getIAdminDaoInstance(dbc.getConnection()).findAllVideo();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbc.close();
		}
		return al;
	}
}
