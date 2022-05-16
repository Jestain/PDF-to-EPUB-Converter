package load;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UpLoad
 */
@WebServlet("/UpLoad")
public class UpLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	  String uploadPath;
      String tempPath;
    public UpLoad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("GBK");
		response.setContentType("text/html;charset=GBK");
		uploadPath=this.getServletContext().getRealPath("/WEB-INF/uploadPath");
        tempPath=this.getServletContext().getRealPath("/WEB-INF/tempPath");
        HttpSession session=request.getSession();
       
        if(!session.getAttributeNames().hasMoreElements()) {
        	session.setAttribute("currOrder", 0);
        }else {
        	int currOrder=Integer.parseInt(session.getAttribute("currOrder").toString());
        	currOrder++;
        	session.setAttribute("currOrder", currOrder);
        }
        String progressName="a"+session.getAttribute("currOrder")+"imgId";
        System.out.println(progressName);
        session.setAttribute(progressName,0); 
        
        File tmpFile = new File(tempPath);
        if (!tmpFile.exists()) {
            //创建临时目录
            tmpFile.mkdirs();
        }
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()) {
            //创建临时目录
            uploadFile.mkdirs();
        }
        System.out.println(uploadPath);
        System.out.println(tempPath);
        
        try {
        DiskFileItemFactory factory=new DiskFileItemFactory();
		factory.setSizeThreshold(4*1024);
		factory.setRepository(new File(tempPath));
		ServletFileUpload upload=new ServletFileUpload(factory);
		upload.setSizeMax(50*1024*1024);
		List items;
		items = upload.parseRequest(request);
		Iterator iter=items.iterator();
		while(iter.hasNext()) {
			FileItem item=(FileItem)iter.next();
			
			if(!item.isFormField()) {
				String filename=item.getName();
				int index=filename.lastIndexOf("\\");
				filename=filename.substring(index+1);
				System.out.println("文件名："+filename);
				long fileSize=item.getSize();
				InputStream in=item.getInputStream();
				
				FileOutputStream out=new FileOutputStream(new File(uploadPath+"/"+filename));
				int len=0;
				byte[] by=new byte[1024];
				double percent=0;
				while((len=in.read(by))!=-1) {
					out.write(by, 0, len);
					 percent+=100*len/((double)fileSize); 
					 session.setAttribute(progressName,Math.round(percent));
					// System.out.println(progressName);
					 Thread.sleep(1);
				
				}
				out.close();
			}
			
		}
		} catch (FileUploadException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}

}
