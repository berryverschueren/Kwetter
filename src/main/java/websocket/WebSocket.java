package websocket;

import model.Kwetteraar;
import service.KwetterService;

import javax.inject.Inject;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

/**
 * Created by Berry-PC on 25/04/2017.
 */
@ServerEndpoint("/socket/{username}")
public class WebSocket {

//    private static Map<String, Session> allSessions =
//            Collections.synchronizedMap(new HashMap<String, Session>());
    private static Map<String, Session> allSessions;
    private KwetterService kwetterService;

    @Inject
    public WebSocket(KwetterService ks) {
        kwetterService = ks;
        allSessions = new HashMap<>();
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        allSessions.put(username, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        allSessions.remove(username);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String username) {
        List<String> usernames = SessionLister.getInstance().getActiveUsers();

        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(username);

        //synchronized (allSessions) {
            for (Kwetteraar volger : kwetteraar.getVolgers()) {
                if (usernames.contains(volger.getProfielNaam())) {
                    //push to client, the new kweet
                    try {
                        allSessions.get(volger.getProfielNaam()).getBasicRemote().sendText(message);
                    } catch (IOException ioe) {
                        System.out.println(ioe.getMessage());
                    }
                }
            }
        //}
    }
}
