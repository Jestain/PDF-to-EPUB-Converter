package toHtml;

public class MyPdfToHtml {
//	htmlName�ǲ��Ӻ�׺�����֣�����������Ϊimages����
	public static void convert(String pdfAbsoultPath,String OPSPath,String htmlName) {
		String imgPath=OPSPath+"/images";
//		saveImages.save(pdfAbsoultPath, imgPath, htmlName);
		splitText.getSplitText(pdfAbsoultPath,OPSPath, imgPath, htmlName);
	}
}
