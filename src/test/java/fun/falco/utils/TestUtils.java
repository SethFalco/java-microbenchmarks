package fun.falco.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

/**
 * Utilities to scaffold unit tests.
 *
 * <p>Requirements for this project are different from the norm, as we're
 * implementing each function multiple times, and need to ensure each
 * implementation is identical before benchmarking.</p>
 */
public class TestUtils {

    /**
     * Maps each method to an invocation of the method, ensuring each invocation
     * yields identical results.
     *
     * @param methods Methods to invoke.
     * @param input Parameters to pass to each invocation of an method.
     * @param expected Expected result of the JUnit assertion.
     * @return Executables that can be passed to {@link Assertions#assertAll}.
     */
    public static Executable[] toJunitExecutables(Method[] methods, Object[] input, Object expected) {
        return Stream.of(methods).map(m -> {
            return new Executable() {
                public void execute() throws Exception {
                    assertEquals(expected, m.invoke(null, input));
                }
            };
        }).toArray(Executable[]::new);
    }
}
