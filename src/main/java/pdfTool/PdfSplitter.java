package pdfTool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.spire.pdf.PdfDocument;

public class PdfSplitter {
//	public static void split(String document,String subPdfPath) {
//		PdfDocument doc = new PdfDocument();
//	    doc.loadFromFile(document);
//
//	    //���÷���split()��PDF�ĵ���ÿһҳ���Ϊ�������ĵ�
//	    doc.split(subPdfPath+"/subPdf{0}.pdf", 1);
//	    doc.close();
//	}
	public static void main(String[] args) {
//		try {
//			split("D:/pdf_2/geography.pdf","D:/pdf_2/geography");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public static void split(String document,String subPdfPath) throws Exception {
		
		String filename=document;
		int splitSize=1;
		PdfReader reader;
		File sub=new File(subPdfPath);
		if(!sub.exists()) {
			sub.mkdirs();
		}
	    try {
	        reader = new PdfReader(filename);
	    } catch (IOException e) {
//	        log.error(e.getMessage(), e);
	        throw new Exception("��ȡPDF�ļ�ʧ��");
	    }
	    int numberOfPages = reader.getNumberOfPages();
	    int newFileCount = 1;
	    // PageNumber�Ǵ�1��ʼ������
	    int pageNumber = 1;
	    while (pageNumber <= numberOfPages) {
	        Document doc = new Document();
	        String splitFileName = subPdfPath+"/" + "subPdf" + newFileCount + ".pdf";
	        PdfCopy pdfCopy;
	        try {
	            pdfCopy = new PdfCopy(doc, new FileOutputStream(splitFileName));
	        } catch (FileNotFoundException e) {
//	            log.error(e.getMessage(), e);
	            throw new Exception("�и��ļ���������ʧ��");
	        }
	        doc.open();
	        // ��pdf��ҳ���Ƶ��½���PDF��
	        for (int i = 1; pageNumber <= numberOfPages && i <= splitSize; ++i, pageNumber++) {
	            doc.newPage();
	            PdfImportedPage page = pdfCopy.getImportedPage(reader, pageNumber);
	            try {
					pdfCopy.addPage(page);
				} catch (BadPdfFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        doc.close();
	        newFileCount++;
	        pdfCopy.close();
	    }
}
}

//	public void split(PDDocument document,String subPdfPath) {
//		
//		try {
//			File f=new File(subPdfPath);
//			if(!f.exists()) {
//				f.mkdirs();
//			}
//			int i=0;
//			Splitter splitter=new Splitter();
//			List<PDDocument> pages=splitter.split(document);
//			for(PDDocument p:pages) {
//				p.save(subPdfPath+"/"+"subPdf"+(++i)+".pdf");
//				p.close();
//			}
//		} catch (IOException | COSVisitorException   e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
////	split����
//	public void split(PDDocument document,String subPdfPath,int eachPages) {
//		
//		try {
//			File f=new File(subPdfPath);
//			if(!f.exists()) {
//				f.mkdirs();
//			}
//			int i=0;
//			Splitter splitter=new Splitter();
//			splitter.setStartPage(1);
//			splitter.setSplitAtPage(eachPages);
//			List<PDDocument>pages=splitter.split(document);
//			for(PDDocument p:pages) {
//				p.save(subPdfPath+"/"+"subPdf"+(++i)+".pdf");
//			}
//		} catch (IOException | COSVisitorException  e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}















