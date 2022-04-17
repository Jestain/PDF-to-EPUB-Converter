package pdftostandardhtml;

import org.apache.pdfbox.pdfparser.PDFStreamParser;
import org.apache.pdfbox.pdfwriter.ContentStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDFontDescriptor;
import org.apache.pdfbox.util.PDFImageWriter;
import org.apache.pdfbox.util.PDFOperator;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;

import java.awt.image.BufferedImage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.List;


public class Pdf2Html extends PDFTextStripper {
    private BufferedWriter htmlFile;
    private String outputFileName;
    private int type = 0;
    private float zoom = (float) 2;
    private int marginTopBackground = 0;
    private int lastMarginTop = 0;
    private int max_gap = 15;
    float previousAveCharWidth = -1;
    private int resolution = 72; //default resolution
    private boolean needToStartNewSpan = false;
    private int lastMarginLeft = 0;
    private int lastMarginRight = 0;
    private int numberSpace = 0;
    private int sizeAllSpace = 0;
    private boolean addSpace;
    private int startXLine;
    private boolean wasBold = false;
    private boolean wasItalic = false;
    private int lastFontSizePx = 0;
    private String lastFontString = "";
    private StringBuffer currentLine = new StringBuffer();

 
    /**
     * Public constructor
     * @param outputFileName The html file
     * @param type represents how we are going to create the html file
     *                         0: we create a new block for every letters
     *                         1: we create a new block for every words
     *                         2: we create a new block for every line
     *                         3: we create a new block for every line - using a cache to set the word-spacing property
     * @param zoom 1.5 - 2 is a good range
     * @throws IOException
     */
    public Pdf2Html(String outputFileName, int type, float zoom) throws IOException {
        try {
            htmlFile = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(outputFileName), "UTF8"));

            String header = "<?xml version = \"1.0\" encoding =\"UTF-8\"?>"+"<!DOCTYPE html PUBLIC\r\n"
            		+ "	  \"-//W3C//DTD XHTML 1.0 Transitional//EN\"\r\n"
            		+ "	  \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"+"<html  xmlns = \"http://www.w3.org/1999/xhtml\" lang= \"en\" xml:lang = \"en\">" + "<head>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>" +"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1,user-scalable=0\"/>"+
                "<script>\r\n"
                + "        var offWidth = window.screen.width/1536; \r\n"
                + "        document.getElementsByTagName(\"html\")[0].style.fontSize = offWidth + 'px';\r\n"
                + "</script>"+"<title>Html file</title>" + "<link rel=\"stylesheet\" href=\"css/style.css\" />" +
                "</head>" + "<body>";
            htmlFile.write(header);
            this.type = type;
            this.zoom = zoom;
            this.outputFileName = outputFileName;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.err.println("Error: Unsupported encoding.");
            System.exit(1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Error: File not found.");
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: IO error, could not open html file.");
            System.exit(1);
        }
    }

    /**
     * Close the HTML file
     */
    public void closeFile() {
        try {
            htmlFile.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: IO error, could not close html file.");
            System.exit(1);
        }
    }

    /**
     * Convert a PDF file to HTML
     *
     * @param pathToPdf Path to the PDF file
     *
     * @throws IOException If there is an error processing the operation.
     */
    public void convertPdfToHtml(String pathToPdf) throws Exception {
        int positionDotPdf = pathToPdf.lastIndexOf(".pdf");

        if (positionDotPdf == -1) {
            System.err.println("File doesn't have .pdf extension");
            System.exit(1);
        }

        int positionLastSlash = outputFileName.lastIndexOf("/");

        if (positionLastSlash == -1) {
            positionLastSlash = 0;
        } else {
            positionLastSlash++;
        }

        String fileName = outputFileName.substring(positionLastSlash,outputFileName.lastIndexOf(".html"));

        PDDocument document = null;

        try {
            document = PDDocument.load(pathToPdf);

            if (document.isEncrypted()) {
                document.decrypt("");
            }

            List allPages = document.getDocumentCatalog().getAllPages();

            // Retrieve and save text in the HTML file
            for (int i = 0; i < allPages.size(); i++) {
                System.out.println("Processing page " + i);

                PDPage page = (PDPage) allPages.get(i);

                BufferedImage image = page.convertToImage(BufferedImage.TYPE_INT_RGB, resolution);
               
                htmlFile.write("<img class=\"background\" style=\"position: absolute; width: " +
                        (zoom * image.getWidth()) + "rem; height: " + (zoom * image.getHeight()) +
                        "rem; top left no-repeat; margin-top: " + marginTopBackground + "rem\""+" src=\"images/"  + fileName + (int) (i + 1) +
                        ".jpeg"+"\""+" />");
                
                htmlFile.write("<div>");
                    marginTopBackground += (zoom * image.getHeight());
                marginTopBackground += (zoom * image.getHeight());

                PDStream contents = page.getContents();

                if (contents != null) {
                    this.processStream(page, page.findResources(), page.getContents().getStream());
                }

                htmlFile.write("</span>");
                htmlFile.write("</div></body></html>");
            }

         // Remove the text
            for (int i = 0; i < allPages.size(); i++) {
                PDPage page = (PDPage) allPages.get(i);
                PDFStreamParser parser = new PDFStreamParser(page.getContents());
                parser.parse();

                List tokens = parser.getTokens();
                List newTokens = new ArrayList();

                for (int j = 0; j < tokens.size(); j++) {
                    Object token = tokens.get(j);

                    if (token instanceof PDFOperator) {
                        PDFOperator op = (PDFOperator) token;
                        if (op.getOperation().equals("TJ") || op.getOperation().equals("Tj")) {
                            newTokens.remove(newTokens.size() - 1);

                            continue;
                        }
                    }

                    newTokens.add(token);
                }

                PDStream newContents = new PDStream(document);
                ContentStreamWriter writer = new ContentStreamWriter(newContents.createOutputStream());
                writer.writeTokens(newTokens);
                
                //newContents.addCompression(); //Looks like it faster without the compression, but no extensive tests have been run.
                page.setContents(newContents);
            }

            //Save background images
            //TODO: Do not save the image if it's blank. (Retrieve the text of one page, remove it from the document, get the image, check if it's blank, save it or not and write the html file)
            PDFImageWriter imageWriter = new PDFImageWriter();

            String imageFormat = "jpeg";
            String password = "";
            int startPage = 1;
            int endPage = Integer.MAX_VALUE;
            String outputPrefix = outputFileName.substring(0, outputFileName.lastIndexOf("/") + 1) +"/images/"+
                 "/";
//            fileName +
            int imageType = BufferedImage.TYPE_INT_RGB;

            //new dir
            File newdir = new File(outputPrefix);

            if (!newdir.exists()) {
                newdir.mkdirs();
            }

            boolean success = imageWriter.writeImage(document, imageFormat, password, startPage,endPage, outputPrefix + fileName, imageType, (int) (resolution * zoom));
            System.out.println(document+","+ imageFormat+","+  password+","+  startPage+","+ endPage+","+  outputPrefix + fileName+","+  imageType+","+  (int) (resolution * zoom));
//            File newImgName=new File(outputPrefix+"/"+fileName);
            if (!success) {
                System.err.println("Error: no writer found for image format '" + imageFormat + "'");
                System.exit(1);
            }
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }

    /**
     * A method provided as an event interface to allow a subclass to perform
     * some specific functionality when text needs to be processed.
     *
     * @param text The text to be processed
     */
    protected void processTextPosition(TextPosition text) {
        try {
            int marginLeft = (int) ((text.getXDirAdj()) * zoom);
            int fontSizePx = Math.round(text.getFontSizeInPt() / 72 * resolution * zoom);
            int marginTop = (int) (((text.getYDirAdj()) * zoom) - fontSizePx);

            String fontString = "";
            PDFont font = text.getFont();
            PDFontDescriptor fontDescriptor = font.getFontDescriptor();

            if (fontDescriptor != null) {
                fontString = fontDescriptor.getFontName();
            } else {
                fontString = "";
            }

            int indexPlus = fontString.indexOf("+");

            if (indexPlus != -1) {
                fontString = fontString.substring(indexPlus + 1);
            }

            boolean isBold = fontString.contains("Bold");
            boolean isItalic = fontString.contains("Italic");

            int indexDash = fontString.indexOf("-");

            if (indexDash != -1) {
                fontString = fontString.substring(0, indexDash);
            }

            int indexComa = fontString.indexOf(",");

            if (indexComa != -1) {
                fontString = fontString.substring(0, indexComa);
            }

            switch (type) {
            case 0:
                renderingSimple(text, marginLeft, marginTop, fontSizePx, fontString, isBold,
                    isItalic);

                break;

            case 1:
                renderingGroupByWord(text, marginLeft, marginTop, fontSizePx, fontString, isBold,
                    isItalic);

                break;

            case 2:
                renderingGroupByLineNoCache(text, marginLeft, marginTop, fontSizePx, fontString,
                    isBold, isItalic);

                break;

            case 3:
                renderingGroupByLineWithCache(text, marginLeft, marginTop, fontSizePx, fontString,
                    isBold, isItalic);

                break;

            default:
                renderingSimple(text, marginLeft, marginTop, fontSizePx, fontString, isBold,
                    isItalic);

                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method that given one character is going to write it in the HTML file.
     *
     * @param text
     * @param marginLeft
     * @param marginTop
     * @param fontSizePx
     * @param fontString
     * @param isBold
     * @param isItalic
     * @throws IOException
    
     */
    private void renderingSimple(TextPosition text, int marginLeft, int marginTop, int fontSizePx,
        String fontString, boolean isBold, boolean isItalic) throws IOException {
        htmlFile.write("<span style=\"position: absolute; margin-left:" + marginLeft +
            "rem; margin-top: " + marginTop + "rem; font-size: " + fontSizePx + "rem; font-family:" +
            fontString + ";");

        if (isBold) {
            htmlFile.write("font-weight: bold;");
        }

        if (isItalic) {
            htmlFile.write("font-style: italic;");
        }

        htmlFile.write("\">");

        htmlFile.write(text.getCharacter());

        htmlFile.write("</span>");
    }

    /**
     * The method that given one character is going to write it only if it's the end of a word in the HTML file.
     *
     * @param text
     * @param marginLeft
     * @param marginTop
     * @param fontSizePx
     * @param fontString
     * @param isBold
     * @param isItalic
     * @throws IOException
    
     */
    private void renderingGroupByWord(TextPosition text, int marginLeft, int marginTop,
        int fontSizePx, String fontString, boolean isBold, boolean isItalic)
        throws IOException {
        if (lastMarginTop == marginTop) {
            if ((needToStartNewSpan) || (wasBold != isBold) || (wasItalic != isItalic) ||
                    (lastFontSizePx != fontSizePx) || (lastMarginLeft > marginLeft) ||
                    ((marginLeft - lastMarginRight) > max_gap)) {
                if (lastMarginTop != 0) {
                    htmlFile.write("</span>");
                }

                htmlFile.write("<span style=\"position: absolute; margin-left:" + marginLeft +
                    "rem; margin-top: " + marginTop + "rem; font-size: " + fontSizePx +
                    "rem; font-family:" + fontString + ";");

                if (isBold) {
                    htmlFile.write("font-weight: bold;");
                }

                if (isItalic) {
                    htmlFile.write("font-style: italic;");
                }

                htmlFile.write("\">");

                needToStartNewSpan = false;
            }

            if (text.getCharacter().equals(" ")) {
                htmlFile.write(" ");

                needToStartNewSpan = true;
            } else {
                htmlFile.write(text.getCharacter().replace("<", "&lt;").replace(">", "&gt;"));
            }
        } else {
            if (text.getCharacter().equals(" ")) {
                htmlFile.write("&nbsp;");
                needToStartNewSpan = true;
            } else {
                needToStartNewSpan = false;

                if (lastMarginTop != 0) {
                    htmlFile.write("</span>");
                }

                htmlFile.write("<span style=\"position: absolute; margin-left:" + marginLeft +
                    "rem; margin-top: " + marginTop + "rem; font-size: " + fontSizePx +
                    "rem; font-family:" + fontString + ";");

                if (isBold) {
                    htmlFile.write("font-weight: bold;");
                }

                if (isItalic) {
                    htmlFile.write("font-style: italic;");
                }

                htmlFile.write("\">");

                htmlFile.write(text.getCharacter().replace("<", "&lt;").replace(">", "&gt;"));
            }

            lastMarginTop = marginTop;
        }

        lastMarginLeft = marginLeft;
        lastMarginRight = (int) (marginLeft + text.getWidth());

        wasBold = isBold;
        wasItalic = isItalic;
        lastFontSizePx = fontSizePx;
    }

    /**
     * The method that given one character is going to write it only if it's the end of a line in the HTML file.
     *
     * @param text
     * @param marginLeft
     * @param marginTop
     * @param fontSizePx
     * @param fontString
     * @param isBold
     * @param isItalic
     * @throws IOException
    
     */
    private void renderingGroupByLineNoCache(TextPosition text, int marginLeft, int marginTop,
        int fontSizePx, String fontString, boolean isBold, boolean isItalic)
        throws IOException {
        if (lastMarginTop == marginTop) {
            if (lastMarginLeft > marginLeft) {
                htmlFile.write("</span>");
                htmlFile.write("<span style=\"position: absolute; margin-left:" + marginLeft +
                    "rem; margin-top: " + marginTop + "rem; font-size: " + fontSizePx +
                    "rem; font-family:" + fontString + ";");

                if (isBold) {
                    htmlFile.write("font-weight: bold;");
                }

                if (isItalic) {
                    htmlFile.write("font-style: italic;");
                }

                htmlFile.write("\">");
            }

            lastMarginTop = marginTop;
        } else {
            if (lastMarginTop != 0) {
                htmlFile.write("</span>");
            }

            htmlFile.write("<span style=\"position: absolute; margin-left:" + marginLeft +
                "rem; margin-top: " + marginTop + "rem; font-size: " + fontSizePx +
                "rem; font-family:" + fontString + ";");

            if (isBold) {
                htmlFile.write("font-weight: bold;");
            }

            if (isItalic) {
                htmlFile.write("font-style: italic;");
            }

            htmlFile.write("\">");

            lastMarginTop = marginTop;
        }

        htmlFile.write(text.getCharacter().replace("<", "&lt;").replace(">", "&gt;"));
        lastMarginLeft = marginLeft;
    }

    /**
     * The method that given one character is going to write it only if it's the end of a line in the HTML file.
     * A cache is used to set the word-spacing property.
     *
     * @param text
     * @param marginLeft
     * @param marginTop
     * @param fontSizePx
     * @param fontString
     * @param isBold
     * @param isItalic
     * @throws IOException
    
     */
    private void renderingGroupByLineWithCache(TextPosition text, int marginLeft, int marginTop,
        int fontSizePx, String fontString, boolean isBold, boolean isItalic)
        throws IOException {
        if ((marginLeft - lastMarginRight) > text.getWidthOfSpace()) {
            currentLine.append(" ");

            sizeAllSpace += (marginLeft - lastMarginRight);
            numberSpace++;
            addSpace = false;
        }

        if ((lastMarginTop != marginTop) || (!lastFontString.equals(fontString)) ||
                (wasBold != isBold) || (wasItalic != isItalic) || (lastFontSizePx != fontSizePx) ||
                (lastMarginLeft > marginLeft) || ((marginLeft - lastMarginRight) > 150)) {
            if (lastMarginTop != 0) {
                boolean display = true;

                // if the bloc is empty, we do not display it (for a lighter result)
                if (currentLine.length() == 1) {
                    char firstChar = currentLine.charAt(0);

                    if (firstChar == ' ') {
                        display = false;
                    }
                }

                if (display) {
                    if (numberSpace != 0) {
                        int spaceWidth = Math.round((((float) sizeAllSpace) / ((float) numberSpace)) -
                                text.getWidthOfSpace());
                        htmlFile.write("<span style=\"word-spacing:" + spaceWidth +
                            "rem;position: absolute; margin-left:" + startXLine +
                            "rem; margin-top: " + lastMarginTop + "rem; font-size: " +
                            lastFontSizePx + "rem; font-family:" + lastFontString + ";");
                    } else {
                        htmlFile.write("<span style=\"position: absolute; margin-left:" +
                            startXLine + "rem; margin-top: " + lastMarginTop + "rem; font-size: " +
                            lastFontSizePx + "rem; font-family:" + lastFontString + ";");
                    }

                    if (wasBold) {
                        htmlFile.write("font-weight: bold;");
                    }

                    if (wasItalic) {
                        htmlFile.write("font-style: italic;");
                    }

                    htmlFile.write("\">");

                    htmlFile.write(currentLine.toString());

                    htmlFile.write("</span>\n");
                }
            }

            numberSpace = 0;
            sizeAllSpace = 0;

            currentLine = new StringBuffer();
            startXLine = marginLeft;
            lastMarginTop = marginTop;
            wasBold = isBold;
            wasItalic = isItalic;
            lastFontSizePx = fontSizePx;
            lastFontString = fontString;

            addSpace = false;
        } else {
            int sizeCurrentSpace = (int) (marginLeft - lastMarginRight - text.getWidthOfSpace());

            if (sizeCurrentSpace > 5) {
                if (lastMarginTop != 0) {
                    if (numberSpace != 0) {
                        int spaceWidth = Math.round((((float) sizeAllSpace) / ((float) numberSpace)) -
                                text.getWidthOfSpace());
                        htmlFile.write("<span style=\"word-spacing:" + spaceWidth +
                            "rem;position: absolute; margin-left:" + startXLine +
                            "rem; margin-top: " + lastMarginTop + "rem; font-size: " +
                            lastFontSizePx + "rem; font-family:" + lastFontString + ";");
                    } else {
                        htmlFile.write("<span style=\"position: absolute; margin-left:" +
                            startXLine + "rem; margin-top: " + lastMarginTop + "rem; font-size: " +
                            lastFontSizePx + "rem; font-family:" + lastFontString + ";");
                    }

                    if (wasBold) {
                        htmlFile.write("font-weight: bold;");
                    }

                    if (wasItalic) {
                        htmlFile.write("font-style: italic;");
                    }

                    htmlFile.write("\">");

                    htmlFile.write(currentLine.toString());

                    htmlFile.write("</span>\n");
                }

                numberSpace = 0;
                sizeAllSpace = 0;

                currentLine = new StringBuffer();
                startXLine = marginLeft;
                lastMarginTop = marginTop;
                wasBold = isBold;
                wasItalic = isItalic;
                lastFontSizePx = fontSizePx;
                lastFontString = fontString;

                addSpace = false;
            } else {
                if (addSpace) {
                    currentLine.append(" ");

                    sizeAllSpace += (marginLeft - lastMarginRight);
                    numberSpace++;
                    addSpace = false;
                }
            }
        }

        if (text.getCharacter().equals(" ")) {
            addSpace = true;

            //sizeAllSpace += text.getWidthOfSpace();
        } else {
            currentLine.append(text.getCharacter().replace("<", "&lt;").replace(">", "&gt;"));
        }

        lastMarginLeft = marginLeft;
        lastMarginRight = (int) (marginLeft + (text.getWidth() * zoom));
    }
}
