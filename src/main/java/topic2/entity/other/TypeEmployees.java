package topic2.entity.other;

public enum TypeEmployees {
    NORMAL(1),
    TESTER(1.2),
    DESIGNER(1.5),
    PROGRAMMER(2.5);

    private final double coefficient;

    TypeEmployees(double coefficient) {
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }
}