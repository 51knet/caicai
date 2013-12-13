package com.knet51.ccweb.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Test {
	public static void main(String[] args) throws Exception {
//		try
//		{
//			//打开文件
//			WritableWorkbook book=
//			Workbook.createWorkbook(new File("e://测试.xls"));
//			//生成名为“第一页”的工作表，参数0表示这是第一页
//			WritableSheet sheet=book.createSheet("第一页",0);
//			//在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
//			//以及单元格内容为test
//			Label label=new Label(0,0,"test");
//			//将定义好的单元格添加到工作表中
//			sheet.addCell(label);
//			/*生成一个保存数字的单元格
//			必须使用Number的完整包路径，否则有语法歧义
//			单元格位置是第二列，第一行，值为789.123*/
//			jxl.write.Number number = new jxl.write.Number(1,0,789.123);
//			sheet.addCell(number);
//			//写入数据并关闭文件
//			book.write();
//			book.close();
//		}catch(Exception e){
//				System.out.println(e);
//		}
			StringBuffer sb = new StringBuffer("insert into patent ( patentNum, patentName, mainClassNum, classNum, applicant, inventer, publishDate, publishNum, agency, agent, applicationDate, address, summary, user_id, patentType_id, patentField ) values "); ;
			//StringBuffer bigSb = new StringBuffer();
			try{
				Workbook book=
				Workbook.getWorkbook(new File("e://book1.xls"));
				Sheet sheet=book.getSheet(0);		
				//System.out.println(sheet.getColumns());
				//System.out.println(sheet.getRows());
				for (int i = 1; i < sheet.getRows(); i++) {
					sb.append("(");
					Cell[] cs = sheet.getRow(i);
					for (int j = 0; j < cs.length; j++) {
						String c = cs[j].getContents();
						if(j == cs.length-1){
							sb.append(" '"+c+"' ");
						}else{
							sb.append(" '"+c+"', ");
						}
						
					}
					
					if(i==sheet.getRows()-1){
						sb.append(", '3' , '1' ,'工业工程' ) ;");
					}else{
						sb.append(", '3' , '1' ,'工业工程' ) ,");
					}
				}
				String text = sb.toString();
				File file = new File("e://sql.txt");
				if(!file.exists()){
					file.createNewFile();
				}
				FileWriter writer = new FileWriter(file);
				writer.write(text);
				writer.flush();
				writer.close();
				book.close();
			}catch(Exception e){
				System.out.println(e);
			}finally{
				System.out.println("done");
			}
			
	}
	
}
