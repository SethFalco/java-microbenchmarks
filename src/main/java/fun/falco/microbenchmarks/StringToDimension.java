package fun.falco.microbenchmarks;

import java.awt.Dimension;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Convert a String in the format <code>{int}x{int}</code> or <code>{int}</code>
 * to a {@link Dimension}.
 */
public class StringToDimension {

    private static final Pattern DIMENSION_PATTERN = Pattern.compile("(\\d+)(?:x(\\d+))?");

    public static Dimension alwaysParse(final String value) {
        final Matcher matcher = DIMENSION_PATTERN.matcher(value);

        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }

        final String x = matcher.group(1);
        final String y = matcher.group(2);
        final int xValue = Integer.parseInt(x);
        final int yValue = (y != null) ? Integer.parseInt(y) : xValue;

        return new Dimension(xValue, yValue);
    }

    public static Dimension parseConditionally(final String value) {
        final Matcher matcher = DIMENSION_PATTERN.matcher(value);

        if (!matcher.matches()) {
            throw new IllegalArgumentException();
        }

        final String x = matcher.group(1);
        final String y = matcher.group(2);
        final int xValue = Integer.parseInt(x);
        final int yValue = (y == null || x.equals(y)) ? xValue : Integer.parseInt(y);

        return new Dimension(xValue, yValue);
    }
}
