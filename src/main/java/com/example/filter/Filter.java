package com.example.filter;

import java.util.Map;

public interface Filter {
    boolean matches(Map<String, String> resource);

    //**** REVIEW ****//
    String toQL();

    default Filter and(String property, Object value) {
        return new AndFilter(this, Filters.equalTo(property, String.valueOf(value)));
    }

    

    default Filter and(Filter other) {
        return new AndFilter(this, other);
    }

    
    //**** REVIEW ****//

    void accept(FilterVisitor visitor);
}
