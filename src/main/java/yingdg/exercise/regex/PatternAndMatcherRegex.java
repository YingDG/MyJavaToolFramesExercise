package yingdg.exercise.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yingdg on 2017/8/9.
 */
public class PatternAndMatcherRegex {
    private static final String EXAMPLE = "hellO1234Regex";

    public static void main(String[] args) {
        boolean matches = Pattern.matches("\0", EXAMPLE);
        System.out.println(matches);

        Pattern pattern = Pattern.compile("hell");
        Matcher matcher = pattern.matcher(EXAMPLE);
        if (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
