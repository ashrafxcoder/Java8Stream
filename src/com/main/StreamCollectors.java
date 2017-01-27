/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author Ashraf-XCODER
 */
public class StreamCollectors {

    static List<Person> people = new ArrayList<>();

    public StreamCollectors() {
        people.add(new Person("Bob", 21));
        people.add(new Person("Tom", 39));
        people.add(new Person("Jerry", 15));
        people.add(new Person("Pinko", 32));
        people.add(new Person("Mawa", 16));
        people.add(new Person("Bear", 24));
        people.add(new Person("Tarzan", 54));
        people.add(new Person("Duck", 27));
        people.add(new Person("Donald", 19));
        people.add(new Person("Trumpkin", 65));
    }

    
    public void minPerson(){
        
        Optional<Person> person = 
        people.stream().filter(p -> p.getAge() > 15)
                        .min(Comparator.comparing(Person::getAge));
        
        System.out.println(person); 
    }
    
    public void maxPerson(){
        
        Optional<Person> person = 
        people.stream().filter(p -> p.getAge() > 15)
                        .max(Comparator.comparing(Person::getAge));
        
        System.out.println(person); 
    }
    public void collectPerson(){
        
        Map<Integer, List<Person>> ageGroups = 
        people.stream()
                .collect(
                        Collectors.groupingBy(Person::getAge)
                        );
                        
        System.out.println(ageGroups); 
    }
    
    

    public class Person {

        String name;
        int age;

        public Person() {
        }

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
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

    }

}
