package com.ZohoIncubation.Task5;

public class Variable {
    private String variable;
    private Value value;

    public Variable(String variable, String val) {
        this.variable = variable;
        this.value = new Value(val);
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }
}
