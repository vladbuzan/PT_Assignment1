package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
//TODO Division
public class Polynomial {
    private List<Monomial> polynomial;
    public Polynomial() {
        polynomial = new ArrayList<Monomial>();
    }
    public void differentiate() {
        polynomial.forEach(Monomial::differentiate);
        removeZeroes();
    }
    public void integrate() {
        polynomial.forEach(Monomial::integrate);
    }
    private void addRemainingMonomials(Iterator<Monomial> iterator) {
        while(iterator.hasNext()) {
            Monomial m = iterator.next();
            polynomial.add(m);
        }
    }
    public void add(Polynomial polynomialArg) {
        ListIterator<Monomial> iterator1 = polynomial.listIterator();
        ListIterator<Monomial> iterator2 = polynomialArg.polynomial.listIterator();
        Monomial monomial1 = iterator1.next();
        Monomial monomial2 = iterator2.next();
        while (true) {
            if (monomial1.getDegree() == monomial2.getDegree()) {
                addToMonomialCoeff(monomial1, monomial2.getCoefficient().doubleValue());
                if (iterator1.hasNext() && iterator2.hasNext()) {
                    monomial1 = iterator1.next();
                    monomial2 = iterator2.next();
                } else {
                    addRemainingMonomials(iterator2);
                    break;
                }
            } else if (monomial1.getDegree() > monomial2.getDegree()) {
                if (iterator1.hasNext()) {
                    monomial1 = iterator1.next();
                } else {
                    addRemainingMonomials(iterator2);
                    break;
                }
            } else {
                polynomial.add(monomial2);
                if (iterator2.hasNext()) {
                    monomial2 = iterator2.next();
                } else break;
            }
        }
        removeZeroes();
        sort();
    }
    public void subtract(Polynomial polynomialArg) {
        polynomialArg.negate();
        add(polynomialArg);
    }
    public void simplify(){
        sort();
        ListIterator<Monomial> it = polynomial.listIterator();
        Monomial monomial = it.next();
        while(it.hasNext()) {
            Monomial next = it.next();
            if(monomial.getDegree() == next.getDegree()) {
                monomial.addToCoefficient(next.getCoefficient().doubleValue());
                polynomial.remove(next);
            } else {
                if(it.hasNext()) monomial = it.next();
            }
        }
    }
    private void negate() {
        polynomial.forEach(Monomial::negate);
    }
    private void sort() {
        polynomial.sort(Monomial::compareTo);
    }
    private void addToMonomialCoeff(Monomial monomial, Number coeff) {
        monomial.addToCoefficient(coeff);
    }
    public void addMonomial(Monomial monomial) {
        polynomial.add(monomial);
    }
    public void multiply(Polynomial polynomialArg){
        Polynomial newPolynomial = new Polynomial();
        for(Monomial monomial1 : polynomial) {
            for(Monomial monomial2 : polynomialArg.polynomial) {
                newPolynomial.addMonomial(monomial1.multiply(monomial2));
            }
        }
        polynomial = newPolynomial.polynomial;
        simplify();
    }
    private void removeZeroes(){
        ListIterator<Monomial> iterator = polynomial.listIterator();
        while(iterator.hasNext()) {
            Monomial monomial = iterator.next();
            if(monomial.getCoefficient().doubleValue() == 0.0) {
                iterator.remove();
            }
        }
    }
    public static void main(String[] args) {

        Polynomial p = new Polynomial();
        Polynomial m = new Polynomial();
        p.addMonomial(new Monomial(2,3));
        p.addMonomial(new Monomial(1,2));
        m.addMonomial(new Monomial(1,3));
        m.addMonomial(new Monomial(2,1));
        System.out.println(p + "\n" + m);
        p.multiply(m);
        System.out.println(p);
    }
    @Override
    public String toString() {
        String ret = "";
        if(polynomial.size() == 0) return "0";
        for(Monomial monomial : polynomial) {
            ret += monomial.toString();
        }
        return ret;
    }
}
