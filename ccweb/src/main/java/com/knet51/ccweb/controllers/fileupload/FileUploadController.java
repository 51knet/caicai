package com.knet51.ccweb.controllers.fileupload;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
	/**
	 * TODO: each user can only see his upload folder, they have their own root path
	 */
	@RequestMapping(value = "/file_upload", method = RequestMethod.POST)
	public @ResponseBody FileUploadInfo uploadFile(Locale locale, Model model, HttpSession session, MultipartHttpServletRequest request) {
		logger.info("Welcome home! the client locale is " + locale.toString());
		
		//文件保存目录路径
		String savePath = session.getServletContext().getRealPath("/") + "/resources/attached/";
		logger.info("savePath " + savePath);
		//文件保存目录URL
		String saveUrl  = request.getContextPath() + "/resources/attached/";

		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		//最大文件大小
		long maxSize = 1000000;
		if(!ServletFileUpload.isMultipartContent(request)){
//			out.println(getError("请选择文件。"));
			return FileUploadInfo.createFailedFileUploadInfo("请选择文件。");
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
//			out.println(getError("上传目录不存在。"));
			return FileUploadInfo.createFailedFileUploadInfo("上传目录不存在。");
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
//			out.println(getError("上传目录没有写权限。"));
			return FileUploadInfo.createFailedFileUploadInfo("上传目录没有写权限。");
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if(!extMap.containsKey(dirName)){
//			out.println(getError("目录名不正确。"));
			return FileUploadInfo.createFailedFileUploadInfo("目录名不正确。");
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
				if(fileSize > maxSize){
					//out.println(getError("上传文件大小超过限制。"));
					return FileUploadInfo.createFailedFileUploadInfo("上传文件大小超过限制。");
				}
				//检查扩展名
				String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
				if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
					//out.println(getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。"));
					return FileUploadInfo.createFailedFileUploadInfo("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
				try{
					File uploadedFile = new File(savePath, newFileName);
					multipartFile.transferTo(uploadedFile);
				}catch(Exception e){
					//out.println(getError("上传文件失败。"));
					return FileUploadInfo.createFailedFileUploadInfo("上传文件失败。");
				}
				logger.info(saveUrl + newFileName);
				return FileUploadInfo.createSucceedFileUploadInfo( saveUrl + newFileName);
			}
		}
		
		return FileUploadInfo.createFailedFileUploadInfo("上传文件失败。没有上传内容。");
	}
}
