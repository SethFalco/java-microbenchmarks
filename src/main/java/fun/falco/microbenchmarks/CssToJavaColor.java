package fun.falco.microbenchmarks;

import java.awt.Color;
import java.util.regex.Pattern;

/**
 * Convert a CSS-like hexadecimal color to a Java {@link Color}.
 */
public class CssToJavaColor {

    /** For {@link #regexToDuplicateChars}. */
    private static final Pattern pattern = Pattern.compile("[^#]");

    public static Color allLengths(final String value) {
        return switch (value.length()) {
            case 4 -> new Color(
                Integer.parseInt(value.substring(1, 2), 16) * 17,
                Integer.parseInt(value.substring(2, 3), 16) * 17,
                Integer.parseInt(value.substring(3, 4), 16) * 17
            );
            case 5 -> new Color(
                Integer.parseInt(value.substring(1, 2), 16) * 17,
                Integer.parseInt(value.substring(2, 3), 16) * 17,
                Integer.parseInt(value.substring(3, 4), 16) * 17,
                Integer.parseInt(value.substring(4, 5), 16) * 17
            );
            case 7 -> new Color(
                Integer.parseInt(value.substring(1, 3), 16),
                Integer.parseInt(value.substring(3, 5), 16),
                Integer.parseInt(value.substring(5, 7), 16)
            );
            case 9 -> new Color(
                Integer.parseInt(value.substring(1, 3), 16),
                Integer.parseInt(value.substring(3, 5), 16),
                Integer.parseInt(value.substring(5, 7), 16),
                Integer.parseInt(value.substring(7, 9), 16)
            );
            default -> throw new IllegalArgumentException();
        };
    }

    public static Color regexToDuplicateChars(final String value) {
        final int length = value.length();
        final String hexString = (length == 4 || length == 5) ? pattern.matcher(value).replaceAll("$0$0") : value;

        return switch (hexString.length()) {
            case 7 -> Color.decode(hexString);
            case 9 -> new Color(
                Integer.parseInt(hexString.substring(1, 3), 16),
                Integer.parseInt(hexString.substring(3, 5), 16),
                Integer.parseInt(hexString.substring(5, 7), 16),
                Integer.parseInt(hexString.substring(7, 9), 16)
            );
            default -> throw new IllegalArgumentException();
        };
    }
}
