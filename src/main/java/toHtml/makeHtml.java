package toHtml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class makeHtml {
	String rootPath;
	String htmlName;
	FileWriter writer;
	makeHtml(String rootPath,String htmlName){
		this.rootPath=rootPath;
		this.htmlName=htmlName;
	}
	public void finite() {

		File html=new File(rootPath+"/"+htmlName+".html");
			if(!html.exists()) {
				try {
					
					html.createNewFile();

					BufferedWriter writer = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (rootPath+"/"+htmlName+".html",true),"UTF-8"));
//					writer=new FileWriter(rootPath+"/"+htmlName+".html",true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}
		public void addHead(String page) {
			try {
				BufferedWriter writer = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (rootPath+"/"+htmlName+".html",true),"UTF-8"));
				writer.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"zh-cn\">\r\n"
						+ "<head>\r\n"
						+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\"/>\r\n"
						+ "<title>page-"+page+"</title>\r\n"
						+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\r\n"
						+ "</head>\r\n"
						+ "<body style=\"background-color:#ffffff; \">\r\n");

				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		
	
	
	
	public void addParagrap(String p) {
		try {
			if(p!=null) {
				BufferedWriter writer = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (rootPath+"/"+htmlName+".html",true),"UTF-8"));
//				writer=new FileWriter(rootPath+"/"+htmlName+".html",true);
				writer.write("<p>"+p+"</p>\r\n");
				writer.flush();
				writer.close();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addImage(int left,int top,int width,int height,String img) {
		try {
			BufferedWriter writer = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (rootPath+"/"+htmlName+".html",true),"UTF-8"));
			writer.write("<div><img style=\"position:absolute;left:"+left+"rem;"+"top:"+top+"rem;"+"width:"+width+"rem;"+"height:"+height+"rem\" "+"src=\""+img+"\" /></div>\r\n");
			writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addEnd() {
		try {
			BufferedWriter writer = new BufferedWriter (new OutputStreamWriter (new FileOutputStream (rootPath+"/"+htmlName+".html",true),"UTF-8"));
//			writer=new FileWriter(rootPath+"/"+htmlName+".html",true);
			writer.write("</body></html>");
			writer.flush();
			writer.close();
//			rename();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void rename() {
		File oldName=new File(rootPath+"/"+htmlName+".txt");
		File newName=new File(rootPath+"/"+htmlName+".html");
		oldName.renameTo(newName);
	}
}

