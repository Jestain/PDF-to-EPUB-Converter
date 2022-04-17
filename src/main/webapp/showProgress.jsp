<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@ page import="org.apache.commons.fileupload.FileItem" %>
    <%@ page import="org.apache.commons.fileupload.FileUploadException" %>
    <%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
    <%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
    <%@ page import="java.io.File" %>
    <%@ page import="java.util.List" %>
    <%@ page import="java.io.FileOutputStream" %>
    <%@ page import="java.util.Iterator" %>
<%
	
	String value;
	String M=new String() ;
	DiskFileItemFactory factory = new DiskFileItemFactory();

    ServletFileUpload upload = new ServletFileUpload(factory);
    upload.setHeaderEncoding("UTF-8");
	
    try {
        // 解析请求的内容提取文件数据
        @SuppressWarnings("unchecked")
        List<FileItem> formItems = upload.parseRequest(request);

        if (formItems != null && formItems.size() > 0) {
            // 迭代表单数据
            for (FileItem item : formItems) {
                // 处理不在表单中的字段
                if (!item.isFormField()) {
                   
                }else {
                    value = item.getString("utf-8");
                    System.out.println(value);
                    M = session.getAttribute("a"+value+"imgId").toString();
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
  	
  	
  	//System.out.println(M);
%>

<%=M%>
