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
	assertEquals("TRUE", filter.toQL());
        //System.out.println(filter.toString());
    }

    // FalseFilter ---------------------------------------------------------
    @Test
    void testFalseFilter() {
        Filter filter = new FalseFilter();

        assertFalse(filter.matches(user));
	assertEquals("FALSE", filter.toQL());
	//System.out.println(filter.toString());
    }

    // OrFilter (REVIEW) ------------------------------------------------------------
    @Test
    void testOrFilter() {
        Filter filter1 = new OrFilter(new TrueFilter(), new TrueFilter());
        Filter filter2 = new OrFilter(new TrueFilter(), new FalseFilter());
        Filter filter3 = new OrFilter(new FalseFilter(), new TrueFilter());
        Filter filter4 = new OrFilter(new FalseFilter(), new FalseFilter());

        assertTrue(filter1.matches(user));
	assertEquals("(TRUE OR TRUE)", filter1.toQL());
        //System.out.println(filter1.toString());

	assertTrue(filter2.matches(user));
	assertEquals("(TRUE OR FALSE)", filter2.toQL());
        //System.out.println(filter2.toString());

	assertTrue(filter3.matches(user));
	assertEquals("(FALSE OR TRUE)", filter3.toQL());
        //System.out.println(filter3.toString());

	assertTrue(!filter4.matches(user));
	assertEquals("(FALSE OR FALSE)", filter4.toQL());
        //System.out.println(filter4.toString());
    }

    // AndFilter (REVIEW) -----------------------------------------------------------
    @Test
    void testAndFilter() {
        Filter filter1 = new AndFilter(new TrueFilter(), new TrueFilter());
        Filter filter2 = new AndFilter(new TrueFilter(), new FalseFilter());
        Filter filter3 = new AndFilter(new FalseFilter(), new TrueFilter());
        Filter filter4 = new AndFilter(new FalseFilter(), new FalseFilter());

        assertTrue(filter1.matches(user));
	assertEquals("(TRUE AND TRUE)", filter1.toQL());
        //System.out.println(filter1.toString());

	assertTrue(!filter2.matches(user));
	assertEquals("(TRUE AND FALSE)", filter2.toQL());
	//System.out.println(filter2.toString());
	
	assertTrue(!filter3.matches(user));
	assertEquals("(FALSE AND TRUE)", filter3.toQL());
        //System.out.println(filter3.toString());

	assertTrue(!filter4.matches(user));
	assertEquals("(FALSE AND FALSE)", filter4.toQL());
        //System.out.println(filter4.toString());

    }

    // NotFilter (REVIEW) -----------------------------------------------------------
    @Test
    void testNotFilter() {
        Filter filter1 = new NotFilter(new TrueFilter());
	Filter filter2 = new NotFilter(new FalseFilter());

        assertFalse(filter1.matches(user));
	assertEquals("NOT TRUE", filter1.toQL());
        //System.out.println(filter1.toString());

	assertTrue(filter2.matches(user));
        assertEquals("NOT FALSE", filter2.toQL());
        //System.out.println(filter2.toString());

    }

    // PropertyPresentFilter ------------------------------------------------
    @Test
    void testPropertyPresentFilter() {
        Filter filter = new PropertyPresentFilter("height");

        assertTrue(filter.matches(user));
        assertEquals("height IS PRESENT", filter.toQL());
        //System.out.println(filter.toString());

        //user.remove("zahruccina");
        //user.put("Zahruccina", "35");
        //assertFalse(filter.matches(user));
        //user.put("zahruccina", "35");
        //assertTrue(filter.matches(user));
    }

    // PropertyEqualsFilter (REVIEW) -------------------------------------------------
    @Test
    void testPropertyEqualsFilter() {
        Filter filter1 = new PropertyEqualsFilter("weight", "56");
        assertTrue(filter1.matches(user));
        assertEquals("weight = 56.0", filter1.toQL());
        //System.out.println(filter1.toString());

	user.put("weight", "56.1");
	Filter filter2 = new PropertyEqualsFilter("weight", "56.1");
	assertTrue(filter2.matches(user));
        assertEquals("weight = 56.1", filter2.toQL());
        //System.out.printl(filter2.toString());

	user.put("weight", "56.1f");
	Filter filter3 = new PropertyEqualsFilter("weight", "56.1");
        assertTrue(filter3.matches(user));
	assertEquals("weight = 56.1", filter3.toQL());
	//System.out.printl(filter3.toString());

	Filter filter4 = new PropertyEqualsFilter("firstname", "zahra");
	assertTrue(filter4.matches(user));
	assertEquals("firstname = \"zahra\"", filter4.toQL());
        //System.out.printl(filter4.toString());
	

    }

    // PropertyLessFilter (REVIEW) ---------------------------------------------------
    @Test
    void testPropertyLessFilter() {
        Filter filter1 = new PropertyLessFilter("age", "41");
        Filter filter2 = new PropertyLessFilter("age", "41.2");
	Filter filter3 = new PropertyLessFilter("age", "41.2f");

	Filter filter4 = new PropertyLessFilter("age", "33");
	Filter filter5 = new PropertyLessFilter("age", "33.2");
	Filter filter6 = new PropertyLessFilter("age", "33.2f");

        assertTrue(filter1.matches(user));
	assertTrue(filter2.matches(user));
	assertTrue(filter3.matches(user));
	assertEquals("age < 41.0", filter1.toQL());
	assertEquals("age < 41.2", filter2.toQL());
	assertEquals("age < 41.2", filter3.toQL());
        //System.out.println(filter1.toString());
	//System.out.println(filter2.toString());
	//System.out.println(filter3.toString());

	assertTrue(!filter4.matches(user));
	assertTrue(!filter5.matches(user));
	assertTrue(!filter6.matches(user));

    }

    // PropertyGreaterFilter (REVIEW) ------------------------------------------------
    @Test
    void testPropertyGreaterFilter() {
        Filter filter1 = new PropertyGreaterFilter("height", "150");
	Filter filter2 = new PropertyGreaterFilter("height", "150.2");
        Filter filter3 = new PropertyGreaterFilter("height", "150.2f");

        Filter filter4 = new PropertyGreaterFilter("height", "180");
        Filter filter5 = new PropertyGreaterFilter("height", "180.5");
        Filter filter6 = new PropertyGreaterFilter("height", "180.5f");
        
        assertTrue(filter1.matches(user));
        assertTrue(filter2.matches(user));
        assertTrue(filter3.matches(user));
        assertEquals("height > 150.0", filter1.toQL());
        assertEquals("height > 150.2", filter2.toQL());
        assertEquals("height > 150.2", filter3.toQL());
        //System.out.println(filter1.toString());
        //System.out.println(filter2.toString());
        //System.out.println(filter3.toString());

        assertTrue(!filter4.matches(user));
        assertTrue(!filter5.matches(user));
        assertTrue(!filter6.matches(user));
    }

    // PropertyRegularFilter ------------------------------------------------
    @Test
    void testPropertyRegexFilter() {
        Filter filter = new PropertyRegularFilter("lastname", "M.*");

        assertTrue(filter.matches(user));
	assertEquals("lastname MATCHES M.*", filter.toQL());
	//System.out.println(filter.toString());
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
	assertEquals("((role = \"Junior Java Developer\" AND age > 20.0) OR firstname MATCHES x.*)", filter.toQL());
	//System.out.println(filter.toString());
    }
   
    // REVIEW: new test for fluent API
    @Test
    void testFluentAndLessFilter() {
       Filter filter1 = Filters.equalTo("role", "Junior Java Developer")
                          .and("age", "35");

       Filter filter2 = Filters.lessThan("age", "45")
	                  .and(Filters.lessThan("weight","60"));
       
       assertTrue(filter1.matches(user));
       assertTrue(filter2.matches(user));

       //System.out.println(filter1.toString());
       //System.out.println(filter2.toString());
    }

    
}


