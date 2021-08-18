package ltd.icecold.orange.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
    public RegexUtil() {
    }

    public static String getMatchRegion(String input, Matcher matcher) {
        return input.substring(matcher.start(), matcher.end());
    }

    public static String getMatchRegion(String input, Matcher matcher, int index) {
        return input.substring(matcher.start(index), matcher.end(index));
    }

    public static String replace(String input, Pattern pattern, RegexUtil.RegexCallback callback) {
        Matcher m = pattern.matcher(input);
        int lastEnd = 0;

        StringBuilder build;
        for(build = new StringBuilder(); m.find(); lastEnd = m.end()) {
            build.append(input.substring(lastEnd, m.start()));
            String got = callback.replace(input, m);
            if (got == null) {
                got = input.substring(m.start(), m.end());
            }

            build.append(got);
        }

        build.append(input.substring(lastEnd));
        return build.toString();
    }

    public static String findFirst(String input, Pattern pattern, int index) {
        Matcher m = pattern.matcher(input);
        return !m.find() ? null : input.substring(m.start(index), m.end(index));
    }

    public static String[] find(String input, Pattern pattern, int... indices) {
        Matcher m = pattern.matcher(input);
        if (!m.find()) {
            return null;
        } else {
            String[] results = new String[indices.length];

            for(int i = 0; i < results.length; ++i) {
                results[i] = input.substring(m.start(indices[i]), m.end(indices[i]));
            }

            return results;
        }
    }

    public interface RegexCallback {
        String replace(String var1, Matcher var2);
    }
}