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
    public void square() {
        final Dimension expected = new Dimension(512, 512);
        assertAll(TestUtils.toJunitExecutables(methods, "512", expected));
    }

    @Test
    public void rectangle() {
        final Dimension expected = new Dimension(1920, 1080);
        assertAll(TestUtils.toJunitExecutables(methods, "1920x1080", expected));
    }
}
