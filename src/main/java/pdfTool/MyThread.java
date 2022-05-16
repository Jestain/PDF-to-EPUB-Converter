package pdfTool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import org.apache.pdfbox.pdmodel.PDDocument;

import makeFloder.Makefbncx;
import makeFloder.Makefbopf;
import makeFloder.toFolder;
import toHtml.MyPdfToHtml;

public class MyThread extends Thread {
	String pdfPath;
	String epubPath;
	String folderPath;
	String logPath;
	private int n=0;
	String bookName;
	String zipPath;
	private boolean isOver=false;
	private int step=1;
	private final Object lock = new Object();
	private boolean pause = false;
//	pdf页数
	private int subCount;

	public double progress;
//	进度
	public Integer integer=0;
	
	private boolean isExit=false;
	
	public void exit() {
		isExit=true;
	}
	
	public MyThread(String pdfPath,String epubPath,String logPath){
		
		this.pdfPath=pdfPath;
		this.folderPath=epubPath.substring(0,epubPath.length()-5);
		this.logPath=logPath;
		
	}
	public void pauseThread() {
        this.pause = true;
    }
	public void resumeThread() {
        this.pause = false;
        synchronized (lock) {
            //唤醒线程
            lock.notify();
        }
    }
	
	void onPause() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
	public Integer getInteger() {
		return integer;
	}
	public void run() {
		super.run();
		while(!isOver&&!isExit) {
			
			if (pause) {
                onPause();
            }
			
			switch (step) {
			case 1:{cutPdf();step++;}
					break;
			case 2:{makeFolder();step++;}
					break;
			case 3:{smallConvert();step++;}
					break;
			case 4:{opf();step++;}
					break;
			case 5:{ncx();step++;}
					break;
			case 6:{zip();step++;}
					break;
			}
		}
	}
	

//class EpubConvertor{
	//pdfPath锟斤拷锟界：C:\\Users\\Jestain\\Desktop\\锟斤拷锟斤拷锟斤拷锟斤拷痰锟斤拷邸锟绞碉拷锟街革拷锟斤拷锟�.pdf
//	folderPath锟斤拷锟界：D:/pdf_2/锟斤拷锟斤拷锟斤拷锟较碉拷峁癸拷锟斤拷锟街革拷锟斤拷锟�
//	public void convert(String pdfPath,String folderPath) {
//		case1:切割pdf
		public void cutPdf() {
		try {
			System.out.println("-------------------------"+progress);
			System.out.println("pdfPath:  "+pdfPath);
			System.out.println("folderPath:  "+folderPath);
			PDDocument document=PDDocument.load(new File(pdfPath));
			progress+=1;
			integer=(int)Math.round(progress);
			String subPdfPath=new String(folderPath+"/my_subPdf");
			progress+=1;
			integer=(int)Math.round(progress);
			PdfSplitter splitter=new PdfSplitter();
			progress+=1;
			integer=(int)Math.round(progress);
			splitter.split(pdfPath, subPdfPath);
			progress+=1;
			integer=(int)Math.round(progress);
			document.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
//		case2:合成folder
		public void makeFolder() {
			System.out.println("-------------------------"+progress);
			progress+=2;
			integer=(int)Math.round(progress);
		toFolder.get(folderPath);
			progress+=2;
			integer=(int)Math.round(progress);
		}
		
		
//		case3:一张一张转换pdf:可在for循环中加入wait()
		public void smallConvert() {
			System.out.println("-------------------------"+progress);
		String OPSPath=new String(folderPath+"/OPS");
		File subPdfDir=new File(folderPath+"/my_subPdf");
		
		File[] subPdf=subPdfDir.listFiles();
//		pdf的页数
		subCount=subPdf.length;
		Arrays.sort(subPdf, new Comparator<File>() {
		    public int compare(File f1, File f2) {
		        long diff = f1.lastModified() - f2.lastModified();
		        if (diff > 0)
		            return 1;
		        else if (diff == 0)
		            return 0;
		        else
		            return -1;
		    }

		    public boolean equals(Object obj) {
		        return true;
		    }

		});
		n=0;
		Format fm=new DecimalFormat("000");
		for(File subFile:subPdf) {
			if(!isExit) {
			System.out.println("(double)1/(subCount): "+(double)1/(subCount));
			System.out.println("n: "+n);
			System.out.println("-------------------------"+progress);
			progress+=80*1/((double)(subCount));
			integer=(int)Math.round(progress);
			//Math.round(progress);
			if (pause) {
                onPause();
            }
			MyPdfToHtml.convert(subFile.getAbsolutePath(), OPSPath,"page-"+fm.format(++n) );
			}else {
				break;
			}
		}
		}
		
//		case4:合成opf文件
		public void opf() {
			System.out.println("-------------------------"+progress);
		progress+=1;
		integer=(int)Math.round(progress);
		String []folderName=folderPath.split("/");
		bookName=folderName[folderName.length-1];
		progress+=1;
		integer=(int)Math.round(progress);
		int bookNameLenth=bookName.length();
		zipPath=folderPath.substring(0,folderPath.length()-bookNameLenth-1);
		progress+=1;
		integer=(int)Math.round(progress);
		Makefbopf fbopf=new Makefbopf();
		fbopf.general(folderPath,bookName, n, "unknow", "unknow");
		progress+=1;
		integer=(int)Math.round(progress);
		}

//		case5:合成ncx文件
		public void ncx() {
			System.out.println("-------------------------"+progress);
		progress+=1;
		integer=(int)Math.round(progress);
		Makefbncx fbncx=new Makefbncx();
		progress+=1;
		integer=(int)Math.round(progress);
		fbncx.general(folderPath,bookName, n);
		progress+=2;
		integer=(int)Math.round(progress);
		}
		
//		case6:合成zip
		public void zip() {
			System.out.println("-------------------------"+progress);
		toZip zip=new toZip();
		progress+=1;
		integer=(int)Math.round(progress);
		zip.general(folderPath, zipPath, bookName+".zip");
		progress+=1;
		integer=(int)Math.round(progress);
		System.out.println("pdf转epub成功");
		progress+=1;
		integer=(int)Math.round(progress);	
		progress+=1;
		integer=(int)Math.round(progress);
		System.out.println("-------------------------"+progress);
		try {
//    		String logPath=this.getServletContext().getRealPath("/WEB-INF/log");
    		File logFile=new File(logPath);
    		if(!logFile.exists()) {
    			logFile.mkdirs();
    		}
			DateFormat fm=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date=new Date();
			//System.out.println(fm.format(date));
			FileOutputStream f;
			f = new FileOutputStream(new File(logPath+"/log.txt"),true);		
			OutputStreamWriter osw=new OutputStreamWriter(f);
			osw.write("----------"+fm.format(date)+"-------------\n"+"用户转换"+pdfPath.substring(pdfPath.lastIndexOf("/")+1,pdfPath.length())+"为"+pdfPath.substring(pdfPath.lastIndexOf("/")+1,pdfPath.length()).replaceAll(".pdf", ".epub")+"成功！\n\r");
			System.out.println("-----------------------------------\r"+"用户转换"+pdfPath.substring(pdfPath.lastIndexOf("/")+1,pdfPath.length())+"为"+pdfPath.substring(pdfPath.lastIndexOf("/")+1,pdfPath.length()).replaceAll(".pdf", ".epub")+"成功！\n\r");
			osw.flush();
			osw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isOver=true;
		}
		
//		case7:写日志
//		public void Log(String log) {
//			try {
//	    		String writelogPath=log;
//				DateFormat fm=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//				Date date=new Date();
//				//System.out.println(fm.format(date));
//				FileOutputStream f;
//				f = new FileOutputStream(new File(writelogPath),true);		
//				OutputStreamWriter osw=new OutputStreamWriter(f);
//				osw.write("----------"+fm.format(date)+"-------------\n"+"用户在转换"+fileName+"未完成时退出\n\r");
//				osw.flush();
//				osw.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
}
//	}
//}
