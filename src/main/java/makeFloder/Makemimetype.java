package makeFloder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Makemimetype {
	public void general(String rootPath) {
		
			try {
				File Mimetype=new File(rootPath+"/mimetype");
				if(!Mimetype.exists()) {
				Mimetype.createNewFile();
				}
				BufferedWriter writer=new BufferedWriter(new FileWriter(Mimetype));
				writer.write("application/epub+zip");
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

