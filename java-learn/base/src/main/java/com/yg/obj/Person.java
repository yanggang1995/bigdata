package com.yg.obj;

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/7/24 16:44
 **/
public class Person {
    private String name;
    private String age;

    public Person(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Person(Person person) {
        this(person.getName(), person.getAge());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
