package edu.lclark.orego.util;

import java.io.File;
import java.util.logging.*;

/**
 * Default logger implementation for CLI
 */
public class FileLogSink implements LogSink{
    private static Logger logger = null;

    public FileLogSink(){}

    public FileLogSink(String directory) {
        FileLogSink.setFilePath(directory);
    }

    /**
     * Sets logging to appear in a timestamped file in directory. Behavior is
     * undefined if two instances of Orego are launched at the same millisecond.
     */
    public static void setFilePath(String directory) {
        logger = Logger.getLogger("orego-default");
        new File(directory).mkdir();
        directory += File.separator + Logging.timeStamp(false) + ".log";
        try {
            final FileHandler handler = new FileHandler(directory);
            handler.setFormatter(new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return record.getMessage() + "\n";
                }
            });
            logger.addHandler(handler);
            logger.setLevel(Level.ALL);
            logger.setUseParentHandlers(false);
        } catch (final Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Logs a message at the default INFO level.
     *
     * @see Logger#log(Level, String)
     */
    @Override
    public void log(String message) {
        if (FileLogSink.logger == null) return;
        logger.log(Level.INFO, message);
    }
}
