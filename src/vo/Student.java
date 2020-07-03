package vo;
/**
 * 该类为学生类
 * @author zjx
 *
 */
public class Student {
	private String account;  // 账号
	private String password; // 密码
	private String name;  // 学生姓名
	private String school; // 学校名称
	private String grade; // 年级
	private String phone; // 手机号码
	
	public Student() {
		
	}
	/**
	 * 该方法是带有全部属性的构造方法
	 * @param account 账号
	 * @param password 密码
	 * @param name 姓名
	 * @param school 学校
	 * @param grade 年级
	 * @param phone 手机号
	 */
	public Student(String account, String password, String name, String school, String grade, String phone) {
		super();
		this.account = account;
		this.password = password;
		this.name = name;
		this.school = school;
		this.grade = grade;
		this.phone = phone;
	}
	
	/**
	 * 取到用户名属性
	 * @return 返回一个账号字符串
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * 设置用户名属性
	 * @param account 账号字符串参数
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 取到密码属性
	 * @return 返回一个密码字符串
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置密码属性
	 * @param password 密码字符串参数
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 取到姓名属性
	 * @return 返回一个姓名字符串
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置姓名属性
	 * @param name 姓名字符串参数
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 取到学校属性
	 * @return 返回一个学校字符串
	 */
	public String getSchool() {
		return school;
	}
	/**
	 * 设置学校
	 * @param school 学校字符串参数
	 */
	public void setSchool(String school) {
		this.school = school;
	}
	/**
	 * 取到年级属性
	 * @return 返回一个年级字符串
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * 设置年级
	 * @param grade 年级字符串参数
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * 取到手机号码
	 * @return 返回一个手机号码字符串
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置手机号码
	 * @param phone 手机号字符串参数
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return this.account+" "+this.password+" "+this.name+" "+this.school+" "+this.grade+" "+this.phone;
	}
}
