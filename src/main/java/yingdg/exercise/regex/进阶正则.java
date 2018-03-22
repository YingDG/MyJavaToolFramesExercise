package yingdg.exercise.regex;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by yingdg on 2017/12/12 0012.
 * <p>
 * 测试输入法
 */
public class 进阶正则 {
    private static String 取表达式前面的字符 = "(?<txt>.+(?=ing))";
    private static String 取表达式后面的字符 = "(?<txt>(?<=How).+)";
    private static String 取表达式之外前面的字符 = "(?:\\d{3}(?!\\d+))";
    private static String 取表达式之外后面的字符 = "(?:(?!<\\d)[a-z]+)";

    public static void main(String[] args) {
        String testStr = "How are you doing?";
        String replacePattern = StringUtils.replacePattern(testStr, 取表达式前面的字符, "hello ");
        System.out.println(replacePattern);
        String replacePattern1 = StringUtils.replacePattern(testStr, 取表达式后面的字符, " hahaha");
        System.out.println(replacePattern1);

        String testStr2 = "123abc";
        String replacePattern2 = StringUtils.replacePattern(testStr2, 取表达式之外前面的字符, "321");
        System.out.println(replacePattern2);
        String replacePattern3 = StringUtils.replacePattern(testStr2, 取表达式之外后面的字符, "cba");
        System.out.println(replacePattern3);
    }
}
