package org.zucc.lq.fun.stuff.logging.example.en;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.zucc.lq.fun.stuff.logging.example.ErrorMessages;

import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;

public class FunStuffLoggingTestEN {

    @BeforeAll
    public static void setup() {
        Locale.setDefault(Locale.ENGLISH);
    }

    @Nested
    class InternationalizedMessages {
        @Test
        public void test_message() {
            var version = ErrorMessages.msg.version(0, 0, 1, "SNAPSHOT");
            Assertions.assertEquals("Version 0.0.1-SNAPSHOT", version);
        }

        @Test
        public void test_message_via_supplier() {
            var version = ErrorMessages.msg.nullParam("name");
            Assertions.assertEquals("Parameter name cannot be null", version.get());
        }

        @Test
        public void test_message_with_pos_annotation() {

            var message = ErrorMessages.msg.commutativePropertyOfAddition(1, 2);
            Assertions.assertEquals("Commutative Property of Addition 1 + 2 = 2 + 1", message);
        }
    }

    @Nested
    class InternationalizedExceptions {
        @Test
        public void test_exception_with_arg_message() {

            var version = ErrorMessages.msg.invalidValue(1);
            Assertions.assertEquals(IllegalArgumentException.class, version.getClass());
            Assertions.assertEquals("Value '1' is invalid", version.getMessage());
        }

        @Test
        public void test_exception_with_message() {

            var exception = ErrorMessages.msg.fileAccessException();
            Assertions.assertEquals(IOException.class, exception.getClass());
            Assertions.assertEquals("The file could not be opened.", exception.getMessage());
        }

        @Test
        public void test_exception_with_message_and_requires_parameters() {

            var exception = ErrorMessages.msg.dateWasInvalid("yyyy-MM-dd", 0);
            Assertions.assertEquals(ParseException.class, exception.getClass());
            Assertions.assertEquals("Date string 'yyyy-MM-dd' was invalid.", exception.getMessage());
            Assertions.assertEquals(0, exception.getErrorOffset());
        }


        @Test
        public void test_exception_with_function_parameter() {

            var exception = ErrorMessages.msg.operationFailed(UnsupportedOperationException::new, "IO");
            Assertions.assertInstanceOf(RuntimeException.class, exception);
            Assertions.assertEquals("Operation 'IO' failed.", exception.getMessage());
        }

        @Test
        public void test_exception_with_cause_throwable() {

            var cause = new IOException();
            var exception = ErrorMessages.msg.operationFailed(UnsupportedOperationException::new, cause, "IO");
            Assertions.assertInstanceOf(RuntimeException.class, exception);
            Assertions.assertSame(cause, exception.getCause());
            Assertions.assertEquals("Operation 'IO' failed.", exception.getMessage());
        }
    }
}
