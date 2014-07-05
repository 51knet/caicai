package com.graphene.web.util.fileUpload;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FileUtil {
	
	private static FileUtil INSTANCE = null ;
	
	public static synchronized FileUtil getInstance(){
		if(INSTANCE == null){
			return INSTANCE = new FileUtil();
		}
		return INSTANCE;
	}
	
	
	public String saveFile(InputStream inputStream,String fileOriginalName,String realPath) throws Exception{
		
		String fileName = fileOriginalName;
		FileOutputStream outputStream = new FileOutputStream(realPath+"\\"+fileName);
		int count = 0;
		byte[] b = new byte[1024*8];
		while((count=inputStream.read(b))!=-1){
			outputStream.write(b, 0, count);
			outputStream.flush();
		}
		outputStream.close();
		inputStream.close();
		return fileName;
	}
	
	public  void createRealPath(String realPath,HttpSession session){
		File f = new File(realPath);
		if(!f.exists()) {
			 f.mkdirs();
	    }
	}
	
	public  String getCoursePath(String saveFileName,Long user_id,String type,String lessonNum,HttpSession session){
		String realPath = session.getServletContext().getRealPath("/")+"/resources/attached/"+user_id+"/"+saveFileName+"/"+type+"/"+lessonNum;
		File f = new File(realPath);
		if(!f.exists()) {
			 f.mkdirs();
	    }
		return realPath;
	}
	
	public String getSavePath(String saveFileName,Long user_id,String type,HttpServletRequest request){
		String savePath = "/resources/attached/"+user_id+"/"+saveFileName+"/"+type;
		return savePath;
	}
	
	public void getPreviewImage(File sourceImage,File destImage,String format, int WIDTH, int HEIGHT) throws Exception{
		BufferedImage simage = ImageIO.read(sourceImage);
		int x = simage.getWidth();
		int y = simage.getHeight();
		int x1 = WIDTH;
		int y1 = HEIGHT;
		if (WIDTH*y<HEIGHT*x) {
			y1 = WIDTH*y/x;
		}
		if (WIDTH*y>HEIGHT*x) {
			x1=HEIGHT*x/y;
		}
		BufferedImage newimage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.SCALE_SMOOTH);
		Graphics2D  g = newimage.createGraphics();
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		Image  image = simage.getScaledInstance(x1, y1, BufferedImage.SCALE_SMOOTH);
		g.drawImage(image, (WIDTH-x1)/2, (HEIGHT-y1)/2, null);
		ImageIO.write(newimage, format, destImage);
		sourceImage.delete();
	}
	
	public void downLoad(HttpServletRequest request,HttpServletResponse response,String savePath,String fileName) throws Exception{
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			Long fileLength = new File(savePath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-Disposition", "attachement; filename="+new String(fileName.getBytes("GBK"),"ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(savePath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] b = new byte[2048];
			int temp = 0;
			while((temp = bis.read(b, 0, b.length))!=-1 ){
				bos.write(b, 0, temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(bos!=null){
				bos.close();
			}
			if(bis !=null){
				bis.close();
			}
		}
	}
	
}
