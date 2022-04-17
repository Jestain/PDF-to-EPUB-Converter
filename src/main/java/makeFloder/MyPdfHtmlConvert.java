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

         //����һ��PDF�ĵ�
		 doc.loadFromFile(pdfPath);
         //��ȡ��һҳ
         PdfPageBase page = doc.getPages().get(0);
         


         //��ȡ��һҳ�ϵ�ͼƬ��Ϣ
         PdfImageInfo[] imageInfo = page.getImagesInfo();
         
//         float lastX=0;
         float lastY=0;
         float lastHeight=0;
//       ��ȡҳ����,�߶�
       float width=(float)page.getSize().getWidth();
       float height=(float)page.getSize().getHeight();

         //����ͼƬ��Ϣ
         for (int i = 0; i < imageInfo.length; i++)
         {
        	 
             //��ȡָ��ͼƬ�ı߽�����
             Rectangle2D rect = imageInfo[i].getBounds();

             //��ȡ���Ͻ�����
             System.out.println(String.format("��%d��ͼƬ�����Ͻ�����Ϊ����%f, %f��", i+1, rect.getX(), rect.getY()));
            
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
					//ָ�����ͼƬ����ָ��ͼƬ��ʽ,��׺�Լ���
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
