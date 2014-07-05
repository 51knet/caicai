package com.graphene.web.common.avatar;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.graphene.web.common.beans.UserInfo;
import com.graphene.web.common.defs.GlobalDefs;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.service.UserService;



/**
 * Handles requests for the avatar upload and cut
 */
@Controller
public class AvatarController {

	private static final Logger logger = LoggerFactory.getLogger(AvatarController.class);
	public static final long MAX_FILE_SIZE_1M = 1024*1024;
	@Autowired
	private UserService userService;
	
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
	/**
	 * 此方法用来切图
	 * 
	 * @param user_id
	 * @param action
	 * @param locale
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/avatar/{user_id}", params="a=rectavatar", method= RequestMethod.POST) 
	public @ResponseBody String rectAvatar(@PathVariable Long user_id,@RequestParam("a") String action,  Locale locale, Model model, HttpSession session, HttpServletRequest request) {
		logger.info("处理缩略图...");
		String avatar1 = request.getParameter("avatar1");//大
		String avatar2 = request.getParameter("avatar2");//中
		String avatar3 = request.getParameter("avatar3");//小
		String savePath = session.getServletContext().getRealPath("/") + "/resources/attached/" + user_id +"/";
		boolean b1 = saveFile(savePath + "avatar_large.jpg", getFlashDataDecode(avatar1));
		boolean b2 = saveFile(savePath + "avatar_middle.jpg", getFlashDataDecode(avatar2));
		boolean b3 = saveFile(savePath + "avatar_small.jpg", getFlashDataDecode(avatar3));
		// <img src="/resources/attached/${user_id}/avatar_large.jpg">
		if(b1 && b2 && b3){
			User user = userService.updateUserAvatar(user_id, "/resources/attached/"+user_id+"/avatar_large.jpg");
			logger.debug(user.toString());
			UserInfo sessionUser = (UserInfo)session.getAttribute(GlobalDefs.SESSION_USER_INFO);
			sessionUser.setUser(user);
			return "<?xml version=\"1.0\" ?><root><face success=\"1\"/></root>";
		}else{
			return "<?xml version=\"1.0\" ?><root><face success=\"0\"/></root>";
		}
	}
	/**
	 * 此方法用来处理falsh上传的源文件
	 * 
	 * @param user_id
	 * @param locale
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/avatar/{user_id}", params="a=uploadavatar", method = RequestMethod.POST)
	public @ResponseBody String uploadFile(@PathVariable Long user_id,  Locale locale, Model model, HttpSession session, MultipartHttpServletRequest request) {
		logger.info("Welcome home! the client locale is " + locale.toString());
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		String savePath = session.getServletContext().getRealPath("/") + "/resources/attached/" + user_id +"/";
		String saveUrl = request.getContextPath() + "/resources/attached/" + user_id +"/";
		AvatarUploadDirInfo avatarUploadDirInfo = new AvatarUploadDirInfo(savePath, saveUrl);
		createAvatarUploadDirHierarchy(request, avatarUploadDirInfo);

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
					return "上传文件大小超过1M限制。";
				}
			
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + getFileExt(fileName);
				try{
					File uploadedFile = new File(avatarUploadDirInfo.getSavePath(), newFileName);
					multipartFile.transferTo(uploadedFile);
				}catch(Exception e){
					return "上传文件失败。";
				}
				String fullUrlPath = basePath + avatarUploadDirInfo.getSaveUrl() + newFileName;
				logger.info("fullUrlPath=" + fullUrlPath);
				return fullUrlPath;
			}
		}
		return "上传文件失败。没有上传内容。";
	}
	
	private AvatarUploadDirInfo createAvatarUploadDirHierarchy(HttpServletRequest request, AvatarUploadDirInfo avatarUploadDirInfo) {
		//检查并创建目录
		mkdirIfNotExist(avatarUploadDirInfo.savePath);
		String dirTmp = request.getParameter("dir");
		String dirName = (dirTmp==null) ? "image" : dirTmp;
		//创建文件夹
		avatarUploadDirInfo.savePath += dirName + "/";
		avatarUploadDirInfo.saveUrl += dirName + "/";
		mkdirIfNotExist(avatarUploadDirInfo.savePath);
		//创建日期文件夹
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		avatarUploadDirInfo.savePath += ymd + "/";
		avatarUploadDirInfo.saveUrl += ymd + "/";
		mkdirIfNotExist(avatarUploadDirInfo.savePath);
		
		return avatarUploadDirInfo;
	}
	
	private void mkdirIfNotExist(String savePath) {
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
	}
	
	private String getFileExt(String fileName) {
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
			return true;
		}catch(Exception e){
		    return false;
		}
	}

	class AvatarUploadDirInfo {
		private String savePath;
		private String saveUrl;
		
		/**
		 * 
		 * @param savePath 文件保存目录路径
		 * @param saveUrl 文件保存目录URL
		 */
		public AvatarUploadDirInfo(String savePath, String saveUrl) {
			super();
			this.savePath = savePath;
			this.saveUrl = saveUrl;
		}
		
		public String getSaveUrl() {
			return saveUrl;
		}
		public void setSaveUrl(String saveUrl) {
			this.saveUrl = saveUrl;
		}
		public String getSavePath() {
			return savePath;
		}
		public void setSavePath(String savePath) {
			this.savePath = savePath;
		}
		@Override
		public String toString() {
			return "AvatarUploadDirInfo [savePath=" + savePath + ", saveUrl="
					+ saveUrl + "]";
		}
	
	}
	

	
}
