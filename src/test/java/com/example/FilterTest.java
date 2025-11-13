package com.example.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FilterTest {


    // Create user resource having various properties:

    private Map<String, String> user;

    @BeforeEach
    void setup() {
        user = new HashMap<>();
        user.put("firstname", "Zahra");
        user.put("lastname", "Mirzaiyan");
        user.put("role", "Junior java Developer");
        user.put("age", "35");
        user.put("height", "170");
        user.put("weight", "56");
    }

    // TrueFilter ----------------------------------------------------------
    @Test
    void testTrueFilter() {
        Filter filter = new TrueFilter();

        assertTrue(filter.matches(user));
        assertEquals("TRUE", filter.toString());
        
        //TestVisitor visitor = new TestVisitor();
        //filter.accept(visitor);
        //assertTrue(visitor.visited.contains("TRUE"));
        
    }

    // FalseFilter ---------------------------------------------------------
    @Test
    void testFalseFilter() {
        Filter filter = new FalseFilter();

        assertFalse(filter.matches(user));
        assertEquals("FALSE", filter.toString());
    }

    // OrFilter ------------------------------------------------------------
    @Test
    void testOrFilter() {
        Filter filter = new OrFilter(new TrueFilter(), new FalseFilter());

        assertTrue(filter.matches(user));
        assertEquals("(TRUE OR FALSE)", filter.toString());
    }

    // AndFilter -----------------------------------------------------------
    @Test
    void testAndFilter() {
        Filter filter = new AndFilter(new TrueFilter(), new TrueFilter());

        assertTrue(filter.matches(user));
        assertEquals("(TRUE AND TRUE)", filter.toString());
    }

    // NotFilter -----------------------------------------------------------
    @Test
    void testNotFilter() {
        Filter filter = new NotFilter(new TrueFilter());

        assertFalse(filter.matches(user));
        assertEquals("NOT TRUE", filter.toString());
    }

    // PropertyPresentFilter ------------------------------------------------
    @Test
    void testPropertyPresentFilter() {
        Filter filter = new PropertyPresentFilter("height");

        assertTrue(filter.matches(user));
        assertEquals("height is present", filter.toString());
        
       //user.remove("zahruccina");
       //user.put("Zahruccina", "35");
       //assertFalse(filter.matches(user));
       //user.put("zahruccina", "35");
       //assertTrue(filter.matches(user));
    }

    // PropertyEqualsFilter -------------------------------------------------
    @Test
    void testPropertyEqualsFilter() {
        Filter filter = new PropertyEqualsFilter("weight", "56");

        assertTrue(filter.matches(user));
        assertEquals("weight = 56", filter.toString());
    }

    // PropertyLessFilter ---------------------------------------------------
    @Test
    void testPropertyLessFilter() {
        Filter filter = new PropertyLessFilter("age", "41");

        assertTrue(filter.matches(user));
        assertEquals("age < 41", filter.toString());
    }

    // PropertyGreaterFilter ------------------------------------------------
    @Test
    void testPropertyGreaterFilter() {
        Filter filter = new PropertyGreaterFilter("height", "150");

        assertTrue(filter.matches(user));
        assertEquals("height > 150", filter.toString());
    }

    // PropertyRegularFilter ------------------------------------------------
    @Test
    void testPropertyRegexFilter() {
        Filter filter = new PropertyRegularFilter("lastname", "M.*");

        assertTrue(filter.matches(user));
        assertEquals("lastname matches M.*", filter.toString());
    }

    //  The ability to programmatically construct arbitrarily complex filters: Combined filter example
    @Test
    void testCombinedFilter() {
        Filter filter = new OrFilter(
                new AndFilter(
                        new PropertyEqualsFilter("role", "Junior Java Developer"),
                        new PropertyGreaterFilter("age", "20")
                ),
                new PropertyRegularFilter("firstname", "x.*")
        );

        assertTrue(filter.matches(user));
        assertEquals(
                "((role = Junior Java Developer AND age > 20) OR firstname matches x.*)",
                filter.toString()
        );
    }
}


