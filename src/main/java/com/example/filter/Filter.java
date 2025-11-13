package com.example.filter;

import java.util.Map;

public interface Filter {
    boolean matches(Map<String, String> resource);
    void accept(FilterVisitor visitor);
}
