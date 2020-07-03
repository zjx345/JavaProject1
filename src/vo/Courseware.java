package vo;
/**
 * 该类为课件实体类
 * @author zjx
 *
 */
public class Courseware {
	private String cid; // 课程号
	private String cnum; // 课件号
	private String kname; // 课件名
	private String cpath; // 课件路径
	private String size;  // 课件大小
	/**
	 * 该方法为无参构造方法
	 */
	public Courseware() {
		super();
	}
	/**
	 * 该方法带全部属性参数的构造方法
	 * @param cid 课程号
	 * @param cnum 课件号
	 * @param kname 课件名
	 * @param cpath 课件路径
	 * @param size 课件大小
	 */
	public Courseware(String cid, String cnum, String kname, String cpath, String size) {
		super();
		this.cid = cid;
		this.cnum = cnum;
		this.kname = kname;
		this.cpath = cpath;
		this.size = size;
	}
	/**
	 * 取到课程号
	 * @return 返回一个课程号字符串
	 */
	public String getCid() {
		return cid;
	}
	/**
	 * 设置课程号
	 * @param cid 课程号字符串参数
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}
	/**
	 * 取到课件号
	 * @return 返回一个课件号字符串
	 */
	public String getCnum() {
		return cnum;
	}
	/**
	 * 设置课件号
	 * @param cnum 课件号字符串参数
	 */
	public void setCnum(String cnum) {
		this.cnum = cnum;
	}
	/**
	 * 取到课件名
	 * @return 返回一个课件名字符串
	 */
	public String getKname() {
		return kname;
	}
	/**
	 * 设置课件名
	 * @param kname 课件名字符串参数
	 */
	public void setKname(String kname) {
		this.kname = kname;
	}
	/**
	 * 取到课件路径
	 * @return 返回一个课件路径字符串
	 */
	public String getCpath() {
		return cpath;
	}
	/**
	 * 设置课件路径
	 * @param cpath 课件路径字符串参数
	 */
	public void setCpath(String cpath) {
		this.cpath = cpath;
	}
	/**
	 * 取到课件大小
	 * @return 返回一个string类型的课件大小
	 */
	public String getSize() {
		return size;
	}
	/**
	 * 设置课件大小
	 * @param size String类型的课件大小属性
	 */
	public void setSize(String size) {
		this.size = size;
	}
	
	
}
