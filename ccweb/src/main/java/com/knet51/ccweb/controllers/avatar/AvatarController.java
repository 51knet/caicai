package com.knet51.ccweb.controllers.avatar;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Handles requests for the avatar upload and cut
 */
@Controller
public class AvatarController {

	private static final Logger logger = LoggerFactory.getLogger(AvatarController.class);
	public static final long MAX_FILE_SIZE_1M = 1024*1024;
	
	@RequestMapping(value = "/avatar/crossdomain.xml")
	public @ResponseBody String renderCrossdomainXML() {
		StringBuilder builder = new StringBuilder();
		builder.append("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		builder.append("<!DOCTYPE cross-domain-policy SYSTEM \"http://www.macromedia.com/xml/dtds/cross-domain-policy.dtd\">");
		builder.append("<cross-domain-policy>");
		builder.append("   <site-control permitted-cross-domain-policies=\"all\" />");
		builder.append("   <allow-access-from domain=\"*\" to-ports=\"*\" />");
		builder.append("</cross-domain-policy>");
		
		return builder.toString();
	}
	@RequestMapping(value="/avatar/{user_id}", params="a=rectavatar", method= RequestMethod.POST) 
	public @ResponseBody String rectAvatar(@PathVariable Long user_id,@RequestParam("a") String action,  Locale locale, Model model, HttpSession session, HttpServletRequest request) {
		logger.info("处理缩略图...");
		String avatar1 = request.getParameter("avatar1");//大
		String avatar2 = request.getParameter("avatar2");//中
		String avatar3 = request.getParameter("avatar3");//小
		String savePath = session.getServletContext().getRealPath("/") + "/resources/attached/" + user_id +"/";
		
		if(saveFile(savePath + "big.jpg", getFlashDataDecode(avatar1))){
			return "<?xml version=\"1.0\" ?><root><face success=\"0\"/></root>";
		}else{
			return "<?xml version=\"1.0\" ?><root><face success=\"1\"/></root>";
		}
	}
	/**
	 *
	 */
	@RequestMapping(value = "/avatar/{user_id}", params="a=uploadavatar", method = RequestMethod.POST)
	public @ResponseBody String uploadFile(@PathVariable Long user_id,  Locale locale, Model model, HttpSession session, MultipartHttpServletRequest request) {
		logger.info("Welcome home! the client locale is " + locale.toString());
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		//文件保存目录路径
		String savePath = session.getServletContext().getRealPath("/") + "/resources/attached/" + user_id +"/";
		logger.info("savePath " + savePath);
		//文件保存目录URL
		String saveUrl  = request.getContextPath() + "/resources/attached/" + user_id +"/";

		if(!ServletFileUpload.isMultipartContent(request)){
//			out.println(getError("请选择文件。"));
			return "请选择文件。";
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(uploadDir.mkdirs() && (!uploadDir.isDirectory())){
//			out.println(getError("上传目录不存在。"));
			return "上传目录不存在。";
		}
		
		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		
		//创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
		//if("uploadavatar".equals(action)){//上传图片,可以自己实现
			MultiValueMap<String, MultipartFile> map = request.getMultiFileMap();
			logger.debug("map.size = "+map.size());
			Iterator<String> keyitr = map.keySet().iterator();
			while(keyitr.hasNext()) {
				String key = keyitr.next();
				logger.debug("key = " + key);
				List<MultipartFile> list = map.get(key);
				for (MultipartFile multipartFile : list) {
					String fileName = multipartFile.getOriginalFilename();
					long fileSize = multipartFile.getSize();
					
					logger.debug(fileName+":"+fileSize);
					//检查文件大小
					if(fileSize > MAX_FILE_SIZE_1M){
						//out.println(getError("上传文件大小超过限制。"));
						return "上传文件大小超过1M限制。";
					}
				
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + "png";
					try{
						File uploadedFile = new File(savePath, newFileName);
						multipartFile.transferTo(uploadedFile);
					}catch(Exception e){
						//out.println(getError("上传文件失败。"));
						return "上传文件失败。";
					}
					logger.info(saveUrl + newFileName);
					return  basePath + saveUrl + newFileName;
				}
			}
			
			
			
	
		return "上传文件失败。没有上传内容。";
		
		
	}
	
	private String getFileExt(String fileName) {
	    // 下面取到的扩展名错误，只有三位，而如html的文件则有四位
	    // extName = fileName.substring(fileName.length() - 3, fileName.length()); //扩展名
	    int dotindex = fileName.lastIndexOf(".");
	    String extName = fileName.substring(dotindex, fileName.length());
	    extName = extName.toLowerCase(); //置为小写
	    return extName;
	}
	private byte[] getFlashDataDecode(String src)
	{
		char []s=src.toCharArray();
		int len=s.length;
	    byte[] r = new byte[len / 2];
	    for (int i = 0; i < len; i = i + 2)
	    {
	        int k1 = s[i] - 48;
	        k1 -= k1 > 9 ? 7 : 0;
	        int k2 = s[i + 1] - 48;
	        k2 -= k2 > 9 ? 7 : 0;
	        r[i / 2] = (byte)(k1 << 4 | k2);
	    }
	    return r;
	}
	private boolean saveFile(String path,byte[]b){
		try{
			FileOutputStream fs = new FileOutputStream(path);
		    fs.write(b, 0, b.length);
		    fs.close();
			return false;
		}catch(Exception e){
		    return true;
		}
	}
}
