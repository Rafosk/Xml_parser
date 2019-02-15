package Xml_parser.Xml_parser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import com.thoughtworks.xstream.XStream;

public class main {

	public static void main(String[] args) throws IOException {

	
		String file = "C:\\Users\\Rafal.Krakiewicz\\Desktop\\praca\\IR1200984\\us_ko2.tmx";

		
		
		Person joe = new Person("Joe", "wallnes");
		PhoneNumber ph = new PhoneNumber(123, "1234-456");
		joe.setPhone(new PhoneNumber(123, "1234-456"));
		joe.setFax(new PhoneNumber(123, "9999-999"));

		XStream xstream = new XStream();

		xstream.alias("person", Person.class);
		xstream.alias("phonenumber", PhoneNumber.class);
		
		String xml = xstream.toXML(joe);
		System.out.println(xml);
		
		Writer out = new FileWriter("C:\\Users\\Rafal.Krakiewicz\\Desktop\\praca\\IR1200984\\us_ko2.tmx");
		xstream.toXML(joe, out);
		
	}

}
