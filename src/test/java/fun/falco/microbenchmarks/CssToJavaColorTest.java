package fun.falco.microbenchmarks;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.awt.Color;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import fun.falco.utils.TestUtils;

public class CssToJavaColorTest {

    private final Method[] methods;

    public CssToJavaColorTest() throws Exception {
        methods = new Method[] {
            CssToJavaColor.class.getDeclaredMethod("allLengths", String.class),
            CssToJavaColor.class.getDeclaredMethod("regexToDuplicateChars", String.class)
        };
    }

    @Test
    public void colorWithAlphaChannel() {
        final Color expected = new Color(255, 255, 255, 255);
        assertAll(TestUtils.toJunitExecutables(methods, "#FFFFFFFF", expected));
    }

    @Test
    public void cssColorShorthand() {
        final var expected = new Color(255, 255, 255);
        assertAll(TestUtils.toJunitExecutables(methods, "#FFF", expected));
    }
}
