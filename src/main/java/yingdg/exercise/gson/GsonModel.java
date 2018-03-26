package yingdg.exercise.gson;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class GsonModel {
    private Integer id;
    @SerializedName("name")
    private String username;
    private Date date;

    public GsonModel() {
    }

    public GsonModel(Integer id, String username, Date date) {
        this.id = id;
        this.username = username;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "GsonModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", date=" + date +
                '}';
    }
}
