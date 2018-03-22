package yingdg.exercise.regex;

/**
 * Created by yingdg on 2017/12/11 0011.
 */
public class 模式正则 {
    private static String 重复n次="[abc]{2}";
    private static String 重复m到n次="[abc]{1,3}";
    private static String 至少重复m次="[abc]{2,}";
    private static String 匹配0或1次="[abc]?";
    private static String 匹配至少1次="[abc]+";
    private static String 匹配0或多次="[abc]*";

    public static void main(String[] args) {
        String testStr="ab";
        boolean matches = testStr.matches(重复n次);
        System.out.println(matches);

        String testStr2="ac";
        boolean matches1 = testStr2.matches(重复m到n次);
        System.out.println(matches1);

        String testStr3="abcc";
        boolean matches2 = testStr3.matches(至少重复m次);
        System.out.println(matches2);

        String testStr4="";
        boolean matches3 = testStr4.matches(匹配0或1次);
        System.out.println(matches3);

        String testStr5="c";
        boolean matches4 = testStr5.matches(匹配至少1次);
        System.out.println(matches4);

        String testStr6="";
        boolean matches5 = testStr6.matches(匹配0或多次);
        System.out.println(matches5);
    }
}
