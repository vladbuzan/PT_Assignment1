package model;

public class Monomial {
    private double coefficient;
    private double degree;

    public Monomial(double coefficient, double degree) {
        this.coefficient = coefficient;
        this.degree = degree;
    }
    void differentiate() {
        coefficient *= degree;
        degree --;
    }
    void integrate() {
        degree ++;
        coefficient /= degree;
    }
    int compareTo(Monomial monomial) {
        if(this.degree < monomial.degree) return 1;
        else return -1;
    }
    double getDegree(){
        return degree;
    }
    public double getCoefficient() {
        return coefficient;
    }
    void addToCoefficient(double value) {
        coefficient += value;
    }
    void negate(){
        coefficient = -coefficient;
    }
    @Override
    public String toString() {
        return (Double.toString(getDegree()));
    }
}
