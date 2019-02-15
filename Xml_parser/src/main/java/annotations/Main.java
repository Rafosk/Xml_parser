package annotations;

import com.thoughtworks.xstream.XStream;

public class Main {

	public static void main(String[] args) {
		
		XStream xstream = new XStream();
		xstream.processAnnotations(RendezvousMessage.class);
		RendezvousMessage msg = new RendezvousMessage(15);
		System.out.println(xstream.toXML(msg));

	}

}
