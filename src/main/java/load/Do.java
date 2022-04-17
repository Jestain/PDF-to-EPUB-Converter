package load;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pdfTool.MyThread;
import pdfTool.ThreadRecord;

/**
 * Servlet implementation class Do
 */
@WebServlet("/Do")
public class Do extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Do() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		    System.out.println(request.getParameter("username"));
//		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		    System.out.println(request.getParameter("username"));
//		    System.out.println(request.getParameter("password"));
//		request.setCharacterEncoding("GBK2312");
//		response.setCharacterEncoding("GBK2312");
//		String name1 = request.getParameter("fileName"); //閿熺煫纰夋嫹KEY涓�"name"閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿燂拷
//		String name = URLDecoder.decode(name1,"utf-8");
//		System.out.println(name);
//		String fileName = request.getParameter("fileName");
//		fileName = java.net.URLDecoder.decode("fileName", "UTF-8");
//	    System.out.println(request.getParameter("command"));
//	    System.out.println(request.getParameter("fileName"));
		    String command=request.getParameter("command");
		    String fileName=request.getParameter("sendFileName");
		    String order=request.getParameter("order");
		    fileName=fileName.substring(0,fileName.length()-4);
		    System.out.println("命令:"+command);
		    //编码
		    System.out.println("fileName:"+fileName);
		    
		    if(command.equals("convert")||command.equals("interrupt")) {
		    	//convert(fileName);
		    	RequestDispatcher requestDispatcher = request.getRequestDispatcher("/convert");
		        //counter2(鏌滃彴2)
		        requestDispatcher.forward(request,response);
		    }
		    if(command.equals("delete")) {
		    	System.out.println("-----------------------------------文件名："+fileName);
		    	HttpSession session=request.getSession();
		    	ThreadRecord curr=(ThreadRecord)session.getAttribute("b"+order+"per");
		    	if(curr!=null) {
		    	MyThread myThread=curr.getThread();
		    	myThread.exit();
		    	session.removeAttribute("b"+order+"per"); 
		    	
		    	System.out.println("-----------------------------------线程进度"+myThread.getInteger());
		    	if(curr!=null&&(myThread.getInteger()>0&&myThread.getInteger()<100)) {
		    	try {
		    		String logPath=this.getServletContext().getRealPath("/WEB-INF/log");
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
					osw.write("----------"+fm.format(date)+"-------------\n"+"用户在转换"+fileName+"未完成时退出\n\r");
					System.out.println("-----------------------------------未完成时退出");
					osw.flush();
					osw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    	}	
		    }
//		   
	      

	    }
	private void convert(String pdfName) {
		String inputPath=this.getServletContext().getRealPath("/WEB-INF/uploadPath");
		String outputPath=this.getServletContext().getRealPath("/WEB-INF/downloadPath");
//		String inputPath="D:\\Eclipse\\eclipse\\Java_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\MeanWeb\\WEB-INF\\uploadPath";
//		String outputPath="D:\\Eclipse\\eclipse\\Java_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\MeanWeb\\WEB-INF\\downloadPath";
//		閿熸枻鎷烽敓鏂ゆ嫹閿熸枻鎷�(pdf璺敓鏂ゆ嫹閿熸枻鎷烽敓鏂ゆ嫹閿熼摪鍑ゆ嫹閿熸枻鎷烽敓绲f閿熸枻鎷烽敓琛楄鎷烽敓鎺ョ尨鎷风紑)
		System.out.println("100010:"+inputPath);
		System.out.println("100010:"+outputPath);
		
//		MyThread thread1=new MyThread(inputPath+"/"+pdfName+".pdf",outputPath+"/"+pdfName);
//		Thread t1=new Thread(thread1);
//		t1.start();
	}
	
}


