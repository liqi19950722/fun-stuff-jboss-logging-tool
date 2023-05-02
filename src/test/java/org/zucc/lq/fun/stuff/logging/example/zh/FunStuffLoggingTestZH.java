package org.zucc.lq.fun.stuff.logging.example.zh;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.zucc.lq.fun.stuff.logging.example.ErrorMessages;

import java.io.IOException;
import java.text.ParseException;
import java.util.Locale;

public class FunStuffLoggingTestZH {

    @BeforeAll
    public static void setup() {
        Locale.setDefault(Locale.CHINA);
    }

    @Nested
    class InternationalizedMessages {
        @Test
        public void test_message() {
            var version = ErrorMessages.msg.version(0, 0, 1, "SNAPSHOT");
            Assertions.assertEquals("版本 0.0.1-SNAPSHOT", version);
        }

        @Test
        public void test_message_via_supplier() {
            var version = ErrorMessages.msg.nullParam("name");
            Assertions.assertEquals("参数name不能为空", version.get());
        }

        @Test
        public void test_message_with_pos_annotation() {

            var message = ErrorMessages.msg.commutativePropertyOfAddition(1, 2);
            Assertions.assertEquals("加法交换律 1 + 2 = 2 + 1", message);
        }
    }

    @Nested
    class InternationalizedExceptions {


        @Test
        public void test_exception_with_arg_message() {
            var exception = ErrorMessages.msg.invalidValue(1);
            Assertions.assertEquals(IllegalArgumentException.class, exception.getClass());
            Assertions.assertEquals("值不合法 1", exception.getMessage());
        }

        @Test
        public void test_exception_with_message() {

            var exception = ErrorMessages.msg.fileAccessException();
            Assertions.assertEquals(IOException.class, exception.getClass());
            Assertions.assertEquals("文件打不开", exception.getMessage());
        }

        @Test
        public void test_exception_with_message_and_requires_parameters() {
            var exception = ErrorMessages.msg.dateWasInvalid("yyyy-MM-dd", 0);
            Assertions.assertEquals(ParseException.class, exception.getClass());
            Assertions.assertEquals("日期格式不合法 yyyy-MM-dd", exception.getMessage());
            Assertions.assertEquals(0, exception.getErrorOffset());
        }

        @Test
        public void test_exception_with_function_parameter() {

            var exception = ErrorMessages.msg.operationFailed(UnsupportedOperationException::new, "IO");
            Assertions.assertInstanceOf(RuntimeException.class, exception);
            Assertions.assertEquals("IO执行失败", exception.getMessage());
        }

        @Test
        public void test_exception_with_cause_throwable() {

            var cause = new IOException();
            var exception = ErrorMessages.msg.operationFailed(UnsupportedOperationException::new, cause, "IO");
            Assertions.assertInstanceOf(RuntimeException.class, exception);
            Assertions.assertSame(cause, exception.getCause());
            Assertions.assertEquals("IO执行失败", exception.getMessage());
        }
    }
}
