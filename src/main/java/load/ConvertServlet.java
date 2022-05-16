package load;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pdfTool.MyThread;
import pdfTool.ThreadRecord;

/**
 * Servlet implementation class ConvertServlet
 */
@WebServlet("/ConvertServlet")
public class ConvertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConvertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("这里是ConverServlet.");
		
		String pdfName=request.getParameter("sendFileName");
		String  order=request.getParameter("order");
		String epubName=pdfName.replaceAll(".pdf", ".epub");
		String fileName=pdfName.substring(0,pdfName.length()-4);
//		获取上传区，下载区的路径
		String inputPath=this.getServletContext().getRealPath("/WEB-INF/uploadPath");
		String outputPath=this.getServletContext().getRealPath("/WEB-INF/downloadPath");
		String logPath=this.getServletContext().getRealPath("/WEB-INF/log");
		System.out.println(inputPath);
		System.out.println(outputPath);
		File inputfile=new File(inputPath);
		File outputfile=new File(outputPath);
		File logfile=new File(logPath);
		if(!inputfile.exists()) {
			inputfile.mkdirs();
		}
		if(!outputfile.exists()) {
			outputfile.mkdirs();
		}
		if(!logfile.exists()) {
			logfile.mkdirs();
		}
//		判断存在后转换
//		System.out.println(haveFile(inputfile,pdfName));
//		System.out.println(inputfile.getName()+"--"+pdfName);
//		if(haveFile(inputfile,pdfName)) {
		HttpSession session=request.getSession();
		ThreadRecord curr=(ThreadRecord)session.getAttribute("b"+order+"per");
			if(request.getParameter("command").equals("convert")) {		
					if(curr==null) {
						MyThread thread1=new MyThread(inputPath+"/"+pdfName,outputPath+"/"+epubName,logPath);
						
						thread1.start();		
						ThreadRecord record=new ThreadRecord(thread1);
						session.setAttribute("b"+order+"per",record);
						System.out.println("start");
						System.out.println("-------------------------"+thread1.getInteger());
					}else {
						MyThread thread1=curr.getThread();
						thread1.resumeThread();
						System.out.println("continue");
					}
			}else if(request.getParameter("command").equals("interrupt")) {
				MyThread thread1=curr.getThread();
				thread1.pauseThread();
				System.out.println("interrupt");
			}	
		
		
	}
	
	private static boolean haveFile(File fileDir,String fileName) {
		String[]fileNameList=fileDir.list();
		for(String f:fileNameList) {
			if(f.equals(fileName)) {
				return true;
			}
		}
		return false;
	}

}
