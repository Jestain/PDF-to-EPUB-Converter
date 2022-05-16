package makeFloder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Makecontainer {
	public void general(String rootPath) {
		
		try {
			File Container=new File(rootPath+"/META-INF/container.xml");
			File metaInf=new File(rootPath+"/META-INF");
			if(!Container.exists()) {
				if(!metaInf.exists()) {
					metaInf.mkdirs();
				}
			Container.createNewFile();
			}
			BufferedWriter writer=new BufferedWriter(new FileWriter(Container));
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
					+ "<container xmlns=\"urn:oasis:names:tc:opendocument:xmlns:container\" version=\"1.0\">\r\n"
					+ "<rootfiles>\r\n"
					+ "<rootfile full-path=\"OPS/fb.opf\" media-type=\"application/oebps-package+xml\"/>\r\n"
					+ "</rootfiles>\r\n"
					+ "</container>");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
//		BufferedWriter writer=new BufferedWriter(new FileWriter(Minetype));
//		writer.write("application/epub+zip");
//		writer.flush();
//		writer.close();
	}

