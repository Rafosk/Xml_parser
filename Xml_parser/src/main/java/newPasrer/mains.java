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
import javax.xml.transform.Result;
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

import org.xml.sax.SAXException;

public class mains {

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

		String rootPath = "C:\\Users\\Rafal.Krakiewicz\\Desktop\\praca\\190215\\";

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		int r = 1;

		File source = null;
		File es = null;
		File hu = null;
		File pt = null;
		File fr = null;
		File it = null;
		File bg = null;
		File cn = null;

		if (r == 1) {
			source = new File(rootPath + "source\\SLCMSG_All_Languages.xml");
			es = new File(rootPath + "es\\SLCMSG_All_Languages.xml");
			hu = new File(rootPath + "hu\\SLCMSG_All_Languages.xml");
			pt = new File(rootPath + "pt\\SLCMSG_All_Languages.xml");
			fr = new File(rootPath + "fr\\SLCMSG_All_Languages.xml");
			it = new File(rootPath + "it\\SLCMSG_All_Languages.xml");
			bg = new File(rootPath + "bg\\SLCMSG_All_Languages.xml");
			cn = new File(rootPath + "cn\\SLCMSG_All_Languages.xml");
		} else {
			source = new File(rootPath + "source\\SLC_All_Languages.xml");
			es = new File(rootPath + "es\\SLC_All_Languages.xml");
			hu = new File(rootPath + "hu\\SLC_All_Languages.xml");
			pt = new File(rootPath + "pt\\SLC_All_Languages.xml");
			fr = new File(rootPath + "fr\\SLC_All_Languages.xml");
			it = new File(rootPath + "it\\SLC_All_Languages.xml");
			bg = new File(rootPath + "bg\\SLC_All_Languages.xml");
			cn = new File(rootPath + "cn\\SLC_All_Languages.xml");
		}

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

		Document docDE = dBuilder.parse(source);
		docDE.getDocumentElement().normalize();
		NodeList nodesDE = docDE.getElementsByTagName("DE");

		Document docEN = dBuilder.parse(source);
		docEN.getDocumentElement().normalize();
		NodeList nodesEN = docEN.getElementsByTagName("EN");

		Document docFR = dBuilder.parse(fr);
		docFR.getDocumentElement().normalize();
		NodeList nodesFR = docFR.getElementsByTagName("DE");

		Document docIT = dBuilder.parse(it);
		docIT.getDocumentElement().normalize();
		NodeList nodesIT = docIT.getElementsByTagName("DE");

		Document docBG = dBuilder.parse(bg);
		docBG.getDocumentElement().normalize();
		NodeList nodesBG = docBG.getElementsByTagName("DE");

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

		Document docCN = dBuilder.parse(cn);
		docCN.getDocumentElement().normalize();
		NodeList nodesCN = docCN.getElementsByTagName("DE");
		
		Document docSLC = dBuilder.parse(source);
		docSLC.getDocumentElement().normalize();
		NodeList nodesSLC = docSLC.getElementsByTagName("SLC");

		allert(nodesES);

		List<String> result = new ArrayList<String>();

		List<String> tmp = printNote(docSLC.getChildNodes(), result);

		System.out.println(nodesDE.getLength() + " " + nodesEN.getLength() + " " + nodesES.getLength() + " "
				+ nodesHU.getLength() + " " + nodesPT.getLength() + " " + tmp.size() + " " + nodesIT.getLength() + " "
				+ nodesBG.getLength() + nodesCN.getLength());

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("SLC");
			doc.appendChild(rootElement);

			addAllElements(nodesDE, nodesEN, nodesFR, nodesES, nodesHU, nodesPL, nodesPT, tmp, doc, rootElement,
					nodesIT, nodesBG, nodesCN);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source2 = new DOMSource(doc);

			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			Result result2 = null;

			if (r == 1) {
				result2 = new StreamResult(new File(rootPath + "SLCMSG_All_Languages.xml"));
			} else {
				result2 = new StreamResult(new File(rootPath + "SLC_All_Languages.xml"));
			}

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

	private static void allert(NodeList nodesES) {
		for (int i = 0; i < nodesES.getLength(); i++) {
			Node node = nodesES.item(i);
			if (node != null) {
				System.out.println(node.getTextContent());
			}
		}
	}

	private static void addAllElements(NodeList nodesDE, NodeList nodesEN, NodeList nodesFR, NodeList nodesES,
			NodeList nodesHU, NodeList nodesPL, NodeList nodesPT, List<String> tmp, Document doc, Element rootElement,
			NodeList nodesIT, NodeList nodesBG, NodeList nodesCN) {
		for (int i = 0; i < tmp.size(); i++) {

			Element slc = doc.createElement(tmp.get(i));
			rootElement.appendChild(slc);

			// firstname elements
			// Element elementDE = doc.createElement("DE");
			// elementDE.appendChild(doc.createTextNode(nodesDE.item(i).getTextContent().toString()));
			// staff.appendChild(elementDE);

			addElement(doc, "DE", nodesDE.item(i).getTextContent().toString(), slc);

			addElement(doc, "EN", nodesEN.item(i).getTextContent().toString(), slc);

			addElement(doc, "FR", nodesFR.item(i).getTextContent().toString(), slc);

			addElement(doc, "IT", nodesIT.item(i).getTextContent().toString(), slc);

			addElement(doc, "PT", nodesPT.item(i).getTextContent().toString(), slc);

			addElement(doc, "ES", nodesES.item(i).getTextContent().toString(), slc);

			addElement(doc, "HU", nodesHU.item(i).getTextContent().toString(), slc);

			addElement(doc, "PL", nodesPL.item(i).getTextContent().toString(), slc);

			addElement(doc, "BG", nodesBG.item(i).getTextContent().toString(), slc);

			addElement(doc, "ZH", nodesCN.item(i).getTextContent().toString(), slc);

		}
	}

	private static void addElement(Document doc, String tagName, String value, Element staff) {
		Element element = doc.createElement(tagName);
		element.appendChild(doc.createTextNode(value));
		staff.appendChild(element);
	}

	private static List<String> printNote(NodeList nodeList, List<String> result) {

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

				if (tempNode.getNodeName().contains("MSG")) {
					result.add(tempNode.getNodeName());
				}
			}

		}

		return result;
	}
}
