package com.example.filter;

import java.util.Map;

public class PropertyEqualsFilter implements Filter {
    private final String property;
    private final String value;

    public PropertyEqualsFilter(String property, String value) {
        this.property = property;
        this.value = value; //value.toLowerCase();
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        String prop = resource.get(property);
        return prop != null && prop.equalsIgnoreCase(value);
    }
    
    @Override
    public String toString() {
        return property + " = " + value;
    }
    
    @Override
    public void accept(FilterVisitor visitor) {
        visitor.visit(this);
    }
}
