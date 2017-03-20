package logger;

import java.util.logging.Level;

/**
 * Created by Berry-PC on 20/03/2017.
 */
public class Logger {

    private Logger() {
        //Empty private constructor to prevent intellij from automatically creating a public one.
        //Preventing a public constructor, since this is a utility class (using static methods only).
    }

    public static void log (String msg) {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(logger.Logger.class.getName());
        logger.log(Level.ALL, msg);
    }

    public static void log (Exception x) {
        java.util.logging.Logger logger = java.util.logging.Logger.getLogger(logger.Logger.class.getName());
        logger.log(Level.ALL, x.getMessage());
    }
}
