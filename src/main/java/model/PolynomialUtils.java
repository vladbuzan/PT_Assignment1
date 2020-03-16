package model;

import java.util.Iterator;
import java.util.ListIterator;

public class PolynomialUtils {

    static void sort(Polynomial polynomialArg) {
        polynomialArg.polynomial.sort(Monomial::compareTo);
    }

    static void negate(Polynomial polynomialArg) {
        polynomialArg.polynomial.forEach(Monomial::negate);
    }

    static Monomial lead(Polynomial polynomialArg) {
        return polynomialArg.polynomial.get(0);
    }

    static void removeZeroes(Polynomial polynomialArg) {
        polynomialArg.polynomial.removeIf(monomial -> monomial.getCoefficient().doubleValue() == 0.0);
    }

    static Polynomial multiplyByMonomial(Polynomial polynomialArg, Monomial monomialArg) {
        Polynomial ret = new Polynomial();
        for(Monomial monomial : polynomialArg.polynomial) {
            addMonomial(ret, monomial.multiply(monomialArg));
        }
        return ret;
    }

    static void addRemainingMonomials(Polynomial polynomialArg, Iterator<Monomial> iterator) {
        while(iterator.hasNext()) {
            Monomial m = iterator.next();
            polynomialArg.polynomial.add(m);
        }
    }

    static Polynomial copy(Polynomial polynomialArg) {
        Polynomial copy = new Polynomial();
        for (Monomial monomial : polynomialArg.polynomial) {
            addMonomial(copy, new Monomial(monomial));
        }
        return copy;
    }

    public static  void addMonomial(Polynomial polynomial ,Monomial monomial) {
        polynomial.polynomial.add(monomial);
    }

    public static void simplify(Polynomial polynomialArg){
        sort(polynomialArg);
        ListIterator<Monomial> it = polynomialArg.polynomial.listIterator();
        Monomial next;
        Monomial current = it.next();
        while(it.hasNext()) {
            next = it.next();
            if(current.getDegree() == next.getDegree()) {
                current.addToCoefficient(next.getCoefficient().doubleValue());
                it.remove();
            } else {
                if(it.hasNext()) {
                    current = next;
                }
            }
        }
    }
}