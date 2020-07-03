package tool;
import java.io.IOException;
/**
 * 该类为打开exe文件工具类
 * @author zjx
 *
 */
public class OpenExeTool{
	/**
	 * 该方法为打开exe文件的静态方法
	 * @param path 要打开文件的路径
	 */
	public static void openExe(String path) {
		Runtime r=Runtime.getRuntime();
		Process p=null;
		try {
			p=r.exec("D:\\QQ影音\\QQPlayer.exe "+path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
