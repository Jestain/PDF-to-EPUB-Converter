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
		
//		MyThread thread2=new MyThread("D:/pdf_2/���ڵ�����Ϣϵͳδ����չ��˼��.pdf","D:/pdf_3/���ڵ�����Ϣϵͳδ����չ��˼��");
//		MyThread thread3=new MyThread("D:/pdf_2/�������ϵ�ṹ����ָ����.pdf","D:/pdf_3/�������ϵ�ṹ����ָ����");
//		Thread t1=new Thread(thread1);
//		Thread t2=new Thread(thread2);
//		Thread t3=new Thread(thread3);
//		t1.start();
//		t2.start();
//		t3.start();
//		�ָ�pdf,��my_subPdf�ļ�����
//		try {
//			PDDocument document=PDDocument.load(new File("C:\\Users\\Jestain\\Desktop\\��������̵��ۡ�ʵ��ָ����.pdf"));
//			String subPdfPath="D:/pdf/my_subPdf";
//			PdfSplitter splitter=new PdfSplitter();
//			splitter.split(document, subPdfPath);
//			document.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		toFolder.get("D:\\pdf_2\\�������ϵ�ṹ����ָ����");
//		
//		
//		
//		String OPSPath="D:/pdf_2/�������ϵ�ṹ����ָ����/OPS";
//		File subPdfDir=new File("D:/pdf_2/my_subPdf");
//		
//		File[] subPdf=subPdfDir.listFiles();
////				��ʱ������
//		Arrays.sort(subPdf, new Comparator<File>() {
//		    public int compare(File f1, File f2) {
//		        long diff = f1.lastModified() - f2.lastModified();
//		        if (diff > 0)
//		            return 1;
//		        else if (diff == 0)
//		            return 0;
//		        else
//		            return -1;//��� if ���޸�Ϊ ����-1 ͬʱ�˴��޸�Ϊ���� 1  ����ͻ��ǵݼ�
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
////		����fbopf,��Ҫ��·�����������ϵ�ṹ����ָ�����·��������������ҳ����������������	
//		Makefbopf fbopf=new Makefbopf();
//		fbopf.general("D:/pdf_2/�������ϵ�ṹ����ָ����","�������ϵ�ṹ����ָ����", n, "unknow", "unknow");
////		
////		
////		����fbncx,��Ҫ��·������������ҳ��
//		Makefbncx fbncx=new Makefbncx();
//		fbncx.general("D:/pdf_2/�������ϵ�ṹ����ָ����","�������ϵ�ṹ����ָ����", n);
////		
//		
//		
//		toZip zip=new toZip();
//		zip.general("D:\\pdf_2\\�������ϵ�ṹ����ָ����", "D:/pdf_2", "�������ϵ�ṹ����ָ����.zip");
//		System.out.println("pdfתepub�ļ��ɹ�");
//		
		
////		
////		
////		
////		��toGoodHtml
//		String htmlPath="D:/pdf/Solutions/OPS";
//		String htmlName=null;
//		
//		Format fm=new DecimalFormat("000");
//		
////		��pdf����·��
//		File subPdfDir=new File("D:/pdf/my_subPdf");
//		File[] subPdf=subPdfDir.listFiles();
////				��ʱ������
//		Arrays.sort(subPdf, new Comparator<File>() {
//		    public int compare(File f1, File f2) {
//		        long diff = f1.lastModified() - f2.lastModified();
//		        if (diff > 0)
//		            return 1;
//		        else if (diff == 0)
//		            return 0;
//		        else
//		            return -1;//��� if ���޸�Ϊ ����-1 ͬʱ�˴��޸�Ϊ���� 1  ����ͻ��ǵݼ�
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
//////		��ȡ��pdf��Ŀn
////		
////		
////
////		
////		����fbopf,��Ҫ��·�����������ϵ�ṹ����ָ�����·��������������ҳ����������������	
//		Makefbopf fbopf=new Makefbopf();
//		fbopf.general("D:/pdf/Solutions","Solutions", n, "unknow", "unknow");
//		
//		
////		����fbncx,��Ҫ��·������������ҳ��
//		Makefbncx fbncx=new Makefbncx();
//		fbncx.general("D:/pdf/Solutions","Solutions", n);
//////		
//////		
//////		
//////		
//////		
////////		pdfתhtml
//////		try {
//////			
//////			String htmlPath="D:/pdf/�������ϵ�ṹָ����/OPS";
//////			String htmlName=null;
//////			
//////			Format fm=new DecimalFormat("000");
//////			
//////			
//////			File subPdfDir=new File("D:/pdf/my_subPdf");
//////			File[] subPdf=subPdfDir.listFiles();
////////			��ʱ������
//////			Arrays.sort(subPdf, new Comparator<File>() {
//////	            public int compare(File f1, File f2) {
//////	                long diff = f1.lastModified() - f2.lastModified();
//////	                if (diff > 0)
//////	                    return 1;
//////	                else if (diff == 0)
//////	                    return 0;
//////	                else
//////	                    return -1;//��� if ���޸�Ϊ ����-1 ͬʱ�˴��޸�Ϊ���� 1  ����ͻ��ǵݼ�
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
//////		System.out.println("pdfתhtml�ɹ�");
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
		System.out.println("pdfתepub�ļ��ɹ�");
		
	}















}
