package fun.falco.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.InvocationTargetException;
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
    public static Executable[] toEqualsExecutables(final Method[] methods, final Object[] input, final Object expected) {
        return Stream.of(methods).map(m -> {
            return new Executable() {
                public void execute() throws Exception {
                    assertEquals(expected, m.invoke(null, input));
                }
            };
        }).toArray(Executable[]::new);
    }

    /**
     * Maps each method to an invocation of the method, ensuring each invocation
     * yields the same exception.
     *
     * @param methods Methods to invoke.
     * @param input Parameters to pass to each invocation of an method.
     * @param expected Expected exception of the JUnit assertion.
     * @return Executables that can be passed to {@link Assertions#assertThrows}.
     */
    public static Executable[] toThrowsExecutables(final Method[] methods, final Object[] input, final Class<? extends Exception> expected) {
        return Stream.of(methods).map(m -> {
            return new Executable() {
                public void execute() throws Exception {
                    var ex = assertThrows(InvocationTargetException.class, () -> m.invoke(null, input));
                    assertEquals(expected, ex.getCause().getClass());
                }
            };
        }).toArray(Executable[]::new);
    }
}
