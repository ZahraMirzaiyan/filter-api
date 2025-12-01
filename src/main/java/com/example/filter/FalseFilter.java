package com.example.filter;

import java.util.Map;

public class FalseFilter implements Filter {
    @Override
    public boolean matches(Map<String, String> resource) {
        return false;
    }

    //**** REVIEW ****//
    @Override
    public String toQL() {
        return "FALSE";
    }
    //**** REVIEW ****//

   /* @Override
    public String toString() {
       return "FALSE";
    }
   */
    
       
    @Override
    public void accept(FilterVisitor visitor) {
        visitor.visit(this);
    }
}
