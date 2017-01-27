/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author Ashraf-XCODER
 */
public class Streams {
    
    //Map -> Filter -> Reduce
    //Mapping : List<Person> -> List<Integer> same size of both lists
    //Filtering: List<Integer> -> List<Integer> elements filtered out so size is different
    //Reduction step
    
    
    
    public void flatMap(){
        List<Integer> odd = Arrays.asList(new Integer[]{1,3,5,7,9,11,13});
        List<Integer> even = Arrays.asList(new Integer[]{0,2,4,6,8,10,12,14,16});
        List<Integer> cube = Arrays.asList(new Integer[]{1,8, 27, 64, 125});
        
        
        //Nested list
        List<List<Integer>> list = Arrays.asList(odd, even, cube);
        
        Function<List<?>, Integer> size = l -> l.size();
        Function<List<Integer>, Stream<Integer>> intsToStream = l -> l.stream();
        
        
        list.stream()
            //.map(intsToStream)
            .flatMap(intsToStream)
            .forEach(System.out::println);
    }
    
    
    
}
