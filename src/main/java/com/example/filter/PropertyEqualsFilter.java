//**** REVIEWED FOR THE MOST PART ****//

package com.example.filter;

import java.util.Map;

public class PropertyEqualsFilter implements Filter {
private final String property;
private final String stringValue;
private final Double numericValue;


public PropertyEqualsFilter(String property, String value) {
    this.property = property;
    this.stringValue = value;

    Double tempNum;
    try {
        tempNum = Double.parseDouble(value);
    } catch (NumberFormatException e) {
        tempNum = null; 
    }
    this.numericValue = tempNum;
}

@Override
public boolean matches(Map<String, String> resource) {
    String prop = resource.get(property);
    if (prop == null) return false;

    if (numericValue != null) {
        try {
            double propNum = Double.parseDouble(prop);
            return Double.compare(propNum, numericValue) == 0;
        } catch (NumberFormatException e) {
            return false;
        }
    } else {
        return prop.equalsIgnoreCase(stringValue);
    }
}

//**** NEW METHOD ****//
@Override
public String toQL() {
    if (numericValue != null) {
        
        return property + " = " + numericValue;
    } else {
        
        return property + " = \"" + stringValue + "\"";
    }
}

//*** NEW METHOD *** //
/*
@Override
public String toString() {
    if (numericValue != null) {
        // 
        return property + " = " + numericValue;
    } else {
        // 
        return property + " = \"" + stringValue + "\"";
   }
}
*/

@Override
public void accept(FilterVisitor visitor) {
    visitor.visit(this);
}


}

