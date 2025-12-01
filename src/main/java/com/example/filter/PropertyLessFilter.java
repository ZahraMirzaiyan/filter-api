package com.example.filter;

import java.util.Map;

public class PropertyLessFilter implements Filter {
    private final String property;
    //private final int value;
    private final double value; // REVIEW

    public PropertyLessFilter(String property, String value) {
        this.property = property;
        //this.value = Integer.parseInt(value);
	this.value = Double.parseDouble(value); // REVIEW
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        String prop = resource.get(property);
        if (prop == null) return false;
        try {
              double numericProp = Double.parseDouble(prop); // REVIEW
	      return numericProp < value;                    // REVIEW
            //return Integer.parseInt(prop) < value;
        } catch (NumberFormatException e) {
            return false;
        }
    }
   
    //**** REVIEW ****//
    @Override
    public String toQL() {
        return property + " < " + value;
    }  
    //**** REVIEW ****//
/*
    @Override
    public String toString() {
        return property + " < " + value;
    } 
*/  
    @Override
    public void accept(FilterVisitor visitor) {
        visitor.visit(this);
    }

}
