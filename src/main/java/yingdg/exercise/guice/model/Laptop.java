package yingdg.exercise.guice.model;

/**
 * Created by yingdg on 2017/4/19.
 */
public class Laptop {
    private String lname;

    public Laptop() {
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "lname='" + lname + '\'' +
                '}';
    }

}
