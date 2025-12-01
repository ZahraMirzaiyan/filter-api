package com.example.filter;

import java.util.Map;

public class PropertyRegularFilter implements Filter {
    private final String property;
    private final String regex;

    public PropertyRegularFilter(String property, String regex) {
        this.property = property;
        this.regex = regex;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        String prop = resource.get(property);
        return prop != null && prop.toLowerCase().matches(regex.toLowerCase());
    }

    //**** REVIEW ****
    @Override
    public String toQL() {
        return property + " MATCHES " + regex;
    } 
    //**** REVIEW ****
/*
    @Override
    public String toString() {
        return property + " matches " + regex;
    }
*/  
    @Override
    public void accept(FilterVisitor visitor) {
        visitor.visit(this);
    }
}
