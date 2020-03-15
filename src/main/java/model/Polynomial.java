package model;
import static model.PolynomialUtils.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
//TODO Division
public class Polynomial {
    List<Monomial> polynomial;
    public Polynomial() {
        polynomial = new ArrayList<Monomial>();
    }
    public Polynomial(List<Monomial> polynomial) {
        this.polynomial = polynomial;
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
        Polynomial newPolynomial = new Polynomial();
        for(Monomial monomial1 : polynomial) {
            for(Monomial monomial2 : polynomialArg.polynomial) {
                newPolynomial.addMonomial(monomial1.multiply(monomial2));
            }
        }
        polynomial = newPolynomial.polynomial;
        simplify(this);
    }


    private void addRemainingMonomials(Iterator<Monomial> iterator) {
        while(iterator.hasNext()) {
            Monomial m = iterator.next();
            polynomial.add(m);
        }
    }

    public Polynomial divide(Polynomial polynomialArg) { //quotient in this(), rest is returned
        Polynomial quotient = new Polynomial();
        Polynomial rest = copy(this);
        Monomial term;
        while((rest.polynomial.size() > 0) && (lead(rest).getDegree() >= lead(polynomialArg).getDegree())) {
            term = new Monomial(lead(rest));
            term.divide(lead(polynomialArg));
            quotient.addMonomial(new Monomial(term));
            rest.subtract(multiplyByMonomial(polynomialArg, term));
        }
        this.polynomial = quotient.polynomial;
        return rest;
    }

    public void addMonomial(Monomial monomial) {
        polynomial.add(monomial);
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
    int degree(){
    return 0;}

}
