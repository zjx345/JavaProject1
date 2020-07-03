package vo;
/**
 * 该类为课程类
 * @author zjx
 *
 */
public class Course{
	private String cno;  // 课程号
	private String cname;  // 课程名
	private String presentation; // 课程简介
	private String syllabus;   // 教学大纲
	private String plan; // 授课计划
	/**
	 * 无参构造
	 */
	public Course() {}
	/**
	 * 该方法是带有全部属性的构造方法
	 * @param cno 课程号
	 * @param cname 课程名
	 * @param presentation 课程简介
 	 * @param syllabus 教学大纲
	 * @param plan 授课计划
	 */
	public Course(String cno, String cname, String presentation, String syllabus, String plan) {
		super();
		this.cno = cno;
		this.cname = cname;
		this.presentation = presentation;
		this.syllabus = syllabus;
		this.plan = plan;
	}
	/**
	 * 取到课程的课程号
	 * @return 返回一个课程号字符串
	 */
	public String getCno() {
		return cno;
	}
	/**
	 * 设置课程号
	 * @param cno 课程号字符串参数
	 */
	public void setCno(String cno) {
		this.cno = cno;
	}
	/**
	 * 取到课程名
	 * @return 返回一个课程名字符串
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * 设置课程名
	 * @param cname 课程名字符串参数
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * 取到课程简介
	 * @return 返回一个课程简介字符串
	 */
	public String getPresentation() {
		return presentation;
	}
	/**
	 * 设置课程简介
	 * @param presentation 课程简介字符串参数
	 */
	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}
	/**
	 * 取到教学大纲
	 * @return 返回一个教学大纲字符串
	 */
	public String getSyllabus() {
		return syllabus;
	}
	/**
	 * 设置教学大纲
	 * @param syllabus 教学大纲字符串参数
	 */
	public void setSyllabus(String syllabus) {
		this.syllabus = syllabus;
	}
	/**
	 * 取到授课计划
	 * @return 返回一个授课计划字符串
	 */
	public String getPlan() {
		return plan;
	}
	/**
	 * 设置授课计划
	 * @param plan 授课计划字符串参数
	 */
	public void setPlan(String plan) {
		this.plan = plan;
	}
}
