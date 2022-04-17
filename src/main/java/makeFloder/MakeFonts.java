package makeFloder;

import java.io.File;

public class MakeFonts {
	public void general(String rootPath) {
		File Fonts=new File(rootPath+"/OPS/Fonts");
		if(!Fonts.exists()) {
		Fonts.mkdirs();
		}
	}
}
