/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insset.jeanluc.util.coll;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jldeleage
 */
public class ListDecoratorTest {
    

    public final static String  UN      = "un";
    public final static String  DEUX    = "deux";
    public final static String  TROIS   = "trois";
    public final static String  QUATRE  = "quatre";
    public final static String  CINQ    = "cinq";
    public final static String  SIX     = "six";

    private ListDecorator<String>       instance;

    public ListDecoratorTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new ListDecorator<String>();
        instance.add(UN);
        instance.add(DEUX);
        instance.add(TROIS);
        instance.add(QUATRE);
        instance.add(CINQ);
        instance.add(SIX);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of filter method, of class ListDecorator.
     */
    @Test
    public void testFilter() {
        System.out.println("filter");
        ListDecorator<String> expResult = new ListDecorator<String>();
        expResult.add(TROIS);
        expResult.add(CINQ);
        expResult.add(SIX);
        ListDecorator<String> result = instance.filter(s -> s.contains("i"));
        assertEquals(expResult, result);
        assertEquals(3, result.size());
        result = result.filter((String s) -> s.length() > 3);
        expResult.remove(SIX);
        assertEquals(expResult, result);
    }

    /**
     * Test of map method, of class ListDecorator.
     */
    @Test
    public void testMap() {
        System.out.println("map");
        ListDecorator<Integer> expResult = new ListDecorator<Integer>();
        expResult.add(2);
        expResult.add(4);
        expResult.add(5);
        expResult.add(6);
        expResult.add(4);
        expResult.add(3);
        ListDecorator result = instance.map(s -> s.length());
        assertEquals(expResult, result);
    }


    
}
