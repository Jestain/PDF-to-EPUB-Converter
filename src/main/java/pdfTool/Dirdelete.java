package pdfTool;

import java.io.File;

public class Dirdelete {
	public void delete(String deletePath) {
		File file=new File(deletePath);
		doDelete(file);
	}
	private void doDelete(File f) {
		File[]fileArray=f.listFiles();
		if(fileArray!=null) {
			for(File fe:fileArray) {
				if(fe.isDirectory()) {
					doDelete(fe);
				}
				else {
					fe.delete();
				}
			}
		}
		f.delete();
		System.out.println("É¾³ý³É¹¦");
	}
}
