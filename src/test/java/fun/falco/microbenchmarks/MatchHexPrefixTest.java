package fun.falco.microbenchmarks;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import fun.falco.utils.TestUtils;

public class MatchHexPrefixTest {

    private final Method[] methods;

    public MatchHexPrefixTest() throws Exception {
        methods = new Method[] {
            MatchHexPrefix.class.getDeclaredMethod("lowercaseString", String.class),
            MatchHexPrefix.class.getDeclaredMethod("prefixEquals", String.class),
            MatchHexPrefix.class.getDeclaredMethod("regex", String.class)
        };
    }

    @Test
    void poundSign() {
        final var args = new Object[] {"0xa3"};
        assertAll(TestUtils.toEqualsExecutables(methods, args, true));
    }

    @Test
    void uppercasePrefix() {
        final var args = new Object[] {"0Xa3"};
        assertAll(TestUtils.toEqualsExecutables(methods, args, true));
    }

    @Test
    void notMatch() {
        final var args = new Object[] {"Hello, World!"};
        assertAll(TestUtils.toEqualsExecutables(methods, args, false));
    }
}
