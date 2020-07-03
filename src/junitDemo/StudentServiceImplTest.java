package junitDemo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import service.impl.StudentServiceImpl;
import vo.Student;

class StudentServiceImplTest {

	@Test
	void testUpdatePassword() {
		try {
			StudentServiceImpl ssl=new StudentServiceImpl();
			Student stu1=new Student("123","123","钟佳兴","吉首大学","大二","17347516760");
			Student stu2=new Student("12345","123","钟佳兴","吉首大学","大二","17347516760");
			TestCase.assertEquals(ssl.updatePassword(stu1, "1234"),true);
			TestCase.assertEquals(ssl.updatePassword(stu2, "1234"),false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testRegister() {
		try {
			StudentServiceImpl ssl=new StudentServiceImpl();
			Student stu1=new Student("123","123","钟佳兴","吉首大学","大二","17347516760");
			Student stu2=new Student("12345","123","钟佳兴","吉首大学","大二","17347516760");
			TestCase.assertEquals(ssl.register(stu1),false);
			TestCase.assertEquals(ssl.register(stu2),true);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testLogin() {
		try {
			StudentServiceImpl ssl=new StudentServiceImpl();
			Student stu1=new Student("123","123","钟佳兴","吉首大学","大二","17347516760");
			Student stu2=new Student("zjxzjx","123","钟佳兴","吉首大学","大二","17347516760");
			TestCase.assertEquals(ssl.register(stu1),true);
			TestCase.assertEquals(ssl.register(stu2),false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testFindCourse() {
		try {
			StudentServiceImpl ssl=new StudentServiceImpl();
			TestCase.assertEquals(ssl.findCourse("java"),null);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testDownloadCourseware() {
		try {
			StudentServiceImpl ssl=new StudentServiceImpl();
			TestCase.assertEquals(ssl.downloadCourseware("djasjfkdf"),false);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
