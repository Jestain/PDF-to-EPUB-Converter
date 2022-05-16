package makeFloder;

import java.io.File;

public class toFolder {
	public static void get(String folderPath) {
		File folder=new File(folderPath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		Makemimetype mime=new Makemimetype();
		mime.general(folderPath);
		
		Makecontainer container=new Makecontainer();
		container.general(folderPath);
		
		
		Makecss css=new Makecss();
		css.general(folderPath);
		
		MakeFonts fonts=new MakeFonts();
		fonts.general(folderPath);
		
		
		Makeimages images=new Makeimages();
		images.general(folderPath);
		
//		Dirdelete deleter=new Dirdelete();
//		deleter.delete("D:/pdf/my_subPdf");
	}
}
