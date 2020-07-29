package com.yg.obj;

/**
 * TODO
 *
 * @author Y.G
 * @date 2020/7/24 16:44
 **/
public class Employ {
    private Person person = new Person("", "");

    public Person getPerson() {
        return new Person(person);
    }


    public void printEmployeeDetail(Employ emp) {
        Person person = emp.getPerson();
// this caller does not modify the object, so defensive copy was unnecessary
        System.out.println("Employee's name: " + person.getName() + "; age: " + person.getAge());
    }

    public static void main(String[] args) {
        Employ employ = new Employ();
        employ.printEmployeeDetail(employ);
    }
}
