package makeFloder;

import java.io.File;

public class Makeimages {
	public void general(String rootPath) {
		File images=new File(rootPath+"/OPS/images");
		if(!images.exists()) {
		images.mkdirs();
		}
	}
}
