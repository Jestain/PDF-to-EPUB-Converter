package toHtml;

public class MyPdfToHtml {
//	htmlName是不加后缀的名字，还可以用来为images命名
	public static void convert(String pdfAbsoultPath,String OPSPath,String htmlName) {
		String imgPath=OPSPath+"/images";
//		saveImages.save(pdfAbsoultPath, imgPath, htmlName);
		splitText.getSplitText(pdfAbsoultPath,OPSPath, imgPath, htmlName);
	}
}
