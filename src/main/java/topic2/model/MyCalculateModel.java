package topic2.model;

public class MyCalculateModel {

    private double value1;
    private double value2;
    private double answer;

    public double getValue1() {
        return value1;
    }

    public void setValue1(double value1) {
        this.value1 = value1;
    }

    public double getValue2() {
        return value2;
    }

    public void setValue2(double value2) {
        this.value2 = value2;
    }

    public double getAnswer() {
        return answer;
    }

    public void setAnswer(double answer) {
        this.answer = answer;
    }

    public void add() {
        this.answer = this.value1 + this.value2;
    }

    public void sub() {
        this.answer = this.value1 - this.value2;
    }

    public void multiply() {
        this.answer = this.value1 * this.value2;
    }

    public void divide() {
        this.answer = this.value1 / this.value2;
    }

    public void mod() {
        this.answer = this.value1 % this.value2;
    }

    public void pow() {
        this.answer = Math.pow(this.value1, this.value2);
    }
}
