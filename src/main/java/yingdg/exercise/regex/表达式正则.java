package yingdg.exercise.regex;

/**
 * Created by yingdg on 2017/12/11 0011.
 */
public class 表达式正则 {
    private static String 表达式1 = "[abc]+";
    private static String 表达式2 = "[^abc]+";
    private static String 表达式3 = "[d-g]+";
    private static String 表达式4 = "[^d-g 1-3]+";

    public static void main(String[] args) {
        String testStr="abbcca";
        boolean matches = testStr.matches(表达式1);
        System.out.println(matches);

        String testStr2="dfg";
        boolean matches1 = testStr2.matches(表达式2);
        System.out.println(matches1);

        String testStr3="effg";
        boolean matches2 = testStr3.matches(表达式3);
        System.out.println(matches2);

        String testStr4="abi0ou4567";
        boolean matches3 = testStr4.matches(表达式4);
        System.out.println(matches3);
    }
}
