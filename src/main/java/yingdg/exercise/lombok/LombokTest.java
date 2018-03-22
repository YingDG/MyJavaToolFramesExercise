package yingdg.exercise.lombok;

/**
 * Created by zdm on 2018/3/20.
 */
public class LombokTest {
    public static void main(String[] args) {
        Model model = new Model(1, "a", 2);
        System.out.println(model);

        Model model1 = Model.builder().id(2).mname("b").price(3).build();
        System.out.println(model1);

        Model2 model2 = new Model2(3, "c", 22);
        System.out.println(model2);

        Model2 model22 = Model2.of(4);
        System.out.println(model22);

        System.out.println();
    }
}
