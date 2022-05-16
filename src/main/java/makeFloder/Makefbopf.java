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

public class Makefbopf {
	public void general(String rootPath,String epubName,int pages,String contributor,String date) {
		File fbopf=new File(rootPath+"/OPS/fb.opf");
		File Ops=new File(rootPath+"/OPS");
		if(!Ops.exists()) {
			Ops.mkdirs();
			}
		
	    //将内存中的对象Document写入硬盘，形成一个xml文件

	
//
//		 Document xmlDocument = DocumentHelper.createDocument();
//
//		    //将内存中的对象Document写入硬盘，形成一个xml文件
//
//		    XMLWriter xmlWriter;
//			try {
//				xmlWriter = new XMLWriter(new FileOutputStream(new File(rootPath+"/OPS/fb.xml")));
//
//			    xmlWriter.write(xmlDocument);
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


	   
	    
//		if(!fbopf.exists()) {
//			if(!Ops.exists()) {
//				Ops.mkdirs();
//			}
//		try {
//			fbopf.createNewFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		}
//		BufferedWriter writer;

		
//		FileOutputStream fos;
		try {	
			 
			
//			旧的字符流方法
//			writer = new BufferedWriter(new FileWriter(oldname,true));
//			writer.write
			StringBuffer str=new StringBuffer("<package xmlns=\"http://www.idpf.org/2007/opf\" unique-identifier=\"EPB-UUID\" version=\"2.0\">\r\n"
					+ "<metadata xmlns:opf=\"http://www.idpf.org/2007/opf\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\">");
//			writer.flush();
			
//			writer.write
			str.append("\r\n<dc:title>"+"  "+"</dc:title>");
//			writer.flush();
//			writer.write
			str.append("\r\n<dc:creator></dc:creator>\r\n"
					+ "<dc:subject></dc:subject>\r\n"
					+ "<dc:description></dc:description>\r\n"
				);
//			writer.flush();
//			writer.write
			str.append("<dc:contributor>"+contributor+"</dc:contributor>\r\n"
					+ "<dc:date>"+date+"</dc:date>\r\n"
				);
//			writer.flush();
//			writer.write
			str.append("<dc:type></dc:type>\r\n"
					+ "<dc:format></dc:format>\r\n"
					+ "<dc:source></dc:source>\r\n"
					+ "<dc:relation></dc:relation>\r\n"
					+ "<dc:coverage></dc:coverage>\r\n"
					+ "<dc:rights></dc:rights>\r\n"
					+ "<dc:identifier id=\"EPB-UUID\">urn:uuid:123456</dc:identifier>\r\n"
					+ "<dc:language>en-gb</dc:language>\r\n"
					+ "</metadata>\r\n"
					+ "<manifest>\r\n"
					+ "<!-- Content Documents -->\r\n"
				);
			
			
//			
//			writer.flush();
			Format fm=new DecimalFormat("000");
			for(int i=1;i<=pages;i++) {
//				writer.write
				str.append("<item id=\"page-"+fm.format(i)+"\" href=\"page-"+fm.format(i)+".html\" media-type=\"application/xhtml+xml\"/>\r\n");
//				writer.flush();
			}
			
			File imgs=new File(rootPath+"/OPS/images");
			String []imagesName=imgs.list();
//			int images=imagesName.length;
			for(int i=1;i<=imagesName.length;i++) {
//				System.out.println(name.substring(0,name.length()-5));
//				writer.write
				str.append("<item id=\"img-"+fm.format(i)+"\" href=\"images/"+imagesName[i-1]+"\" media-type=\"image/jpeg\"/>\r\n");
//				writer.flush();
			}
//			
//			writer.write
			str.append("<item id=\"font\" href=\"Fonts/EmbedFont.ttf\" media-type=\"application/fonts\"/>\r\n"
					+ "<item id=\"style\" href=\"css/style.css\" media-type=\"text/css\"/>\r\n"
					+ "<!-- NCX -->"
					+ "<item id=\"ncx\" href=\"fb.ncx\" media-type=\"application/x-dtbncx+xml\"/>\r\n"
					+ "</manifest>\r\n"
					+ "<spine toc=\"ncx\">\r\n"
				);
//			writer.flush();
			
			for(int i=1;i<=pages;i++) {
//				writer.write
				str.append("<itemref idref=\"page-"+fm.format(i)+"\" linear=\"yes\"/>\r\n");
//				writer.flush();
			}
//			writer.write
			str.append("</spine>\r\n"
					+ "</package>"
				);
			Document xmlDocument2 = DocumentHelper.parseText(new String(str));
			XMLWriter xmlWriter2 = new XMLWriter(new FileOutputStream(new File(rootPath+"/OPS/fb.xml")));
			xmlWriter2.write(xmlDocument2);
				
			
			
//			writer.flush();
//			writer.close();
			xmlWriter2.close();
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File oldname=new File(rootPath+"/OPS/fb.xml");
		oldname.renameTo(fbopf);
		
	}
}
