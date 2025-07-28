package fun.falco.microbenchmarks;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.lang.reflect.Method;
import java.time.DayOfWeek;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import fun.falco.utils.TestUtils;

public class EnumStringToConstTest {

    private final Method[] methods;

    public EnumStringToConstTest() throws Exception {
        methods = new Method[] {
            EnumStringToConst.class.getDeclaredMethod("usingRegex", Class.class, String.class),
            EnumStringToConst.class.getDeclaredMethod("stringParsing", Class.class, String.class),
        };
    }

    @Test
    void timeUnitWithGeneric() {
        final var expected = TimeUnit.NANOSECONDS;
        final var args = new Object[] {Enum.class, "java.util.concurrent.TimeUnit.NANOSECONDS"};
        assertAll(TestUtils.toEqualsExecutables(methods, args, expected));
    }

    @Test
    void dayOfWeekConcreteFullyQualified() {
        final var expected = DayOfWeek.MONDAY;
        final var args = new Object[] {DayOfWeek.class, "java.time.DayOfWeek#MONDAY"};
        assertAll(TestUtils.toEqualsExecutables(methods, args, expected));
    }

    @Test
    void dayOfWeekConcreteValueOnly() {
        final var expected = DayOfWeek.MONDAY;
        final var args = new Object[] {DayOfWeek.class, "MONDAY"};
        assertAll(TestUtils.toEqualsExecutables(methods, args, expected));
    }

    @Test
    void emptyString() {
        final var expected = IllegalArgumentException.class;
        final var args = new Object[] {Enum.class, ""};
        assertAll(TestUtils.toThrowsExecutables(methods, args, expected));
    }

    @Test
    void shortString() {
        final var expected = IllegalArgumentException.class;
        final var args = new Object[] {Enum.class, "MONDAY"};
        assertAll(TestUtils.toThrowsExecutables(methods, args, expected));
    }

    @Test
    void onlyHash() {
        final var expected = IllegalArgumentException.class;
        final var args = new Object[] {Enum.class, "#"};
        assertAll(TestUtils.toThrowsExecutables(methods, args, expected));
    }

    @Test
    void mismatchingEnumType() {
        final var expected = IllegalArgumentException.class;
        final var args = new Object[] {TimeUnit.class, "java.time.DayOfWeek#MONDAY"};
        assertAll(TestUtils.toThrowsExecutables(methods, args, expected));
    }

    @Test
    void brokenNamingConvention() {
        final var expected = IllegalArgumentException.class;
        final var args = new Object[] {Enum.class, "JAVA-TIME-DAYOFWEEK#MONDAY"};
        assertAll(TestUtils.toThrowsExecutables(methods, args, expected));
    }

    @Test
    void nonEnumClasses() {
        final var expected = IllegalArgumentException.class;
        final var args = new Object[] {Enum.class, "java.lang.String#MONDAY"};
        assertAll(TestUtils.toThrowsExecutables(methods, args, expected));
    }

    @Test
    void nonExistingClasses() {
        final var expected = IllegalArgumentException.class;
        final var args = new Object[] {Enum.class, "java.lang.does.not.exist#MONDAY"};
        assertAll(TestUtils.toThrowsExecutables(methods, args, expected));
    }
}
