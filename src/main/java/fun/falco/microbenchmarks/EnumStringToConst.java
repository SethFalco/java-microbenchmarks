package fun.falco.microbenchmarks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Convert a Enum string to a concrete Enum objects.
 *
 * <p>For example, {@code "java.time.DayOfWeek.MONDAY"} →
 * {@link java.time.DayOfWeek#MONDAY}.</p>
 */
public class EnumStringToConst {

    /** For {@link #usingRegex}. */
    private static final Pattern pattern = Pattern.compile(
        "((?:[a-z\\d.]+)*\\.[A-Za-z\\d]+)[#.]([A-Z\\d_]+)"
    );

    public static <T extends Enum<?>> T usingRegex(final Class<T> type, final String value) {
        try {
            return (T) Enum.valueOf((Class) type, value);
        } catch (IllegalArgumentException ex) {
            // Continue to check fully qualified name.
        }

        Matcher matcher = pattern.matcher(value);

        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }

        String className = matcher.group(1);

        try {
            Class classForName = Class.forName(className);

            if (!classForName.isEnum() || !type.isAssignableFrom(classForName)) {
                throw new IllegalArgumentException();
            }

            return (T) Enum.valueOf(classForName, matcher.group(2));
        } catch (ClassNotFoundException ex) {
            throw new IllegalArgumentException();
        }
    }

    public static <T extends Enum<?>> T stringParsing(final Class<T> type, final String value) {
        try {
            return (T) Enum.valueOf((Class) type, value);
        } catch (IllegalArgumentException ex) {
            // Continue to check fully qualified name.
        }

        final int lastHash = value.lastIndexOf('#');
        final int lastDot = value.lastIndexOf('.');

        if (lastDot == -1 && lastHash == -1) {
            throw new IllegalArgumentException();
        }

        final String enumValue = value.substring(Math.max(lastHash, lastDot) + 1);
        final String className = value.substring(0, value.length() - enumValue.length() - 1);

        try {
            Class classForName = Class.forName(className);

            if (!classForName.isEnum() || !type.isAssignableFrom(classForName)) {
                throw new IllegalArgumentException();
            }

            return (T) Enum.valueOf(classForName, enumValue);
        } catch (ClassNotFoundException ex) {
            throw new IllegalArgumentException();
        }
    }
}
