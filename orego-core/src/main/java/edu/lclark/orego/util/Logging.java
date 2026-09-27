package edu.lclark.orego.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Logging {

    /**
     * Set a custom implementation of logging
     */
    private static LogSink logger;
    private static String previousTimeStamp = null;

    public static synchronized void log(String message) {
        if (logger == null) return;

        String stamp = Logging.timeStamp(false);
        logger.log(stamp + " thread "
                + Thread.currentThread().getId() + " " + message);

        if (previousTimeStamp != null && rawTime(stamp) - rawTime(previousTimeStamp) > 5) {
            logger.log(stamp + " thread "
                    + Thread.currentThread().getId() + " " + "LONG DELAY!");
        }
        previousTimeStamp = stamp;
    }

    /**
     * Sets the logger implementation.
     * @param loggerImpl
     */
    public static void setLogger(LogSink loggerImpl) {
        logger = loggerImpl;
    }

    /**
     * Returns a String representing the current date and time.
     *
     * @param nest
     *            If true, use File.separator instead of dashes to separate
     *            year, month, date, and time.
     */
    public static String timeStamp(boolean nest) {
        String punctuation;
        if (nest) {
            punctuation = File.separator;
        } else {
            punctuation = "-";
        }
        return new SimpleDateFormat("yyyy" + punctuation + "MM" + punctuation
                + "dd" + punctuation + "HH:mm:ss.SSS").format(new Date(System
                .currentTimeMillis()));
    }

    /**
     * Returns the time represented by stamp as a number of minutes since the
     * beginning of the day.
     */
    public static int rawTime(String stamp) {
        int hour = Integer.parseInt(stamp.substring(11, 13));
        int minute = Integer.parseInt(stamp.substring(14, 16));
        return 60 * hour + minute;
    }
}
