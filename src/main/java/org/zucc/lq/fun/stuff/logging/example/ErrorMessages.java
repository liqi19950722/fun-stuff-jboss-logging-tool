package org.zucc.lq.fun.stuff.logging.example;

import org.jboss.logging.Messages;
import org.jboss.logging.annotations.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@MessageBundle(projectCode = "ErrorMsg")
public interface ErrorMessages {
    ErrorMessages msg = Messages.getBundle(ErrorMessages.class);

    @Message(value = "Version %d.%d.%d-%s")
    String version(int major, int minor, int macro, String rel);
    @Message(value = "Parameter %s cannot be null")
    Supplier<String> nullParam(String name);
    @Message(value = "Value '%s' is invalid")
    IllegalArgumentException invalidValue(Object value);

    @Message(value = "The file could not be opened.")
    IOException fileAccessException();

    @Message(value = "Date string '%s' was invalid.")
    ParseException dateWasInvalid(String dateString, @Param int errorOffset);

    @Message(value = "Commutative Property of Addition %d + %d = %d + %d")
    String commutativePropertyOfAddition(@Pos(value = {1, 4}) int a, @Pos(value = {2, 3}) int b);


    @Message(value = "Operation '%s' failed.")
    <T extends RuntimeException> T operationFailed(@Producer BiFunction<String, IOException, T> fn, @Cause IOException cause, String name);
    <T extends RuntimeException> T operationFailed(@Producer Function<String, T> fn, String name);
}
