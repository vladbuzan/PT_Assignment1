package model;

import static model.PolynomialUtils.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Polynomial {
    List<Monomial> polynomial;

    public Polynomial() {
        polynomial = new ArrayList<Monomial>();
    }

    public void add(Polynomial polynomialArg) {
        ListIterator<Monomial> iterator1 = polynomial.listIterator();
        ListIterator<Monomial> iterator2 = polynomialArg.polynomial.listIterator();
        Monomial monomial1 = iterator1.next();
        Monomial monomial2 = iterator2.next();
        while (true) {
            if (monomial1.getDegree() == monomial2.getDegree()) {
                monomial1.addToCoefficient(monomial2.getCoefficient().doubleValue());
                if (iterator1.hasNext() && iterator2.hasNext()) {
                    monomial1 = iterator1.next();
                    monomial2 = iterator2.next();
                } else {
                    addRemainingMonomials(this, iterator2);
                    break;
                }
            } else if (monomial1.getDegree() > monomial2.getDegree()) {
                if (iterator1.hasNext()) {
                    monomial1 = iterator1.next();
                } else {
                    addRemainingMonomials(this, iterator2);
                    break;
                }
            } else {
                polynomial.add(monomial2);
                if (iterator2.hasNext()) monomial2 = iterator2.next();
                 else break;
            }
        }
        removeZeroes(this);
        sort(this);
    }

    public void subtract(Polynomial polynomialArg) {
        negate(polynomialArg);
        add(polynomialArg);
    }

    public void differentiate() {
        polynomial.forEach(Monomial::differentiate);
        removeZeroes(this);
    }

    public void integrate() {
        polynomial.forEach(Monomial::integrate);
    }

    public void multiply(Polynomial polynomialArg){
        List<Monomial> polynomial = new ArrayList<>();
        for(Monomial monomial1 : this.polynomial) {
            for(Monomial monomial2 : polynomialArg.polynomial) {
                polynomial.add(monomial1.multiply(monomial2));
            }
        }
        this.polynomial = polynomial;
        simplify(this);
        removeZeroes(this);
    }

    public Polynomial divide(Polynomial polynomialArg) throws ArithmeticException{ //quotient in this(), rest is returned
        if (lead(polynomialArg).getCoefficient().doubleValue() == 0) throw new ArithmeticException("Division by 0");
        Polynomial quotient = new Polynomial();
        Polynomial rest = copy(this);
        Monomial term;
        while((rest.polynomial.size() > 0) && (lead(rest).getDegree() >= lead(polynomialArg).getDegree())) {
            term = new Monomial(lead(rest));
            term.divide(lead(polynomialArg));
            addMonomial(quotient, new Monomial(term));
            rest.subtract(multiplyByMonomial(polynomialArg, term));
        }
        this.polynomial = quotient.polynomial;
        return rest;
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
