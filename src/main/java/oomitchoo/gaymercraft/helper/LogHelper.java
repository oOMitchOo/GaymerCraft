package oomitchoo.gaymercraft.helper;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

/**
 * Created by oOMitchOo on 14.10.2016.
 */

public class LogHelper {
    private static Logger modLogger;

    public static void setModLogger(Logger modLogger) {LogHelper.modLogger = modLogger;}

    public static void log(Level loglevel, Object object){
        modLogger.log(loglevel, object);
    }

    public static void all(Object object){
        log(Level.ALL, object);
    }

    public static void debug(Object object){
        log(Level.DEBUG, object);
    }

    public static void error(Object object){
        log(Level.ERROR, object);
    }

    public static void fatal(Object object){
        log(Level.FATAL, object);
    }

    public static void info(Object object){
        log(Level.INFO, object);
    }

    public static void off(Object object){
        log(Level.OFF, object);
    }

    public static void trace(Object object){
        log(Level.TRACE, object);
    }

    public static void warn(Object object){
        log(Level.WARN, object);
    }
}
