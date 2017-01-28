/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import controller.CityJpaController;
import db.City;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Spliterator;
import java.util.StringJoiner;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Ashraf-XCODER
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    
    private static EntityManagerFactory emf 
                = Persistence.createEntityManagerFactory("WorldPU"); 
    public static void main(String[] args) throws Exception{
        
        Comparator<Integer> comp = Integer::compare;
        
        
        SecurityManager manager;
       
        
        CityJpaController city = new CityJpaController(emf);
        
        List<City> cities = city.findCityEntities();
        
        cities.forEach(c -> System.out.println(c.getName()));
        
        
        //walkDirs();
        
        
        //StreamCollectors collectors = new StreamCollectors();
        //collectors.collectPerson();
        
        //Streams test = new Streams();
        //test.flatMap();
        
        
        //showDirs(new File("F:\\Java"));
        //consumerTests();
        
    }

    
    
    public static void listDirs() throws Exception {
        Path path = Paths.get("G:", "University");
        try(Stream<Path> stream = Files.list(path)){
            stream
                  .filter(p -> p.toFile().getName().endsWith(".pdf"))
                  .forEach(System.out::println);
        }
    }
    public static void walkDirs() throws Exception {
        Path path = Paths.get("G:", "University");
        try(Stream<Path> stream = Files.walk(path)){
            stream
                  //.filter(p -> p.toFile().isDirectory())
                  .forEach(System.out::println);
        }
    }
    
    
    private static void streamsIntro() {
        BinaryOperator<Integer> result = (x, y) -> x + y;

        new Random().ints().limit(10).forEach(i -> System.err.println(i));

        Function<String, String> upCase = name -> name.toUpperCase();

        Consumer<String> show = s -> System.err.println(s);

        List<String> names = Arrays.asList(new String[]{"ashraf", "umair", "hassan", "rasheed"});

        if (names.stream().anyMatch(s -> s.startsWith("um"))) {
            System.out.println("Umair is present");
        }

        Spliterator.OfInt spliterator = Arrays.spliterator(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});

        System.out.println(upCase.apply("ashraf"));

        show.accept("hi");

        StringJoiner joiner = new StringJoiner(",", "[", "]");
        joiner.add("kotlin");
        joiner.add("phantom");
        joiner.add("java");
        joiner.add("groovy");
        joiner.add("scala");
        System.out.println(joiner.toString());

        System.out.println(result.apply(2, 3));
    }

    public static void showDirs(File dir) {
        FileFilter filter = (p) -> p.getName().endsWith(".java");
        FilenameFilter nameFilter = (d ,n) -> n.endsWith(".java");

        Arrays.asList(dir.list())
                .forEach(System.out::println);
    }
     
    public static void consumerTests(){
        
        List<Integer> ints = Arrays.asList(new Integer[]{1,2,3,4,5,6,7,8,9,10});
        List<Integer> result = new ArrayList<>();
        
        Consumer<Integer> print = System.out::println;
        Consumer<Integer> add = result::add;
        Consumer<Integer> addAndShow = add.andThen(print);
        
        
        
        ints.forEach(addAndShow);
        result.forEach(print);
    }
}
