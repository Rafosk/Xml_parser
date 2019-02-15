package Xml_parser.Xml_parser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args ) throws IOException, ParserConfigurationException, SAXException
    {
    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	String fileE = FileUtils.readFileToString(new File("C:\\Users\\Rafal.Krakiewicz\\Desktop\\praca\\psi\\Translate_1_to_CLS4Text.xml"), StandardCharsets.UTF_8);
    	//String fileE = FileUtils.readFileToString(new File("C:\\Users\\Rafal.Krakiewicz\\Desktop\\praca\\IR1200984\\us_ko.tmx"), StandardCharsets.UTF_8);
    	
		String xmlFile = "CN.xml";
		File file = new File("CN.xml");
		
		DocumentBuilder builder = factory.newDocumentBuilder();
		org.w3c.dom.Document doc = builder.parse(fileE);
		
    	NodeList nodes = doc.getElementsByTagName("seg");

    	for(int i = 0; i < nodes.getLength(); i++) {

    	    Node node = nodes.item(i);

    	    if(node != null) {

    	       System.out.println(node.getLocalName());

    	    }

    	}
    	
    	//System.out.println(fileE);
    	
    	//fileE = fileE.replaceAll("<\\?xml.*?page>", "<nt>$0</nt>");
    	//fileE = fileE.replaceAll("(\\[[A-Z]*(\\.?[A-Z]*)*\\|)", "<nt>$0<nt>");
    	/*fileE = fileE.replaceAll("(\\[\\[.*?\\|)", "<nt>$0</nt>");
    	
    	fileE = fileE.replaceAll("<hint>.*?</hint>", "<nt>$0</nt>");
    	fileE = fileE.replaceAll("(<title>)(((?!Attribute:).)*)(</title>)", "$1<nt>$2</nt>$4");
    	fileE = fileE.replaceAll("<title>Attribute:", "<title><nt>Attribute:</nt>");
    	//fileE = fileE.replaceAll("</title>", "<nt>$0</nt>");
    	fileE = fileE.replaceAll("<orgtitle>.*</orgtitle>", "<nt>$0</nt>");
    	fileE = fileE.replaceAll("<showhide>.*</showhide>", "<nt>$0</nt>");
    	fileE = fileE.replaceAll("<ns>.*</ns>", "<nt>$0</nt>");
    	//fileE = fileE.replaceAll("==.*==", "<nt>$0<nt>");
    	fileE = fileE.replaceAll("==?.((Value Range)|(Comments)|(Default)|(Operations)|(Attibutes)|(Special Points)|(References)|(Comments).).?==", "<nt>$0</nt>");
    	fileE = fileE.replaceAll("&lt;.*&gt;", "<nt>$0</nt>");
    	fileE = fileE.replaceAll("__[A-Z]*__", "<nt>$0</nt>");
    	fileE = fileE.replaceAll("\\[\\[Category.*\\]\\]", "<nt>$0</nt>");
    	fileE = fileE.replaceAll("\\{\\{PFooter\\|.*\\}\\}", "<nt>$0</nt>");
    	fileE = fileE.replaceAll("\\{\\{IFooter\\|.*\\}\\}", "<nt>$0</nt>");
    	fileE = fileE.replaceAll("\\{\\{\\:Enumlist.*\\}\\}", "<nt>$0</nt>");
    	*/
    	
    	
    	String result = "";
    	//String[] tmp = fileE.split("\\{\\{Table\\}\\}.*(\\n.*?)*\\|\\}");
    	//String[] tmp = fileE.split("\\{\\||\\|\\}");
    	int i = 0;
    	/*for (String string : tmp) {
    		if(i%2!=0 && i > 0) {
    			//
    			string = string.replaceAll("[A-Z][A-Z][A-Z]+\\s", "<tagTab>$0</tagTab>");
        		string = "{|" + string + "|}";	
    		}    		
    		FileUtils.writeStringToFile(new File("C:\\Users\\Rafal.Krakiewicz\\Desktop\\praca\\psi\\rt" + Integer.toString(i) + ".xml"), string, StandardCharsets.UTF_8);
    		i++;
    		if(i>1000) {
    			break;
    		}
    		
    		result = result + string;
		}*/
    	
    	FileUtils.writeStringToFile(new File("C:\\\\Users\\\\Rafal.Krakiewicz\\\\Desktop\\\\praca\\\\IR1200984\\\\de_ko_result.tmx"), fileE, StandardCharsets.UTF_8);
    	//FileUtils.writeStringToFile(new File("C:\\Users\\Rafal.Krakiewicz\\Desktop\\praca\\psi\\RTranslate_1_to_CLS4Text.xml"), fileE, StandardCharsets.UTF_8);
    }
    
    private Document parseXML(String filePath) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = (Document) db.parse(filePath);
        ((Node) doc.getDefaultRootElement()).normalize();
        return doc;
    }
    
    
}

