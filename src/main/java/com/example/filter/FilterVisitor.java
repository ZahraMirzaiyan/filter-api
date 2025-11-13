package com.example.filter;

public interface FilterVisitor {
    void visit(TrueFilter filter);
    void visit(FalseFilter filter);
    void visit(AndFilter filter);
    void visit(OrFilter filter);
    void visit(NotFilter filter);
    void visit(PropertyPresentFilter filter);
    void visit(PropertyEqualsFilter filter);
    void visit(PropertyLessFilter filter);
    void visit(PropertyGreaterFilter filter);
    void visit(PropertyRegularFilter filter);
}

