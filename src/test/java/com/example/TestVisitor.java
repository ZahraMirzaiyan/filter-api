package com.example.filter;

import java.util.ArrayList;
import java.util.List;

public class TestVisitor implements FilterVisitor {

    public final List<String> visited = new ArrayList<>();

    @Override
    public void visit(TrueFilter filter) { visited.add("TRUE"); }

    @Override
    public void visit(FalseFilter filter) { visited.add("FALSE"); }

    @Override
    public void visit(AndFilter filter) { visited.add("AND"); }

    @Override
    public void visit(OrFilter filter) { visited.add("OR"); }

    @Override
    public void visit(NotFilter filter) { visited.add("NOT"); }

    @Override
    public void visit(PropertyPresentFilter filter) { visited.add("PRESENT"); }

    @Override
    public void visit(PropertyEqualsFilter filter) { visited.add("EQUALS"); }

    @Override
    public void visit(PropertyLessFilter filter) { visited.add("LESS"); }

    @Override
    public void visit(PropertyGreaterFilter filter) { visited.add("GREATER"); }

    @Override
    public void visit(PropertyRegularFilter filter) { visited.add("REGULAR"); }
}
