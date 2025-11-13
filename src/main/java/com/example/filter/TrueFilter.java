package com.example.filter;

import java.util.Map;
import java.util.HashMap;

public class TrueFilter implements Filter {
    @Override
    public boolean matches(Map<String, String> resource) {
        return true;
    }

    @Override
    public String toString() {
        return "TRUE";
    }
    
    @Override
    public void accept(FilterVisitor visitor) {
        visitor.visit(this);
    }
}
