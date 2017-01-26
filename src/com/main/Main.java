/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main;

import java.util.function.BinaryOperator;

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
        
        System.out.println(result.apply(2, 3));
    }
    
}
