package fun.falco.microbenchmarks;

import java.util.regex.Pattern;

/**
 * Check if the first 2 characters in a String indicate that the string
 * represents a hexadecimal number.
 *
 * @see <a href="https://github.com/apache/commons-beanutils/pull/49">apache/commons-beanutils#49</a>
 */
public class MatchHexPrefix {

    private static final String HEX_PREFIX = "0x";

    /** For {@link #regex}. */
    private static final Pattern HEX_PATTERN = Pattern.compile("^" + HEX_PREFIX, Pattern.CASE_INSENSITIVE);

    /**
     * @see <a href="https://github.com/apache/commons-beanutils/pull/49">Comment in apache/commons-beanutils#49.</a>
     */
    public static boolean prefixEquals(final String value) {
        return value.substring(0, 2).equalsIgnoreCase(HEX_PREFIX);
    }

    public static boolean lowercaseString(final String value) {
        return value.toLowerCase().startsWith(HEX_PREFIX);
    }

    public static boolean regex(final String value) {
        return HEX_PATTERN.matcher(value).find();
    }
}
