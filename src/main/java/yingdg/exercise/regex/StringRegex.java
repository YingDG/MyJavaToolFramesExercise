package yingdg.exercise.regex;

import java.util.Arrays;

/**
 * Created by yingdg on 2017/8/9.
 */
public class StringRegex {
    private static final String EXAMPLE = "hellO1234Regex";

    public static void main(String[] args) {
        String str = new String(EXAMPLE);

        boolean matches = str.matches("\0");
        System.out.println(matches);

        String[] split = str.split("\\s");
        System.out.println(Arrays.toString(split));

        String replaceAll = str.replaceAll("\\S", "a");
        System.out.println(replaceAll);

        String replaceFirst = str.replaceFirst("\0", "1");
        System.out.println(replaceFirst);
    }
}
