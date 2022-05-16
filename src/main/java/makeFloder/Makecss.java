package makeFloder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Makecss {
	public void general(String rootPath) {
		try {
			File stylecss=new File(rootPath+"/OPS"+"/css"+"/style.css");
			File css =new File(rootPath+"/OPS"+"/css");
			if(!stylecss.exists()) {
				if(!css.exists()) {
					css.mkdirs();
				}
				stylecss.createNewFile();
			}
			BufferedWriter writer=new BufferedWriter(new FileWriter(stylecss));
			writer.write("@font-face\r\n"
					+ "{\r\n"
					+ "font-family:\"msyh\";\r\n"
					+ "src:url(\"../Fonts/EmbedFont.ttf\");\r\n"
					+ "}\r\n"
					+ "@font-face {\r\n"
					+ "font-family:\"cnepub\";\r\n"
					+ "src:url(res:///opt/sony/ebook/FONT/tt0011m_.ttf), url(res:///tt0011m_.ttf);\r\n"
					+ "}\r\n"
					+ "body {\r\n"
					+ "font-family:\"cnepub\", serif;\r\n"
					+ "font-family: \"msyh\";\r\n"
					+ "}");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
