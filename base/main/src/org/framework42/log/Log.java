package org.framework42.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {

    static String logPath = null;

    static DebugLevel currentDebugLevel = DebugLevel.INFO;

    static LogAppender logTo = LogAppender.FILE;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void debug(Exception exception) {

        write(DebugLevel.DEBUG, exception);
    }

    public static void debug(String message) {

        write(DebugLevel.DEBUG, message);
    }

    public static void info(Exception exception) {

        write(DebugLevel.INFO, exception);
    }

    public static void info(String message) {

        write(DebugLevel.INFO, message);
    }

    public static void error(Exception exception) {

        write(DebugLevel.ERROR, exception);
    }

    public static void error(String message) {

        write(DebugLevel.ERROR, message);
    }

    public static void fatal(Exception exception) {

        write(DebugLevel.FATAL, exception);
    }

    public static void fatal(String message) {

        write(DebugLevel.FATAL, message);
    }

    private static void write(DebugLevel debugLevel, Exception exception) {

        if(currentDebugLevel.getLevel()<=debugLevel.getLevel()) {

            StringBuilder sb = new StringBuilder();

            sb.append(exception.getClass().getName());
            sb.append("\n");

            for (StackTraceElement element : exception.getStackTrace()) {

                sb.append("\t");
                sb.append(element.toString());
                sb.append("\n");
            }

            write(debugLevel, sb.toString());
        }
    }

    private static synchronized void write(DebugLevel debugLevel, String message) {

        if(currentDebugLevel.getLevel()<=debugLevel.getLevel()) {

            try {

                StringBuilder sb = new StringBuilder();

                if (debugLevel == DebugLevel.FATAL) {

                    sb.append("[ F A T A L ! ! ! ]\n");
                }

                sb.append("[");
                sb.append(LocalDateTime.now().format(formatter));
                sb.append("]");
                sb.append("[");
                sb.append(debugLevel.name());
                sb.append("] - ");
                sb.append(message);
                sb.append("\n");

                if (logTo == LogAppender.CONSOLE) {

                    System.out.print(sb.toString());

                } else if (logTo == LogAppender.FILE) {

                    String path = new File(".").getCanonicalPath();

                    if(logPath!=null) {path=logPath;}

                    FileWriter writer = new FileWriter( path+ "/log-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".txt", true);

                    writer.write(sb.toString());
                    writer.close();
                }

            } catch (IOException e) {

                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

}
