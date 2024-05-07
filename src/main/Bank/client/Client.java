package client;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Client {
    private String name;

    private int age;

    public Client(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "client.Client{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
