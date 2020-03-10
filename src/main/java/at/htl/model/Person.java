package at.htl.model;

import java.util.Random;

public class Person {
    String name;
    int age;
    String email;

    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public static Person randomPerson(Random random) {
        String[] firstNames = new String[] { "John", "Adam", "George", "Jenkins", "Thomas", "Will", "Eve", "Alice" };
        String[] lastNames = new String[] { "Smith", "Doe", "McGee", "Jones", "Miller" };

        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastname = lastNames[random.nextInt(lastNames.length)];
        int age = random.nextInt(50) + 10;
        return new Person(firstName + " " + lastname, age, firstName+"."+lastname + "@gmail.com");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
