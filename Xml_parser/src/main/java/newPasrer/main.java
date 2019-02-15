package newPasrer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class main {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		File source = new File("C:\\Users\\Rafal.Krakiewicz\\Desktop\\praca\\190215\\source\\SLC_All_Languages.xml");
		File es = new File("C:\\Users\\Rafal.Krakiewicz\\Desktop\\praca\\190215\\es\\SLC_All_Languages.xml");
		File hu = new File("C:\\Users\\Rafal.Krakiewicz\\Desktop\\praca\\190215\\hu\\SLC_All_Languages.xml");
		File pt = new File("C:\\Users\\Rafal.Krakiewicz\\Desktop\\praca\\190215\\pt\\SLC_All_Languages.xml");

		// String xmlFile = "CN.xml";
		// File file = new File("CN.xml");

		// DocumentBuilder builder = factory.newDocumentBuilder();
		// org.w3c.dom.Document doc2 = builder.parse(source);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document docDE = dBuilder.parse(source);
		docDE.getDocumentElement().normalize();
		NodeList nodesDE = docDE.getElementsByTagName("DE");

		Document docEN = dBuilder.parse(source);
		docEN.getDocumentElement().normalize();
		NodeList nodesEN = docEN.getElementsByTagName("EN");
		
		Document docES = dBuilder.parse(es);
		docES.getDocumentElement().normalize();
		NodeList nodesES = docES.getElementsByTagName("DE");

		Document docHU = dBuilder.parse(hu);
		docHU.getDocumentElement().normalize();
		NodeList nodesHU = docHU.getElementsByTagName("DE");

		Document docPL = dBuilder.parse(source);
		docPL.getDocumentElement().normalize();
		NodeList nodesPL = docHU.getElementsByTagName("PL");
		
		Document docPT = dBuilder.parse(pt);
		docPT.getDocumentElement().normalize();
		NodeList nodesPT = docPT.getElementsByTagName("DE");

		Document docSLC = dBuilder.parse(source);
		docSLC.getDocumentElement().normalize();
		NodeList nodesSLC = docSLC.getElementsByTagName("SLC");

		for (int i = 0; i < nodesES.getLength(); i++) {

			Node node = nodesES.item(i);

			if (node != null) {
				 System.out.println(node.getTextContent());
				// System.out.println(node.getChildNodes());
			}

		}

		List<String> result = new ArrayList<String>();

		List<String> tmp = printNote(docSLC.getChildNodes(), result);

		System.out.println(nodesDE.getLength() + " " + nodesEN.getLength()+" " + nodesES.getLength() + " " + nodesHU.getLength() + " "
				+ nodesPT.getLength() + " " + tmp.size());

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("SLC");
			doc.appendChild(rootElement);
			
			for (int i = 0; i < tmp.size(); i++) {

				// staff elements
				Element staff = doc.createElement(tmp.get(i));
				rootElement.appendChild(staff);

				// firstname elements
				Element elementDE = doc.createElement("DE");
				elementDE.appendChild(doc.createTextNode(nodesDE.item(i).getTextContent().toString()));
				staff.appendChild(elementDE);

				Element elementEN = doc.createElement("EN");
				elementEN.appendChild(doc.createTextNode(nodesEN.item(i).getTextContent().toString()));
				staff.appendChild(elementEN);
				
				Element elementFR = doc.createElement("FR");
				elementFR.appendChild(doc.createTextNode(" "));
				staff.appendChild(elementFR);
				
				Element elementIT = doc.createElement("IT");
				elementIT.appendChild(doc.createTextNode(" "));
				staff.appendChild(elementIT);
				
				Element elementPT = doc.createElement("PT");
				elementPT.appendChild(doc.createTextNode(nodesPT.item(i).getTextContent().toString()));
				staff.appendChild(elementPT);
				
				Element elementES = doc.createElement("ES");
				elementES.appendChild(doc.createTextNode(nodesES.item(i).getTextContent().toString()));
				staff.appendChild(elementES);
				
				Element elementHU = doc.createElement("HU");
				elementHU.appendChild(doc.createTextNode(nodesHU.item(i).getTextContent().toString()));
				staff.appendChild(elementHU);
				
				Element elementPL = doc.createElement("PL");
				elementPL.appendChild(doc.createTextNode(nodesPL.item(i).getTextContent().toString()));
				staff.appendChild(elementPL);
				
				Element elementBG = doc.createElement("BG");
				elementBG.appendChild(doc.createTextNode(" "));
				staff.appendChild(elementBG);
				
				Element elementZH = doc.createElement("ZH");
				elementZH.appendChild(doc.createTextNode(" "));
				staff.appendChild(elementZH);

			}
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source2 = new DOMSource(doc);

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			StreamResult result2 = new StreamResult(
					new File("C:\\Users\\Rafal.Krakiewicz\\Desktop\\praca\\190215\\file.xml"));

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source2, result2);

			System.out.println("File saved!");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}

	private static List<String> printNote(NodeList nodeList, List<String> result) {
		// TODO Auto-generated method stub

		// List<String> result = new ArrayList<String>();

		for (int count = 0; count < nodeList.getLength(); count++) {

			Node tempNode = nodeList.item(count);

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

				// get node name and value
				// System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
				// System.out.println("Node Value =" + tempNode.getTextContent());

				if (tempNode.hasAttributes()) {

					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();

					for (int i = 0; i < nodeMap.getLength(); i++) {

						Node node = nodeMap.item(i);
						System.out.println("attr name : " + node.getNodeName());
						System.out.println("attr value : " + node.getNodeValue());

					}

				}

				if (tempNode.hasChildNodes()) {

					// loop again if has child nodes
					printNote(tempNode.getChildNodes(), result);

				}

				// System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");

				if (tempNode.getNodeName().contains("MSG")) {
					result.add(tempNode.getNodeName());
				}
			}

		}

		return result;
	}
}
