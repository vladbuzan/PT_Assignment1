package model;

public class Monomial {
    private Number coefficient;
    private int degree;

    public Monomial(Number coefficient, int degree) {
        this.coefficient = coefficient;
        this.degree = degree;
    }
    void differentiate() {
        coefficient = coefficient.intValue() * degree;
        degree --;
    }
    void integrate() {
        degree ++;
        coefficient = coefficient.doubleValue()/degree;
    }
    int compareTo(Monomial monomial) {
        if(this.degree < monomial.degree) return 1;
        else return -1;
    }
    Monomial multiply(Monomial m1) {
        System.out.println("multiplying" + m1 + "\n" + this);
        return new Monomial(coefficient.intValue() * m1.coefficient.intValue(),degree + m1.degree);
    }
    double getDegree(){
        return degree;
    }
    public Number getCoefficient() {
        return coefficient;
    }
    void addToCoefficient(Number value) {
        coefficient = coefficient.doubleValue() + value.doubleValue();
    }
    void negate(){
        coefficient = -coefficient.intValue();
    }
    @Override
    public String toString() {
        return ((coefficient.doubleValue() >= 0 ? "+ ":"- ") + coefficient + (degree != 0 ? "x" : " ")
                + ((degree != 1) && (degree != 0) ? "^" + degree : "") + " ");
    }


public static void main(String[] args) {
    Monomial m = new Monomial(2,3);
    m.integrate();
    System.out.println(m.coefficient);
}
}
