package org.zucc.lq.fun.stuff.logging.example.logging.en;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.zucc.lq.fun.stuff.logging.example.logger.AppLogger;

import java.util.Locale;

public class FunStuffLoggingTestEN {
    @BeforeAll
    public static void setup() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Test
    public void test() {
        AppLogger.LOGGER.appVersion("fun stuff jboss logging", 0,0,1,"SNAPSHOT");
    }
}
