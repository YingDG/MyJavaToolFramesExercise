package yingdg.exercise.hessian.client;

import java.io.Serializable;

/**
 * Created by yingdg on 2017/10/10 0010.
 */
public class MyMessage implements Serializable{
    private String message;

    public MyMessage() {

    }

    public MyMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MyMessage{" +
                "message='" + message + '\'' +
                '}';
    }

}
