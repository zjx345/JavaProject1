package tool;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import thread.DownLoadThread;
/**
 * 该类为下载文件工具类
 * @author zjx
 *
 */
public class DownLoadTool {
	
	// 线程数量
	private static final int ThreadCount=4;
	/**
	 * 该方法为下载文件的静态方法
	 * @param path 要下载的文件路径
	 * @return 返回一个boolean值，代表是否下载成功
	 * @throws Exception 抛出一个Exception异常
	 */
	public static boolean downLoadRrsource(String path) throws Exception {
		/*try {
			// String boundary = "*****";
			int startPosition=0;
			// 根据路径创建URL对象
			URL url=new URL(path);
			// 打开连接
			
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			 conn.setRequestMethod("GET");
			conn.setConnectTimeout(6000);
			conn.setRequestProperty("Range",  "bystes="+startPosition+"-");
			// conn.setRequestProperty("Connection", "Keep-Alive");
			// sconn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
			// 获取网络资源的大小
			int fileSize=conn.getContentLength();
			System.out.println(fileSize);
			// 获取每个线程需要处理的数据大小
			int threadSize=fileSize/ThreadCount+1;
			
			// 获取网络资源的文件名
			String  fileName=path.substring(path.lastIndexOf("/")+1);
			// 创建随机访问文件对象
			RandomAccessFile raFile=new RandomAccessFile("E:/DownLoad/"+fileName, "rw");
			// 设置文件的大小
			raFile.setLength(fileSize);
			
			// 循环启动下载线程
			for(int i=1;i<=ThreadCount;i++) {
				startPosition=(i-1)*threadSize;
				raFile.seek(startPosition);
				new Thread(new DownLoadThread(raFile,conn,threadSize)).start();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		CloseableHttpClient httpClient = null;
        InputStream inputStream = null;
        File file = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpGet method = new HttpGet(path);
            // 执行请求，得到一个response
            HttpResponse result = httpClient.execute(method);
            inputStream = result.getEntity().getContent();
            // System.out.println(result.getAllHeaders().toString());
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = readInputStream(inputStream);
            //new一个文件对象用来保存图片，默认保存当前工程根目录
            String  fileName=path.substring(path.lastIndexOf("/")+1);
            File newFile = new File("E:/download/"+fileName);
            
            //创建输出流
            FileOutputStream outStream = new FileOutputStream(newFile);
            //写入数据
            outStream.write(data);
            //关闭输出流
            outStream.close();
        } catch (IOException e) {
        	return false;
		}
		return true; 
    }
	/**
	 * 该方法为读写文件的静态方法
	 * @param inStream 字节输入流
	 * @return 返回一个数据字节
	 * @throws Exception 抛出一个Exception异常
	 */
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

	}

