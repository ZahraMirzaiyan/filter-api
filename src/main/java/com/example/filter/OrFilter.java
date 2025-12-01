package com.example.filter;

import java.util.Map;

public class OrFilter implements Filter {
    private final Filter left;
    private final Filter right;

    public OrFilter(Filter left, Filter right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        return left.matches(resource) || right.matches(resource);
    }
    
    //**** REVIEW ****//
    @Override
    public String toQL() {
        return "(" + left.toQL() + " OR " + right.toQL() + ")";
    }
    //**** REVIEW ****//
/*
    @Override
    public String toString() {
        return "(" + left + " OR " + right + ")";
    }
*/  
       
    @Override
    public void accept(FilterVisitor visitor) {
        visitor.visit(this);
        left.accept(visitor);
        right.accept(visitor);
    }
}
