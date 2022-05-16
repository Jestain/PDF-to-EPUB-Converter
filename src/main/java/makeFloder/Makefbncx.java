package makeFloder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.Format;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.XMLWriter;

public class Makefbncx {
	public void general(String rootPath,String epubName,int pages) {
		File fbncx=new File(rootPath+"/OPS/fb.ncx");
		File Ops=new File(rootPath+"/OPS");
		if(!Ops.exists()) {
			Ops.mkdirs();
			}
//		 Document xmlDocument = DocumentHelper.createDocument();

		    //将内存中的对象Document写入硬盘，形成一个xml文件

//		    XMLWriter xmlWriter;
//			try {
//				xmlWriter = new XMLWriter(new FileOutputStream(new File(rootPath+"/OPS/fb2.xml")));
//				xmlWriter.write(xmlDocument);
//
//			    xmlWriter.close();
//			} catch (UnsupportedEncodingException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			} catch (FileNotFoundException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

		    

	

	    

	    
		

		
		
		
		try {
//			BufferedWriter writer;



		   

		   

	

			
			
			StringBuffer str=new StringBuffer("<!DOCTYPE ncx\r\n"
					+ "PUBLIC \"-//NISO//DTD ncx 2005-1//EN\" \"http://www.daisy.org/z3986/2005/ncx-2005-1.dtd\">\r\n"
					+ "<ncx xmlns=\"http://www.daisy.org/z3986/2005/ncx/\" version=\"2005-1\">\r\n"
					+ "<head>\r\n"
					+ "<meta name=\"dtb:uid\" content=\"123456\"/>\r\n"
					+ "<meta name=\"dtb:depth\" content=\"1\"/>\r\n"
					+ "<meta name=\"dtb:totalPageCount\" content=\"0\"/>\r\n"
					+ "<meta name=\"dtb:maxPageNumber\" content=\"0\"/>\r\n"
					+ "</head>\r\n");
		
			str.append("<docTitle>\r\n"
					+ "<text>"+epubName+"</text>\r\n"
					+ "</docTitle>\r\n"
					+ "<navMap>\r\n");
			Format fm=new DecimalFormat("000");
			for(int i=1;i<=pages;i++) {
				str.append("<navPoint id=\"navpoint-"+i+"\" playOrder=\""+i+"\">\r\n"
						+ "<navLabel>\r\n"
						+ "<text>page-"+fm.format(i)+"</text>\r\n"
						+ "</navLabel>\r\n"
						+ "<content src=\"page-"+fm.format(i)+".html\"/>\r\n"
						+ "</navPoint>\r\n");
			}
				str.append("</navMap>\r\n"
						+ "</ncx>");
				Document xmlDocument2 = DocumentHelper.parseText(new String(str));
				 XMLWriter xmlWriter2 = new XMLWriter(new FileOutputStream(new File(rootPath+"/OPS/fb2.xml")));
				xmlWriter2.write(xmlDocument2);
			    xmlWriter2.close();
			
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File oldname=new File(rootPath+"/OPS/fb2.xml");
	    oldname.renameTo(fbncx);
		
	}
}

