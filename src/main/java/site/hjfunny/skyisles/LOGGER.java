package site.hjfunny.skyisles;

import java.util.logging.Logger;

public class LOGGER {
    public static Logger logger;

    public static void debug(String message) {
        logger.info(message);
    }
}
