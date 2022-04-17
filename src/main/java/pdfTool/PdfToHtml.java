package pdfTool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import org.fit.pdfdom.PDFDomTree;

import org.apache.pdfbox.pdmodel.PDDocument;

public class PdfToHtml {

	
	public void convert(PDDocument document,String htmlPath,String htmlName) throws IOException {
		
		try {
			File fFather=new File(htmlPath);
			File fSon=new File(htmlPath,htmlName);
			if(!fSon.exists()) {
				if(!fFather.exists()) {
					fFather.mkdirs();
				}
				fSon.createNewFile();
			}
			FileOutputStream fos=new FileOutputStream(fSon);
			OutputStreamWriter osw=new OutputStreamWriter(fos,"utf-8");
			BufferedWriter out=new BufferedWriter(osw);
			PDFDomTree PdfDomTree=new PDFDomTree();
			PdfDomTree.writeText(document,out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
