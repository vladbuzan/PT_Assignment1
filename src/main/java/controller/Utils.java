package controller;

import model.Polynomial;

import java.io.IOException;
//TODO parsing stuff
public class Utils {

    Polynomial parsePolynomial (String polynomialString) throws IOException {
        Polynomial ret = new Polynomial();
        String[] monomials = polynomialString.split("\\+|\\-");
        for (String monomial : monomials) {
            System.out.println(monomial);
        }
        return new Polynomial();
    }
}
