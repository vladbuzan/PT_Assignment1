package model;

import java.util.ListIterator;

public class PolynomialUtils {

    static void removeZeroes(Polynomial polynomialArg) {
        ListIterator<Monomial> iterator = polynomialArg.polynomial.listIterator();
        while(iterator.hasNext()) {
            Monomial monomial = iterator.next();
            if(monomial.getCoefficient().doubleValue() == 0.0) {
                iterator.remove();
            }
        }
    }

    static void negate(Polynomial polynomialArg) {
        polynomialArg.polynomial.forEach(Monomial::negate);
    }

    static void sort(Polynomial polynomialArg) {
        polynomialArg.polynomial.sort(Monomial::compareTo);
    }

    static Monomial lead(Polynomial polynomialArg) {
        return polynomialArg.polynomial.get(0);
    }

    static Polynomial copy(Polynomial polynomialArg) {
        Polynomial copy = new Polynomial();
        for (Monomial monomial : polynomialArg.polynomial) {
            copy.addMonomial(new Monomial(monomial));
        }
        return copy;
    }

    static Polynomial multiplyByMonomial(Polynomial polynomialArg, Monomial monomialArg) {
        Polynomial ret = new Polynomial();
        for(Monomial monomial : polynomialArg.polynomial) {
            ret.addMonomial(monomial.multiply(monomialArg));
        }
        return ret;
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
