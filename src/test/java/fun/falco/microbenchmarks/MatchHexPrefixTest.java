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
    public void poundSign() {
        final var args = new Object[] {"0xa3"};
        assertAll(TestUtils.toJunitExecutables(methods, args, true));
    }

    @Test
    public void uppercasePrefix() {
        final var args = new Object[] {"0Xa3"};
        assertAll(TestUtils.toJunitExecutables(methods, args, true));
    }

    @Test
    public void notMatch() {
        final var args = new Object[] {"Hello, World!"};
        assertAll(TestUtils.toJunitExecutables(methods, args, false));
    }
}
