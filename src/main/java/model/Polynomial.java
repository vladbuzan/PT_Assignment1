package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Polynomial {
    private List<Monomial> polynomial;
    Polynomial() {
        polynomial = new ArrayList<Monomial>();
        polynomial.add(new Monomial(1,2.0));
        polynomial.add(new Monomial(1,4.0));
        polynomial.add(new Monomial(1,1.0));
        sort();
        polynomial.forEach(System.out::println);
    }
    Polynomial(int i) {
        polynomial = new ArrayList<Monomial>();
        polynomial.add(new Monomial(1,2.0));
        polynomial.add(new Monomial(1,4.0));
        polynomial.add(new Monomial(1,3.0));
        sort();
        polynomial.forEach(System.out::println);
    }

    void derrivate(Polynomial polynomial) {
        for(Monomial monomial : this.polynomial) {
            monomial.derrivate();
        }
    }
    void integrate(Polynomial polynomial) {
        for(Monomial monomial : this.polynomial) {
            monomial.integrate();
        }
    }
    private void addRemainingMonomials(Iterator<Monomial> iterator) {
        while(iterator.hasNext()) {
            Monomial m = iterator.next();
            polynomial.add(m);
        }
    }
    void add(Polynomial polynomialArg) {
        ListIterator<Monomial> iterator1 = polynomial.listIterator();
        ListIterator<Monomial> iterator2 = polynomialArg.polynomial.listIterator();
        Monomial monomial1 = iterator1.next();
        Monomial monomial2 = iterator2.next();
        while (true) {
            if (monomial1.getDegree() == monomial2.getDegree()) {
                addToMonomialCoeff(monomial1, monomial2.getCoefficient());
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
        sort();
        polynomial.forEach(System.out::println);
    }
    private void sort() {
        polynomial.sort(Monomial::compareTo);
    }
    private void addToMonomialCoeff(Monomial monomial, double coeff) {
        monomial.addToCoefficient(coeff);
        if(monomial.getCoefficient() == 0) {
            polynomial.remove(monomial);
        }
    }
    public static void main(String[] args) {
        Polynomial test1 = new Polynomial();
        Polynomial test2 = new Polynomial(3);
        test1.add(test2);
        return;
    }

}
