package toHtml;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;

import javax.imageio.ImageIO;

import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;

public class saveImages {

	
	public static void save(String pdfPath,String imagesPath,String htmlName,BufferedImage image,int i) {
//	}
//}
//	}
//			PdfDocument document=new PdfDocument();
//			document.loadFromFile(pdfPath);
//			PdfPageBase page;
//			page=document.getPages().get(0);
//			int i=0;
			Format fm=new DecimalFormat("00");
//			if (page.extractImages() != null) {
//				for (BufferedImage image : page.extractImages()) {
					if (image != null) {
						//指定输出图片名，指定图片格式,后缀自己换
						File output = new File(imagesPath+"/"+htmlName.replaceAll("page","img") +fm.format(i)+".png");
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
							File newName=new File(imagesPath+"/"+htmlName.replaceAll("page","img")+fm.format(i)+".jpeg");
							output.renameTo(newName);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			
		}
		




