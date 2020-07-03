package vo;
/**
 * 该类为管理员类
 * @author zjx
 *
 */
public class Admin {
	private String account; // 账号
	private String password; // 密码
	
	/**
	 * 该方法为无参构造方法
	 */
	public Admin() {
		super();
	}
	/**
	 * 该方法为带有全部属性的构造方法
	 * @param account 账号
	 * @param password 密码
	 */
	public Admin(String account, String password) {
		super();
		this.account = account;
		this.password = password;
	}
	/**
	 * 取到账号
	 * @return 返回一个账号字符串
	 */
	public String getAccount() {
		return account;
	}
	/**
	 * 设置账号
	 * @param account 账号字符串参数
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 取到密码
	 * @return 返回一个密码字符串
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置密码
	 * @param password 密码字符串参数
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
}
