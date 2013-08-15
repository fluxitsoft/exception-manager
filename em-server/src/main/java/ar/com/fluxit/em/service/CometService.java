package ar.com.fluxit.em.service;

import org.cometd.bayeux.ChannelId;
import org.cometd.bayeux.server.Authorizer;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ConfigurableServerChannel;
import org.cometd.bayeux.server.ServerChannel;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.java.annotation.Configure;
import org.cometd.java.annotation.Service;

import com.google.gson.Gson;

@Service
public final class CometService {
	

	
	public static CometService instance; 
	
	public CometService() {
		//Migrar a Spring 
		instance = this;
	}

	public static final String KEY_FROM = "from";
	public static final String KEY_SUBJECT = "subject";
	public static final String KEY_MESSAGE = "message_inbox";

	@Configure("/inbox/**")
	void configure(ConfigurableServerChannel channel) {
		channel.addAuthorizer(new Authorizer() {
			public Result authorize(Operation operation, ChannelId channel,
					ServerSession session, ServerMessage message) {
				return  Result.grant();
			}
		});
	}
	
	@javax.inject.Inject
	private BayeuxServer bayeux;
	
	public void publish(String user, Object value){
		
		ServerChannel clientChannel = bayeux.getChannel("/inbox");
		
		Gson gson = new Gson();
		clientChannel.publish(null, gson.toJson(value) , null);
		
	}

}