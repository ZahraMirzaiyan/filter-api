package com.example.filter;

import java.util.Map;

public class PropertyGreaterFilter implements Filter {
    private final String property;
    private final int value;

    public PropertyGreaterFilter(String property, String value) {
        this.property = property;
        this.value = Integer.parseInt(value);
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        String prop = resource.get(property);
        if (prop == null) return false;
        try {
            return Integer.parseInt(prop) > value;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    @Override
    public String toString() {
        return property + " > " + value;
    }
    
    @Override
    public void accept(FilterVisitor visitor) {
        visitor.visit(this);
    }
}
