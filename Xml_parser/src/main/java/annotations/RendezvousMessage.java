package annotations;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("message")
public class RendezvousMessage {
	
	@XStreamAlias("type")
	private int messageType;

	public RendezvousMessage(int messageType) {
		super();
		this.messageType = messageType;
	}

	
	
}
