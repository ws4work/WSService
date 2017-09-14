package personal.ws.myservice.sniper.baidu.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jsoup.nodes.Element;

import personal.ws.myservice.sniper.baidu.config.Configuration;

public class LoopSpiderService {

	public void img_download(Element imgEle) {
		String imgsrc = imgEle.attr("src");
		if (imgsrc.startsWith("http://static.tieba.baidu.com") || imgsrc.startsWith("http://tb2.bdstatic.com/tb/editor")) {
			return;
		}
		System.out.println("下载图片:" + imgsrc);
		String filename = findFileName(imgsrc);
		//下载图片资源
		ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10, false);
		ThreadPoolExecutor.AbortPolicy handler = new ThreadPoolExecutor.AbortPolicy();
		ThreadPoolExecutor ex = new ThreadPoolExecutor(10, 30, 5000, TimeUnit.SECONDS, workQueue, handler);
		ex.execute(new Runnable() {

			@Override
			public void run() {
				try {
					download(new URL(imgsrc), filename);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 查找资源文件名
	 * 
	 * @param src
	 * @return
	 */
	public String findFileName(String src) {
		String[] urlsplit = src.split("/");
		int fileNameBeginIndex = src.lastIndexOf("/") + 1;
		int fileNameEndIndex = 0;
		String lastString = urlsplit[urlsplit.length - 1];
		if (lastString.contains("?")) {
			fileNameEndIndex = src.lastIndexOf("/") + lastString.indexOf("?") + 1;
		} else {
			fileNameEndIndex = src.length();
		}
		String filename = Configuration.DOWNLOAD_PATH + src.substring(fileNameBeginIndex, fileNameEndIndex);
		return filename;
	}

	/**
	 * 资源下载
	 * 
	 * @param url
	 */
	public static void download(URL url, String name) {
		try {
			createPath(name);
			URLConnection connection = url.openConnection();
			HttpURLConnection http = (HttpURLConnection) connection;
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			InputStream is = http.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			FileOutputStream fos = new FileOutputStream(name);
			int i;
			while ((i = is.read()) != -1) {
				fos.write(i);
			}
			fos.close();
			baos.close();
			is.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 构建路径
	 * 
	 * @param name
	 * @throws IOException
	 */
	public static void createPath(String name) throws IOException {
		String path = name.substring(0, name.lastIndexOf(File.separator));
		File file_path = new File(path);
		if (!file_path.exists()) {
			file_path.mkdirs();
		}
		File file = new File(name);
		if (!file.exists()) {
			file.createNewFile();
		}
	}

	/**
	 * 文本输出到txt
	 * 
	 * @param string
	 */
	public void toAppendFile(String string) {
		string += "\r\n";
		File file = new File(Configuration.DOWNLOAD_PATH + File.separator + Configuration.CONNECT_BARNAME + ".txt");
		try {
			FileWriter fw = new FileWriter(file, true);
			fw.write(string);
			fw.flush();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
