package pdfTool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class toZip {
	public void general(String resourcePath,String zipPath,String zipName) {
		try {
			
			String zipFile=new String(zipPath+"/"+zipName);
			String newzipFile=new String(zipPath+"/"+zipName.replaceAll(".zip",".epub"));
			FileOutputStream fos= new FileOutputStream(zipFile);
			ZipOutputStream zos = new ZipOutputStream(fos);
			
			ZipEntry container = new ZipEntry("mimetype");
			zos.putNextEntry(container);
			FileInputStream mimetypeData = new FileInputStream(resourcePath+"/mimetype");
			byte[] bys=new byte[1024];
			int by=0;
			while((by=mimetypeData.read(bys))!=-1) {
				zos.write(bys,0,by);
			}
			mimetypeData.close();
			
			ZipEntry container2 = new ZipEntry("META-INF/container.xml");
		    zos.putNextEntry(container2);
		    FileInputStream inMime2 = new FileInputStream(resourcePath+"/META-INF/container.xml");
		    int len2;
		    byte[]buffer2=new byte[1024];
		    while((len2 = inMime2.read(buffer2)) != -1){
		        zos.write(buffer2, 0, len2);
		    }
		    inMime2.close();
		    
		    ZipEntry container3 = new ZipEntry("OPS/css/style.css");
		    zos.putNextEntry(container3);
		    FileInputStream inMime3 = new FileInputStream(resourcePath+"/OPS/css/style.css");
		    int len3;
		    byte[]buffer3=new byte[1024];
		    while((len3 = inMime3.read(buffer3)) != -1){
		        zos.write(buffer3, 0, len3);
		    }
		    inMime3.close();
		    
		    ZipEntry container4 = new ZipEntry("OPS/Fonts");
		    zos.putNextEntry(container4);
		    File fontsFile=new File(resourcePath+"/OPS/Fonts");
		    String [] fontsName=fontsFile.list();
//		    不为空就写入
		    if(fontsName!=null) {
		    	File[]fontsArray=fontsFile.listFiles();
		    	for(File fonts:fontsArray) {
		    		ZipEntry font=new ZipEntry("OPS/Fonts/"+fonts.getName());
		    		zos.putNextEntry(font);
		    		FileInputStream inMime4 = new FileInputStream(fonts.getAbsoluteFile());
				    int len4;
				    byte[]buffer4=new byte[1024];
				    while((len4 = inMime4.read(buffer4)) != -1){
				        zos.write(buffer4, 0, len4);
				    }
				    inMime4.close();
		    	}
		    	
		    }
		    
		   
		 
		    		
		    		
		    File imgsource=new File(resourcePath+"/OPS/images");
		    File[] imgList=imgsource.listFiles();
		    for(File file : imgList){
	                System.out.println("File Added : " + file.getName());
	                ZipEntry img= new ZipEntry("OPS/images/"+file.getName());
	                zos.putNextEntry(img);

	                FileInputStream imgIn = 
	                    new FileInputStream(file.getAbsolutePath());

	                int imglen;
	                byte[] imgbuffer=new byte[1024];
	                while ((imglen = imgIn.read(imgbuffer)) > 0) {
	                        zos.write(imgbuffer, 0, imglen);
	                }

	                imgIn.close();
	           }
		    
		    
		    File resource=new File(resourcePath+"/OPS");
		    File[] fileList=resource.listFiles();
		    for(File file : fileList){
	            if(!file.getName().equals("css") && !file.getName().equals("Fonts")&&!file.getName().equals("images")){
	                System.out.println("File Added : " + file.getName());
	                ZipEntry ze= new ZipEntry("OPS/"+file.getName());
	                zos.putNextEntry(ze);

	                FileInputStream in = 
	                    new FileInputStream(file.getAbsolutePath());

	                int len;
	                byte[] buffer=new byte[1024];
	                while ((len = in.read(buffer)) > 0) {
	                        zos.write(buffer, 0, len);
	                }

	                in.close();
	           }
	    }
		    
		    zos.closeEntry();
		    zos.close();
		    File oldName=new File(zipFile);
		    File newName=new File(newzipFile);
		    oldName.renameTo(newName);
		    
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	}
