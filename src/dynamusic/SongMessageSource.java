package dynamusic;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import atg.dms.patchbay.MessageSource;
import atg.dms.patchbay.MessageSourceContext;

/**

This component fires a NewSongMessage event using JMS.

Note that since it makes use of DMS and PatchBay it does not have any knowledge
of where the message is being sent.  See the dynamoMessagingSystem.xml file
for the destination of these messages (ultimately they will go to the ScenarioManager
component).
*/
public class SongMessageSource extends atg.nucleus.GenericService implements MessageSource 
{
    private MessageSourceContext mContext;
    private boolean mStarted = false;	
    
    // These methods implement the MessageSource interface
    public void setMessageSourceContext (MessageSourceContext pContext)
    { mContext = pContext; }
    public void startMessageSource () 
    { mStarted = true; }
    public void stopMessageSource () 
    { mStarted = false; }    
    
    // This method will send a message
    public void fireMessage(String pSongId, String pSongGenre, String pTitle)
        throws JMSException
    {
       if (mStarted) {
    	   ObjectMessage msg = mContext.createObjectMessage();
    	   msg.setJMSType("NewSongMessage");
    	   NewSongMessage nsm = new NewSongMessage();
    	   nsm.setSongId(pSongId);
    	   nsm.setSongGenre(pSongGenre);
    	   nsm.setTitle(pTitle);
    	   msg.setObject(nsm);
    	   if(isLoggingDebug()){
    		   logDebug("SongMessageSource...sending message: " + msg );
    	   }
    	   mContext.sendMessage(msg);
       }
       else {
    	   if (isLoggingDebug()) {
    		   logDebug("fire message called but message source not yet started");
    	   }
       }
    }    	
}