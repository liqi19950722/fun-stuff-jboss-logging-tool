package org.zucc.lq.fun.stuff.logging.example.logger;

import org.jboss.logging.BasicLogger;
import org.jboss.logging.Logger;
import org.jboss.logging.annotations.LogMessage;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageLogger;
import org.jboss.logging.annotations.Once;

import java.util.Locale;

@MessageLogger(projectCode = "ErrorMsg")
public interface AppLogger extends BasicLogger {
    AppLogger LOGGER = Logger.getMessageLogger(AppLogger.class, AppLogger.class.getPackage().getName());

    @LogMessage
    @Once
    @Message("%s version %d.%d.%d.%s")
    void appVersion(CharSequence AppName, int major, int minor, int macro, String rel);


}
