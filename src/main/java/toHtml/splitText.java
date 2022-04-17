package toHtml;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfImageObject;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.exporting.PdfImageInfo;

import test.T1;

public class splitText {
	public static void getSplitText(String pdfPath,String OPSPath,String imgPath,String htmlName) {
//		String imgPath=OPSPath+"/images";
		String havelasthtml=OPSPath+"/"+htmlName+".html";
		T1.startConvert(pdfPath,havelasthtml);
//	}
//}
		
		makeHtml html=new makeHtml(OPSPath,htmlName);
 	  	html.finite();
 	  	
 	  	
        final List<int[]> arrays = new ArrayList<int[]>();
        final StringBuffer ps=new StringBuffer();
        final List<BufferedImage> images = new ArrayList<BufferedImage>();
        PdfReader pdfReader;
            try {

		        
                pdfReader = new PdfReader(pdfPath);
                int pageNum = pdfReader.getNumberOfPages();
                PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);

                for (int i = 1; i <= pageNum; i++) {

                    pdfReaderContentParser.processContent(i, new RenderListener() {
                    	 @Override
	                        public void renderImage(ImageRenderInfo arg0) {
	                            PdfImageObject image0;
	                            
	                            try {
	                                image0 = arg0.getImage();
	                                byte[] imageByte = image0.getImageAsBytes();
	                                Image imageInPDF = Image.getInstance(imageByte);
	                                if(image0!=null){
	                                    int[] resu = new int[4];
	                                    // 0 => x;  1 => y;  2 => z
	                                    //z的值始终为1
	                                    resu[0] = (int)arg0.getStartPoint().get(0);
	                                   
	                                    resu[1] = (int)arg0.getStartPoint().get(1);
	                                    BufferedImage image=arg0.getImage().getBufferedImage();
	                                    resu[2]=image.getWidth();
	                                    resu[3]=image.getHeight();
	                                    images.add(image);
	                                    
	                                   
//	                                   PdfImageObject img= arg0.getImage();
//	                                   BufferedImage buffimg=img.getBufferedImage();
	                                   
//	                                   ps.append("x:"+arg0.getStartPoint().get(0)+"y:"+arg0.getStartPoint().get(1)+"宽："+buffimg.getWidth()+"高："+buffimg.getHeight());;
	                                   arrays.add(resu);
	                                }
	                            } catch (IOException e) {
	                                e.printStackTrace();
	                            } catch (BadElementException e) {
	                                e.printStackTrace();
	                            }

                        //此方法是监听PDF里的图片内容
                       

                        }

						@Override
						public void beginTextBlock() {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void endTextBlock() {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void renderText(TextRenderInfo arg0) {
							// TODO Auto-generated method stub
							
						}

                        

                    });
                    int img_i=0;
                    for(BufferedImage img:images) {
                    	saveImages.save(pdfPath, imgPath, htmlName,img,++img_i);
                    }
                   int len=arrays.size();
                   int count=0;
                   Format fm=new DecimalFormat("00");
                    	for(int[] arr:arrays) {
                    		count++;
                    		if(count<len) {
//                    			html.addImage(arr[0], arr[1],arr[2], arr[3],"images/"+htmlName.replaceAll("page", "img") +fm.format(count)+".jpeg");
                    		System.out.println(arr[0]+";"+arr[1]+";"+arr[2]+";"+arr[3]);
                    	}else {
//                    		html.addImage(arr[0], arr[1],arr[2], arr[3],"images/"+htmlName.replaceAll("page", "img") +fm.format(count)+".jpeg");
//                    		html.addEnd();
                    		System.out.println("最后一个："+arr[0]+";"+arr[1]+";"+arr[2]+";"+arr[3]);
                    	}
                    	}
                    
                    
//                    System.out.println(ps);
                }
                

                pdfReader.close();

            } catch (IOException e) {

                e.printStackTrace();
            }
           
            
    }

}

	/*public static void getSplitText(String pdfPath,String OPSPath,String imgPath,String htmlName) {
		String havelasthtml=OPSPath+"/"+htmlName+".html";
		T1.startConvert(pdfPath,havelasthtml);
		
		
		
		PdfDocument doc = new PdfDocument();

       //加载一个PDF文档
		 doc.loadFromFile(pdfPath);
       //获取第一页
       PdfPageBase page = doc.getPages().get(0);
       

       //获取第一页上的图片信息
       PdfImageInfo[] imageInfo = page.getImagesInfo();
       	makeHtml html=new makeHtml(OPSPath,htmlName);
 	  	html.finite();
       if(imageInfo.length!=0) {


         
         
    	 
         
         Format fm=new DecimalFormat("00");
           //遍历图片信息
           for (int i = 1; i <= imageInfo.length; i++)
           {
          	 
               //获取指定图片的边界属性
               Rectangle2D rect = imageInfo[i-1].getBounds();

               //获取左上角坐标

              
//               String splitText = page.extractText(new Rectangle(0,(int)(lastY+lastHeight),(int)width, (int)rect.getY()));
               int left=(int)rect.getX();
               int top=(int)rect.getY();
               int width=(int)rect.getWidth();
               int height=(int)rect.getHeight();
               

               System.out.println(String.format("第%d张图片的左上角坐标为：（%f, %f）", i, rect.getX(), rect.getY()));
               html.addImage(left, top,width, height,"images/"+htmlName.replaceAll("page", "img") +fm.format(i)+".jpeg");
               if(i==imageInfo.length) {
            	html.addImage(left, top,width, height,"images/"+htmlName.replaceAll("page", "img") +fm.format(i)+".jpeg");
              	html.addEnd();
                
                	}
                
                }
       }else {
    	   html.addEnd();
       }
      
       

//          	 System.out.println(11);
           }
}*/
	
	
//	public static void getSplitText(String pdfPath,String OPSPath,String imgPath,String htmlName) {
//		 PdfDocument doc = new PdfDocument();

       //加载一个PDF文档
//		 doc.loadFromFile(pdfPath);
       //获取第一页
//       PdfPageBase page = doc.getPages().get(0);
//       
//       makeHtml html=new makeHtml(OPSPath,htmlName);
//       html.finite();
//       html.addHead(htmlName.substring(5,htmlName.length()));
//
//       //获取第一页上的图片信息
//       PdfImageInfo[] imageInfo = page.getImagesInfo();
//       if(imageInfo.length!=0) {
////         float lastX=0;
//           float lastY=0;
//           float lastHeight=0;
////         获取页面宽度,高度
//         float width=(float)page.getSize().getWidth();
//         float height=(float)page.getSize().getHeight();
//         
//         
//        
//
//         
//         Format fm=new DecimalFormat("00");
//           //遍历图片信息
//           for (int i = 1; i <= imageInfo.length; i++)
//           {
//          	 
//               //获取指定图片的边界属性
//               Rectangle2D rect = imageInfo[i-1].getBounds();
//
//               //获取左上角坐标
//
//              
//               String splitText = page.extractText(new Rectangle(0,(int)(lastY+lastHeight),(int)width, (int)rect.getY()));
////               System.out.println(splitText);
//               String[] lines=splitText.split("\r\n");
//               for(int n=0;n<lines.length;n++) {
//               	html.addParagrap(lines[n]);
//               	System.out.println(lines[n]);
//               }
//               System.out.println(String.format("第%d张图片的左上角坐标为：（%f, %f）", i, rect.getX(), rect.getY()));
//               html.addImage("images/"+htmlName.replaceAll("page", "img") +fm.format(i)+".jpeg");
//               lastY=(float)rect.getY();
//               lastHeight=(float)rect.getHeight();
//               if(i==imageInfo.length) {
//              	 String lastText=page.extractText(new Rectangle(0,(int)(lastY+lastHeight),(int)width,(int)height));
//              	
//              	 
//                String[] lastlines=lastText.split("\r\n");
//                int len=lastlines.length;
//                if(lastlines!=null) {
//               	 if(len!=0) {
//               		 for(int n=0;n<len;n++) {
//                        	html.addParagrap(lastlines[n]);
//                        	if(n==len-1) {
//                        		html.addEnd();
//       	             		
//       	             	}
//       	             }
//               	 }
//               	 else {
//               		 html.addEnd();
//               	 }
//                }else {
////               	 System.out.println(1);
//               	 html.addEnd();
//                }
//                	}
//                
//                }
//       }
//       else {
//    	   String content=page.extractText(true);
//    	   String[] lines=content.split("\r\n");
//           for(int n=0;n<lines.length;n++) {
//           	html.addParagrap(lines[n]);
//           	System.out.println(lines[n]);
//           	if(n==lines.length-1) {
//           		html.addEnd();
//           	}
//           }
//       }
//       
//
////          	 System.out.println(11);
//           }
//}
