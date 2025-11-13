package com.example.filter;

import java.util.Map;

public class NotFilter implements Filter {
    private final Filter target;

    public NotFilter(Filter target) {
        this.target = target;
    }

    @Override
    public boolean matches(Map<String, String> resource) {
        return !target.matches(resource);
    }
    
    @Override
    public String toString() {
        return "NOT " + target;
    }
    
    @Override
    public void accept(FilterVisitor visitor) {
        visitor.visit(this);
        target.accept(visitor);
    }
}
