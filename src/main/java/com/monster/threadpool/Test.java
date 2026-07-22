package com.monster.threadpool;

import lombok.Data;

public class Test {
    public static void main(String[] args) {
        Person person = new Person("Java");
        modifyReference(person);
        trychangereference(person);
        System.out.println("After   trychangereference:" + person.getName());
    }

    static void modifyReference(Person p) {
        p.setName("Go");
    }

    static void trychangereference(Person p) {
        p = new Person("Python");
    }

    @Data
    static class Person {
        private String name;

        public Person(String name) {
            this.name = name;
        }
    }
}