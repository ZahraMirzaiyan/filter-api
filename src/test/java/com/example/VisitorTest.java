package com.example.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VisitorTest {
    // Visitor ----------------------------------------------------------------
    @Test
    void testVisitorFilter() {
    
        TestVisitor visitor = new TestVisitor();
        
        Filter propertyRegularFilter = new PropertyRegularFilter("firstname", "x.*");
        Filter propertyGreaterFilter = new PropertyGreaterFilter("age", "20");
        Filter propertyEqualsFilter = new PropertyEqualsFilter("role", "Junior Java Developer");
        Filter andFilter = new AndFilter(propertyEqualsFilter, propertyGreaterFilter);
        Filter orFilter = new OrFilter(andFilter, propertyRegularFilter);
        
        // Apply visitor
        orFilter.accept(visitor);
        
        // Verify that all the the filters have been visited
        //List<String> expected = List.of("OR", "AND", "EQUALS", "GREATER", "REGULAR");   
        List<String> expected = Arrays.asList("OR", "AND", "EQUALS", "GREATER", "REGULAR");

        assertEquals(expected, visitor.visited);
                   
        // Print for processing
        //System.out.println("Visitor visited nodes: " + visitor.visited);
    }
}


