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
import java.util.*;

/**
 * Created by Berry-PC on 25/04/2017.
 */
@ServerEndpoint("/socket/{username}")
public class WebSocket {

    private KwetterService kwetterService;

    @Inject
    public WebSocket(KwetterService ks) {
        kwetterService = ks;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) {
        SessionLister.getInstance().getSessionMap().put(username, session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("username") String username) {
        SessionLister.getInstance().getSessionMap().remove(username);
    }

    @OnMessage
    public void onMessage(String message, Session session, @PathParam("username") String username) {
        List<String> usernames = SessionLister.getInstance().getActiveUsers();

        Kwetteraar kwetteraar = kwetterService.getKwetteraarBaseService().getKwetteraarByProfielnaam(username);

        for (Kwetteraar volger : kwetteraar.getVolgers()) {
            if (usernames.contains(volger.getProfielNaam())) {
                SessionLister.getInstance().getSessionMap().get(volger.getProfielNaam()).getAsyncRemote().sendText(message);
            }
        }
        SessionLister.getInstance().getSessionMap().get(kwetteraar.getProfielNaam()).getAsyncRemote().sendText("You sent the following message: " + message);
    }
}
