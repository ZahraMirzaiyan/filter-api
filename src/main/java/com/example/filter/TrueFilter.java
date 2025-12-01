package com.example.filter;

import java.util.Map;
import java.util.HashMap;

public class TrueFilter implements Filter {
    @Override
    public boolean matches(Map<String, String> resource) {
        return true;
    }

    //**** REVIEW ****//
    @Override
    public String toQL() {
        return "TRUE";
    }
    //**** REVIEW ****//

   /* @Override
    public String toString() {
        return "TRUE";
    }
    */

    @Override
    public void accept(FilterVisitor visitor) {
        visitor.visit(this);
    }
}
