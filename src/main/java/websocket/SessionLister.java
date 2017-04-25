package websocket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Berry-PC on 25/04/2017.
 */
public class SessionLister {

    private static SessionLister sessionLister = null;
    private static List<String> activeUsers;

    protected SessionLister() {
        activeUsers = new ArrayList<>();
    }

    public static SessionLister getInstance() {
        if (sessionLister == null) {
            sessionLister = new SessionLister();
        }
        return sessionLister;
    }

    public List<String> getActiveUsers() {
        return activeUsers;
    }

}
