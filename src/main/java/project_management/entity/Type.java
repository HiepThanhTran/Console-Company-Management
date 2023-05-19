package project_management.entity;

public enum Type {
    NORMAL(1),
    MANAGER(1),
    TESTER(1.2),
    DESIGNER(1.5),
    PROGRAMMER(2.5);

    private final double coefficient;

    Type(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }
}