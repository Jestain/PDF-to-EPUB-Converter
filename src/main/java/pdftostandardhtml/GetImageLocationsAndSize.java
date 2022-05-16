package pdftostandardhtml;



import org.apache.mypdfbox.cos.COSBase;
import org.apache.mypdfbox.cos.COSName;
import org.apache.mypdfbox.pdmodel.PDDocument;
import org.apache.mypdfbox.pdmodel.PDPage;
import org.apache.mypdfbox.pdmodel.graphics.PDXObject;
import org.apache.mypdfbox.pdmodel.graphics.form.PDFormXObject;
import org.apache.mypdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.mypdfbox.util.Matrix;
import org.apache.mypdfbox.contentstream.operator.DrawObject;
import org.apache.mypdfbox.contentstream.operator.Operator;
import org.apache.mypdfbox.contentstream.PDFStreamEngine;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.mypdfbox.contentstream.operator.state.Concatenate;
import org.apache.mypdfbox.contentstream.operator.state.Restore;
import org.apache.mypdfbox.contentstream.operator.state.Save;
import org.apache.mypdfbox.contentstream.operator.state.SetGraphicsStateParameters;
import org.apache.mypdfbox.contentstream.operator.state.SetMatrix;
  
/**
* This is an example on how to get the x/y coordinates of image location and size of image.
*/
public class GetImageLocationsAndSize extends PDFStreamEngine
{
	ArrayList<int[]> array;
	String pdfPath;
	private String fiName;
	BufferedWriter htmlFile;
	int pageHeight;
	 public int imageNumber = 1;
	 private String imgPath;
    /**
     * @throws IOException If there is an error loading text stripper properties.
     */
    public GetImageLocationsAndSize(String imagesPath,String fileName,BufferedWriter htmlFi,int pageHei) throws IOException
    {

    	array=new ArrayList<int []>();
    	fiName=fileName;
    	imgPath=imagesPath;
    	htmlFile=htmlFi;
    	pageHeight=pageHei;
        // preparing PDFStreamEngine
        addOperator(new Concatenate());
        addOperator(new DrawObject());
        addOperator(new SetGraphicsStateParameters());
        addOperator(new Save());
        addOperator(new Restore());
        addOperator(new SetMatrix());
        
    }
  
    /**
     * @throws IOException If there is an error parsing the document.
     */
//    public static void main( String[] args ) throws IOException
    public void putImages(String pdfPath) throws IOException
    {
    	
    	System.out.println("imagesPath:"+imgPath);
        PDDocument document = null;
        this.pdfPath = pdfPath;
        this.htmlFile=htmlFile;
        
        System.out.println("pdfPath:"+pdfPath);
        System.out.println("imagesPath:"+imgPath);
        System.out.println("fileName:"+fiName);
        System.out.println("htmlFile:"+htmlFile);
        System.out.println("pageHeight:"+pageHeight);
        try
        {
            document = PDDocument.load( new File(pdfPath) );
            if(document==null) {
            	System.out.println("document为空");
            }
            GetImageLocationsAndSize printer = new GetImageLocationsAndSize(imgPath,fiName,htmlFile,pageHeight);
            int pageNum = 0;
            for( PDPage page : document.getPages() )
            {
                pageNum++;
                System.out.println( "\n\nProcessing page: " + pageNum +"\n---------------------------------");
                printer.processPage(page);
            }
        }
        finally
        {
        	
            if( document != null )
            {
                document.close();
            }
           
        }
        System.out.println("里面的array的长度："+array.size());
    }
    public String getString() {
    	return imgPath;
    }
    public ArrayList<int[]> getArray() {
    	System.out.println("取的时候的array长度："+array.size());
    	return array;
    }
  
    /**
     * @param operator The operation to perform.
     * @param operands The list of arguments.
     *
     * @throws IOException If there is an error processing the operation.
     */
    @Override
    protected void processOperator( Operator operator, List<COSBase> operands) throws IOException
    {
        String operation = operator.getName();
        if( "Do".equals(operation) )
        {
            COSName objectName = (COSName) operands.get( 0 );
            // get the PDF object
            PDXObject xobject = getResources().getXObject( objectName );
            // check if the object is an image object
            if( xobject instanceof PDImageXObject)
            {
                PDImageXObject image = (PDImageXObject)xobject;
                
                BufferedImage bImage = image.getImage();
                System.out.println("下面的imgPath:"+getString());
                System.out.println("下面的fileName:"+fiName);
                ImageIO.write(bImage,"PNG",new File(imgPath+"/"+fiName+imageNumber+".jpeg"));
                System.out.println("Image saved.");
                
               
                
                
                int imageWidth = image.getWidth();
                int imageHeight = image.getHeight();
                 
                System.out.println("\nImage [" + objectName.getName() + "]");
  
                Matrix ctmNew = getGraphicsState().getCurrentTransformationMatrix();
                float imageXScale = ctmNew.getScalingFactorX();
                float imageYScale = ctmNew.getScalingFactorY();
                 

                // position of image in the pdf in terms of user space units
                System.out.println("position in PDF = " + ctmNew.getTranslateX() + ", " + ctmNew.getTranslateY() + " in user space units");
                // raw size in pixels
                System.out.println("raw image size  = " + imageWidth + ", " + imageHeight + " in pixels");
                // displayed size in user space units
                System.out.println("displayed size  = " + imageXScale + ", " + imageYScale + " in user space units");
                
                
                int[] data= {(int)(2 * imageXScale),(int)(2 * imageYScale),(int)(2 * ctmNew.getTranslateX()),(int)(pageHeight-(2*(imageYScale+ctmNew.getTranslateY())))};
                int[]a=data;
                System.out.println("循环里面的array长度："+array.size());
                              	
                	htmlFile.write("<img class=\"background\" style=\"position: absolute; width: " +
                            a[0] + "rem; height: " + a[1] +
                            "rem;margin-left: "+a[2]+"rem; margin-top: " + a[3] + "rem\""+" src=\"images/"  + fiName+(imageNumber)+".jpeg" +
                            "\""+" />");
                
                
                imageNumber++;
            }
            else if(xobject instanceof PDFormXObject)
            {
                PDFormXObject form = (PDFormXObject)xobject;
                showForm(form);
            }
        }
        else
        {
            super.processOperator( operator, operands);
        }
    }
  
}
