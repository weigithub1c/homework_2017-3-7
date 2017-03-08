package com.hand.HanSong;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Io {

	public static void main(String[] args) {

		String urlString = "http://www.madore.org/~david/math/padics.pdf";
		try {
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();
			
			InputStream is = conn.getInputStream();
			
			File file = new File("download_padics.pdf");
			if(file.exists()){
				
				System.out.println("文件已经存在！");
				
			}else{
				FileOutputStream fos = new FileOutputStream(file);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				
				int t = 0;
				byte b [] = new byte[1024];		
				while ( ( t = is.read(b)) != -1) {
					//读多少字节写入多少字节
					bos.write(b,0,t);		//b是读的数组；0是从第一个字节读取；直到数据读完后就不读了；
					bos.flush();	//强制读出，防止系统机制迟迟不读
				}
				
				bos.close();
				fos.close();
				is.close();
				
				System.out.println("--下载成功--");
			}
			
		}catch (MalformedURLException e) {
			System.out.println("Unable to connect to URL: " + urlString);
		} catch (IOException e) {
			System.out.println("IOException when connecting to URL: " + urlString);		
			
		}
	}

}
