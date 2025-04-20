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
            MatchHexPrefix.class.getDeclaredMethod("lowercasePrefix", String.class),
            MatchHexPrefix.class.getDeclaredMethod("regex", String.class)
        };
    }

    @Test
    public void poundSign() {
        assertAll(TestUtils.toJunitExecutables(methods, "0xa3", true));
    }

    @Test
    public void uppercasePrefix() {
        assertAll(TestUtils.toJunitExecutables(methods, "0Xa3", true));
    }

    @Test
    public void notMatch() {
        assertAll(TestUtils.toJunitExecutables(methods, "Hello, World!", false));
    }
}
