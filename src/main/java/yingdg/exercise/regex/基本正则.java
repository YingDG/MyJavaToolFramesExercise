package yingdg.exercise.regex;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by yingdg on 2017/12/11 0011.
 */
public class 基本正则 {
    private static String 普通字符 = "\\w+";
    private static String 任意数字 = "\\d+";
    private static String 键盘符号 = "\\s";
    private static String 任意符号 = ".*";

    public static void main(String[] args) {
        String testStr = "abcde";
        boolean alpha = StringUtils.isAlpha(testStr);
        System.out.println(alpha);
        boolean contains = testStr.matches(普通字符);
        System.out.println(contains);

        String testStr2 = "123456";
        boolean numeric = StringUtils.isNumeric(testStr2);
        System.out.println(numeric);
        boolean matches = testStr2.matches(任意数字);
        System.out.println(matches);

        String testStr3 = "\n";
        boolean matches1 = testStr3.matches(键盘符号);
        System.out.println(matches1);

        boolean matches2 = (testStr + testStr2 + testStr3).matches(任意符号);
        System.out.println(matches2);
        boolean matches3 = (testStr + testStr2 + "}").matches(任意符号);
        System.out.println(matches3);
    }
}
