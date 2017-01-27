/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Spliterator;
import java.util.StringJoiner;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 * @author Ashraf-XCODER
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BinaryOperator<Integer> result = (x,y) -> x+y;
        
        new Random().ints().limit(10).forEach(i -> System.err.println(i));
        
        
        Function<String, String> upCase = name -> name.toUpperCase();
        
        Consumer<String> show = s -> System.err.println(s);
        
        List<String> names = Arrays.asList(new String[]{"ashraf", "umair", "hassan", "rasheed"});
        
        if (names.stream().anyMatch(s -> s.startsWith("um"))) {
            System.out.println("Umair is present");
        }
        
        Spliterator.OfInt spliterator = Arrays.spliterator(new int[] {1,2,3,4,5,6,7,8,9,10});
        
        
        
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

    
    public static void showDirs(File dir){
        FileFilter filter = (p) -> p.getName().endsWith(".java");
        Arrays.asList(dir.listFiles()).forEach(System.out::println);
    }
}
