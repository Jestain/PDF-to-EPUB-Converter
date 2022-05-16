package test;

import java.io.File;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Arrays;
import java.util.Comparator;

import pdftostandardhtml.Pdf2Html;

public class T1 {

//	public static void main(String[] args) {
//		if (args.length != 4) {
//			System.out.println("Invalid arguments");
//			System.out.println("Use: java -jar PDF-to-standard-HTML.jar path/to/pdf/file.pdf path/to/output/file.html type zoom");
//			System.out.println("type is an int:");
//			System.out.println("0 for the simplest method");
//			System.out.println("1 to group letters by word");
//			System.out.println("2 to group letters by line");
//			System.out.println("3 to group letters by line using a cache");
//			System.exit(1);
//		}
		
		/*String folderPath="D:\\pdf_6\\geography";
    	String OPSPath=new String(folderPath+"/OPS");
		File subPdfDir=new File(folderPath+"/my_subPdf");
		
		File[] subPdf=subPdfDir.listFiles();
//				��ʱ������
		Arrays.sort(subPdf, new Comparator<File>() {
		    public int compare(File f1, File f2) {
		        long diff = f1.lastModified() - f2.lastModified();
		        if (diff > 0)
		            return 1;
		        else if (diff == 0)
		            return 0;
		        else
		            return -1;//��� if ���޸�Ϊ ����-1 ͬʱ�˴��޸�Ϊ���� 1  ����ͻ��ǵݼ�
		    }

		    public boolean equals(Object obj) {
		        return true;
		    }

		});
		int n=0;
		Format fm=new DecimalFormat("000");
		for(File subFile:subPdf) {
//			MyPdfToHtml.convert(subFile.getAbsolutePath(), OPSPath,"page-"+fm.format(++n) );
//			  pdf2html("C:\\Users\\Jestain\\Downloads\\pdfתhtml\\pdfתhtml\\pdf2htmlEX-v1.0\\pdf2htmlEX-v1.0\\pdf2htmlEX.exe",subFile.getAbsolutePath(),"C:\\Users\\Jestain\\Downloads\\pdfתhtml\\pdfתhtml","page-"+fm.format(++n)+".html");
			convert(subFile.getAbsolutePath(),"D:/pdf_6/"+"page-"+fm.format(++n)+".html");
		}*/
	
	public static void main(String[] args) {
		startConvert("D:/pdf_5/subPdf1.pdf", "D:/pdf_5/subPdf1.html");
	}
	public static void startConvert(String pdfPath,String htmlPath) {
		long s=System.currentTimeMillis();
		
		String pdfFileName = pdfPath;

		String outputFileName =  htmlPath;
		int type =  0;
		float zoom = 2;

		try {
			Pdf2Html pdf2Html = new Pdf2Html(outputFileName, type, zoom);
			pdf2Html.convertPdfToHtml(pdfFileName,outputFileName);
			pdf2Html.closeFile();
		} catch (Exception e) {
            System.err.println( "Filed to convert Pdf to Html." );
			e.printStackTrace();
		}
		
		System.out.println("Done"+(System.currentTimeMillis()-s));
	}
	public static void convert(String pdfPath,String htmlPath) {
		long s=System.currentTimeMillis();
		
		String pdfFileName = pdfPath;

		String outputFileName =  htmlPath;
		int type =  0;
		float zoom = 2;

		try {
			Pdf2Html pdf2Html = new Pdf2Html(outputFileName, type, zoom);
			pdf2Html.convertPdfToHtml(pdfFileName,outputFileName);
			pdf2Html.closeFile();
		} catch (Exception e) {
            System.err.println( "Filed to convert Pdf to Html." );
			e.printStackTrace();
		}
		
		System.out.println("Done"+(System.currentTimeMillis()-s));
	}

}
