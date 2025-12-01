//**** NEW CLASS ****//

package com.example.filter;

import java.util.Map;

public final class Filters {

    private Filters() {}

    public static Filter greaterThan(String property, String value) {
        return new PropertyGreaterFilter(property, value);
    }

    public static Filter lessThan(String property, String value) {
        return new PropertyLessFilter(property, value);
    }
    
    public static Filter equalTo(String property, String value) {
        return new PropertyEqualsFilter(property, value);
    }

    public static Filter and(Filter a, Filter b) {
        return new AndFilter(a, b);
    }

    /*public static Filter or(Filter a, Filter b) {
        return new OrFilter(a, b);
    }
    */
}
