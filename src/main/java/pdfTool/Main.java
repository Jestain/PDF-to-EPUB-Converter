package pdfTool;

import java.io.File;
import makeFloder.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import org.apache.pdfbox.pdmodel.PDDocument;

import toHtml.MyPdfToHtml;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		MyThread myThread = new MyThread("D:/pdf_2/geography.pdf","D:/pdf_3/geography");
//		myThread.start();
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		myThread.pauseThread();
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		myThread.resumeThread();

//		MyThread thread1=new MyThread("D:/pdf_2/geography.pdf","D:/pdf_3/geography");
		
//		MyThread thread2=new MyThread("D:/pdf_2/关于地理信息系统未来发展的思考.pdf","D:/pdf_3/关于地理信息系统未来发展的思考");
//		MyThread thread3=new MyThread("D:/pdf_2/计算机体系结构试验指导书.pdf","D:/pdf_3/计算机体系结构试验指导书");
//		Thread t1=new Thread(thread1);
//		Thread t2=new Thread(thread2);
//		Thread t3=new Thread(thread3);
//		t1.start();
//		t2.start();
//		t3.start();
//		分割pdf,到my_subPdf文件夹中
//		try {
//			PDDocument document=PDDocument.load(new File("C:\\Users\\Jestain\\Desktop\\《软件工程导论》实验指导书.pdf"));
//			String subPdfPath="D:/pdf/my_subPdf";
//			PdfSplitter splitter=new PdfSplitter();
//			splitter.split(document, subPdfPath);
//			document.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		toFolder.get("D:\\pdf_2\\计算机体系结构试验指导书");
//		
//		
//		
//		String OPSPath="D:/pdf_2/计算机体系结构试验指导书/OPS";
//		File subPdfDir=new File("D:/pdf_2/my_subPdf");
//		
//		File[] subPdf=subPdfDir.listFiles();
////				按时间排序
//		Arrays.sort(subPdf, new Comparator<File>() {
//		    public int compare(File f1, File f2) {
//		        long diff = f1.lastModified() - f2.lastModified();
//		        if (diff > 0)
//		            return 1;
//		        else if (diff == 0)
//		            return 0;
//		        else
//		            return -1;//如果 if 中修改为 返回-1 同时此处修改为返回 1  排序就会是递减
//		    }
//
//		    public boolean equals(Object obj) {
//		        return true;
//		    }
//
//		});
//		int n=0;
//		Format fm=new DecimalFormat("000");
//		for(File subFile:subPdf) {
//			MyPdfToHtml.convert(subFile.getAbsolutePath(), OPSPath,"page-"+fm.format(++n) );
//		}
//		
////		生成fbopf,需要根路径（计算机体系结构试验指导书的路径），标题名，页数，构建器，日期	
//		Makefbopf fbopf=new Makefbopf();
//		fbopf.general("D:/pdf_2/计算机体系结构试验指导书","计算机体系结构试验指导书", n, "unknow", "unknow");
////		
////		
////		生成fbncx,需要根路径，标题名，页数
//		Makefbncx fbncx=new Makefbncx();
//		fbncx.general("D:/pdf_2/计算机体系结构试验指导书","计算机体系结构试验指导书", n);
////		
//		
//		
//		toZip zip=new toZip();
//		zip.general("D:\\pdf_2\\计算机体系结构试验指导书", "D:/pdf_2", "计算机体系结构试验指导书.zip");
//		System.out.println("pdf转epub文件成功");
//		
		
////		
////		
////		
////		用toGoodHtml
//		String htmlPath="D:/pdf/Solutions/OPS";
//		String htmlName=null;
//		
//		Format fm=new DecimalFormat("000");
//		
////		子pdf所在路径
//		File subPdfDir=new File("D:/pdf/my_subPdf");
//		File[] subPdf=subPdfDir.listFiles();
////				按时间排序
//		Arrays.sort(subPdf, new Comparator<File>() {
//		    public int compare(File f1, File f2) {
//		        long diff = f1.lastModified() - f2.lastModified();
//		        if (diff > 0)
//		            return 1;
//		        else if (diff == 0)
//		            return 0;
//		        else
//		            return -1;//如果 if 中修改为 返回-1 同时此处修改为返回 1  排序就会是递减
//		    }
//
//		    public boolean equals(Object obj) {
//		        return true;
//		    }
//
//		});
//		int n=0;
//		for(File subFile:subPdf) {
////			System.out.println(subFile.getAbsolutePath());
//		toGoodHtml pdftohtml=new toGoodHtml();
//		htmlName=new String("page-"+fm.format(++n)+".html");
////		System.out.println(subFile.getAbsolutePath());
//		pdftohtml.general(subFile.getAbsolutePath(), htmlPath,htmlName);
//		System.out.println("make"+subFile.getAbsolutePath());
//		}
////
//////		获取子pdf数目n
////		
////		
////
////		
////		生成fbopf,需要根路径（计算机体系结构试验指导书的路径），标题名，页数，构建器，日期	
//		Makefbopf fbopf=new Makefbopf();
//		fbopf.general("D:/pdf/Solutions","Solutions", n, "unknow", "unknow");
//		
//		
////		生成fbncx,需要根路径，标题名，页数
//		Makefbncx fbncx=new Makefbncx();
//		fbncx.general("D:/pdf/Solutions","Solutions", n);
//////		
//////		
//////		
//////		
//////		
////////		pdf转html
//////		try {
//////			
//////			String htmlPath="D:/pdf/计算机体系结构指导书/OPS";
//////			String htmlName=null;
//////			
//////			Format fm=new DecimalFormat("000");
//////			
//////			
//////			File subPdfDir=new File("D:/pdf/my_subPdf");
//////			File[] subPdf=subPdfDir.listFiles();
////////			按时间排序
//////			Arrays.sort(subPdf, new Comparator<File>() {
//////	            public int compare(File f1, File f2) {
//////	                long diff = f1.lastModified() - f2.lastModified();
//////	                if (diff > 0)
//////	                    return 1;
//////	                else if (diff == 0)
//////	                    return 0;
//////	                else
//////	                    return -1;//如果 if 中修改为 返回-1 同时此处修改为返回 1  排序就会是递减
//////	            }
//////
//////	            public boolean equals(Object obj) {
//////	                return true;
//////	            }
//////
//////	        });
//////			
//////			int n=0;
//////			for(File subFile:subPdf) {
//////				PDDocument document=PDDocument.load(subFile);
//////				PdfToHtml pdftohtml=new PdfToHtml();
//////				htmlName=new String("page-"+fm.format(++n)+".html");
//////				pdftohtml.convert(document, htmlPath,htmlName);
//////				
//////			}
////////			PDDocument document=PDDocument.load(new File("D:\\pdf\\my_subPdf\\subPdf11.pdf"));
////////			String htmlPath="D:/pdf/subHtml";
////////			String htmlName="subPdf11.html";
////////			PdfToHtml pdftohtml=new PdfToHtml();
////////			pdftohtml.convert(document, htmlPath,htmlName);
////////			System.out.println("success");
//////		} catch (IOException e) {
//////			// TODO Auto-generated catch block
//////			e.printStackTrace();
//////		}
////		
////
////		
////
////			
////			
////		
////		
////		
//////		Dirdelete deleter=new Dirdelete();
//////		deleter.delete("D:/pdf/my_subPdf");
//////		System.out.println("pdf转html成功");
////		
////		
////		
////		
//		Makemimetype mime=new Makemimetype();
//		mime.general("D:/pdf/Solutions");
//		
//		Makecontainer container=new Makecontainer();
//		container.general("D:/pdf/Solutions");
//		
//		
//		Makecss css=new Makecss();
//		css.general("D:/pdf/Solutions");
//		
//		MakeFonts fonts=new MakeFonts();
//		fonts.general("D:/pdf/Solutions");
//		
//		
//		Makeimages images=new Makeimages();
//		images.general("D:/pdf/Solutions");
//		
//		Dirdelete deleter=new Dirdelete();
//		deleter.delete("D:/pdf/my_subPdf");
		
		toZip zip=new toZip();
		zip.general("D:\\Eclipse\\Final\\version1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\PDF-to-EPUB-Converter\\WEB-INF\\downloadPath\\subPdf1","D:\\Eclipse\\Final\\version1\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\PDF-to-EPUB-Converter\\WEB-INF\\downloadPath/pdf2", "subpdf1.zip");
		System.out.println("pdf转epub文件成功");
		
	}















}
