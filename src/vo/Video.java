package vo;

/**
 * 该类为视频实体类
 * @author zjx
 *
 */
public class Video {
	private String cid;   // 课件号
	private String vnum; // 视频号
	private String vname; // 视频名
	private String vpath; // 视频路径
	/**
	 * 该方法为无参构造方法
	 */
	public Video() {
		super();
	}
	/**
	 * 该方法为带全部参的构造方法
	 * @param cid 课程号字符串参数
	 * @param vnum 课件号字符串参数
	 * @param vname 课件名字符串参数
	 * @param vpath 课件路径字符串参数
	 */
	public Video(String cid, String vnum, String vname, String vpath) {
		super();
		this.cid = cid;
		this.vnum = vnum;
		this.vname = vname;
		this.vpath = vpath;
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
	 * 取到视频号
	 * @return 返回一个视频号字符串
	 */
	public String getVnum() {
		return vnum;
	}
	/**
	 * 设置视频号
	 * @param vnum 视频号字符串参数
	 */
	public void setVnum(String vnum) {
		this.vnum = vnum;
	}
	/**
	 * 取到视频名
	 * @return 返回一个视频名字符串
	 */
	public String getVname() {
		return vname;
	}
	/**
	 * 设置视频名
	 * @param vname 视频名字符串参数
	 */
	public void setVname(String vname) {
		this.vname = vname;
	}
	/**
	 * 取到视频路径字符串
	 * @return 返回一个视频路径字符串
	 */
	public String getVpath() {
		return vpath;
	}
	/**
	 * 设置视频路径
	 * @param vpath 视频路径字符串参数
	 */
	public void setVpath(String vpath) {
		this.vpath = vpath;
	}
	
}
