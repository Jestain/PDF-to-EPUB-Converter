package makeFloder;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;

import javax.imageio.ImageIO;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.exporting.PdfImageInfo;

public class MyPdfHtmlConvert {
	public void main(String[]args) {
//		SaveImage("D:\\pdf\\test\\subPdf12.pdf","D:\\pdf\\test\\images","image012");
		getSplitText("D:\\pdf\\test\\subPdf12.pdf");
		
	}
	public static void getSplitText(String pdfPath) {
		 PdfDocument doc = new PdfDocument();

         //加载一个PDF文档
		 doc.loadFromFile(pdfPath);
         //获取第一页
         PdfPageBase page = doc.getPages().get(0);
         


         //获取第一页上的图片信息
         PdfImageInfo[] imageInfo = page.getImagesInfo();
         
//         float lastX=0;
         float lastY=0;
         float lastHeight=0;
//       获取页面宽度,高度
       float width=(float)page.getSize().getWidth();
       float height=(float)page.getSize().getHeight();

         //遍历图片信息
         for (int i = 0; i < imageInfo.length; i++)
         {
        	 
             //获取指定图片的边界属性
             Rectangle2D rect = imageInfo[i].getBounds();

             //获取左上角坐标
             System.out.println(String.format("第%d张图片的左上角坐标为：（%f, %f）", i+1, rect.getX(), rect.getY()));
            
//             String splitText = page.extractText(new Rectangle(0,(int)(lastY+lastHeight),(int)width, (int)rect.getY()));
//             System.out.println(splitText);
//             lastY=(float)rect.getY();
//             lastHeight=(float)rect.getHeight();
//             if(i==imageInfo.length-1) {
//            	 String lastText=page.extractText(new Rectangle(0,(int)(lastY+lastHeight),(int)width,(int)height));
//            	 System.out.println(lastText);
//             }
       }
	}
	
	public static void SaveImage(String pdfPath,String imagesPath,String imagesFrontName) {
		PdfDocument document=new PdfDocument();
		document.loadFromFile(pdfPath);
//		document
		PdfPageBase page;
		page=document.getPages().get(0);
		int i=0;
		Format fm=new DecimalFormat("00");
		if (page.extractImages() != null) {
			for (BufferedImage image : page.extractImages()) {
				if (image != null) {
					//指定输出图片名，指定图片格式,后缀自己换
					File output = new File(imagesPath+"/"+imagesFrontName+fm.format(++i)+".png");
					if(!output.exists()) {
						try {
							output.createNewFile();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					try {
						ImageIO.write(image, "PNG", output);
						File newName=new File(imagesPath+"/"+imagesFrontName+fm.format(i)+".jpeg");
						output.renameTo(newName);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
