package thread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
/**
 * 该类为下载线程类
 * @author Lenovo
 *
 */
public class DownLoadThread implements Runnable {
	
	// 目标文件
	private RandomAccessFile raFile;
	// 网络资源连接
	private HttpURLConnection conn;
	// 每个线程下载的大小
	private int threadSize;
	
	/**
	 * 该方法为带全部参数的构造方法
	 * @param raFile 写入的文件
	 * @param conn 网络连接
	 * @param threadSize 每个线程需要下载的大小
	 */
	public DownLoadThread(RandomAccessFile raFile, HttpURLConnection conn, int threadSize) {
		this.raFile = raFile;
		this.conn = conn;
		this.threadSize = threadSize;
	}


	@Override
	public void run() {
		try {
			// 封装数据源
			BufferedInputStream bis=new BufferedInputStream(conn.getInputStream());
			
			// 记录已写数据大小
			int readedSize=0;
			
			// 读写数据
			byte[] by=new byte[1024];
			int len=0;
			while((len=bis.read(by))!=-1 && readedSize<threadSize) {
				raFile.write(by,0,len);
				readedSize+=len;
			}
			
			// 释放资源
			raFile.close();
			bis.close();
			conn.disconnect();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
