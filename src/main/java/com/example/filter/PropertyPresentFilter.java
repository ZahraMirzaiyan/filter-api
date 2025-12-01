package com.example.filter;

import java.util.Map;

public class PropertyPresentFilter implements Filter {
    private final String property;

    public PropertyPresentFilter(String property) {
        this.property = property;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        return resource.containsKey(property);
    }
   
    //**** REVIEW ****//
    @Override
    public String toQL() {
        return property + " IS PRESENT";
    }
    //**** REVIEW ****//
/*
    @Override
    public String toString() {
        return property + " is present";
    }
*/  
    
    @Override
    public void accept(FilterVisitor visitor) {
        visitor.visit(this);
    }
}
