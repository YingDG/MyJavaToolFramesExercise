package yingdg.exercise.kryo.model;

/**
 * Created by yingdg on 2017/4/19.
 */
public class Mall {
    private int id;
    private String mname;
    private transient Integer price;

    public Mall() {
    }

    public Mall(int id, String mname, Integer price) {
        this.id = id;
        this.mname = mname;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Mall{" +
                "id=" + id +
                ", mname='" + mname + '\'' +
                ", price=" + price +
                '}';
    }

}
