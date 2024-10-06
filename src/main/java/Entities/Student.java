package Entities;

import org.springframework.stereotype.Service;

@Service
public class Student {
    private int id;
    private String name;
    private String city;

    public Student(int id,String name,String city) {
        this.id = id;
        this.city = city;
        this.name = name;
    }
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Student(String name,String city) {
        this.name = name;
        this.city = city;
    }
    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
