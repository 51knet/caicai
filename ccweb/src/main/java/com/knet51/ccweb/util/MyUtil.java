package com.knet51.ccweb.util;

public class MyUtil {
	
	public static int getPageNumber(int pageNum, int listCount ,int pageSize){
		int maxPage = listCount % pageSize == 0? listCount/pageSize-1 : listCount/pageSize ;
		//int res1 = listCount/pageSize;
		//int res2 = listCount/pageSize-1;
		//System.out.println("======="+maxPage+"========="+res1+"-------"+res2+"---"+listCount);
		if(pageNum <=0){
			return 0;
		}else if(pageNum >= maxPage){
			return maxPage;
		}
		return pageNum;
	}
}
