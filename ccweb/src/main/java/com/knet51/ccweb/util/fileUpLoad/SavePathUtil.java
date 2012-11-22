package com.knet51.ccweb.util.fileUpLoad;

import java.io.File;

public class SavePathUtil {
	public static String getPath(String fileName,Long id,String type){
		String path = "d:\\"+fileName+"\\"+id+"\\"+type;
		File f = new File(path);
		if(!f.exists()) {
			 f.mkdirs();
	    }
		return path;
	}
}
