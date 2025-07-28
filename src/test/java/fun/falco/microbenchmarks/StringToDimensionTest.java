package fun.falco.microbenchmarks;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.awt.Dimension;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import fun.falco.utils.TestUtils;

public class StringToDimensionTest {

    private final Method[] methods;

    public StringToDimensionTest() throws Exception {
        methods = new Method[] {
            StringToDimension.class.getDeclaredMethod("alwaysParse", String.class),
            StringToDimension.class.getDeclaredMethod("parseConditionally", String.class)
        };
    }

    @Test
    void square() {
        final Dimension expected = new Dimension(512, 512);
        final var args = new Object[] {"512"};
        assertAll(TestUtils.toJunitExecutables(methods, args, expected));
    }

    @Test
    void rectangle() {
        final Dimension expected = new Dimension(1920, 1080);
        final var args = new Object[] {"1920x1080"};
        assertAll(TestUtils.toJunitExecutables(methods, args, expected));
    }
}
