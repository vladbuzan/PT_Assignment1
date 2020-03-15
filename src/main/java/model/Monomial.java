package model;

public class Monomial {
    private Number coefficient;
    private int degree;

    public Monomial(Number coefficient, int degree) {
        this.coefficient = coefficient;
        this.degree = degree;
    }

    public Monomial(Monomial monomial) {
        this.coefficient = monomial.coefficient;
        this.degree = monomial.degree;
    }
    int getDegree(){
        return degree;
    }

    public Number getCoefficient() {
        return coefficient;
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

    Monomial multiply(Monomial monomialArg) {
        return new Monomial(coefficient.doubleValue() * monomialArg.coefficient.doubleValue(),
                degree + monomialArg.degree);
    }

    void divide(Monomial monomial) {
        degree = degree - monomial.degree;
        coefficient = coefficient.doubleValue() / monomial.coefficient.doubleValue();
    }
    void addToCoefficient(Number value) {
        coefficient = coefficient.doubleValue() + value.doubleValue();
    }

    void negate(){
        coefficient = -coefficient.intValue();
    }

    @Override
    public String toString() {
        String coefficientString;
        if(coefficient.doubleValue() == 1.0) {
            coefficientString = "";
        } else {
            coefficientString = ((coefficient.doubleValue() - Math.floor(coefficient.doubleValue())) == 0) ?
                    Integer.toString(coefficient.intValue()) : String.format("%.2f", coefficient.doubleValue());
        }
        return ((coefficient.doubleValue() > 0 ? "+":"") + coefficientString + (degree != 0 ? "x" : " ")
                + ((degree != 1) && (degree != 0) ? "^" + degree : "") + " ");
    }

}
