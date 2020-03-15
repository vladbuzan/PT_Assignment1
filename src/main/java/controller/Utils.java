package controller;

import model.Monomial;
import model.Polynomial;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static model.PolynomialUtils.*;

public class Utils {
    private static Pattern polynomialPattern;
    private static Pattern monomialPattern;
    private static Pattern splitPattern;
    private static Pattern coefficientPattern;
    private static Pattern degreePattern;
    private static Pattern xPattern;
    private static Matcher polynomialMatcher;
    private static Matcher monomialMatcher;

    static {
        polynomialPattern = Pattern.compile("((\\+?|\\-?)\\d{0,6}?\\*?x(\\^\\d{1,6})?)|" +
                                "((\\+|\\-)?\\d{1,6})");
        monomialPattern = Pattern.compile("(\\d{0,6}?\\*?x(\\^\\d{1,6})?)|(\\d{1,6})");
        splitPattern = Pattern.compile("\\+|\\-");
        coefficientPattern = Pattern.compile("^(\\+|\\-)?\\d{1,6}\\*?x");
        degreePattern = Pattern.compile("x\\^\\d{1,6}");
        xPattern = Pattern.compile("(\\+|\\-)?x");
    }
    private static boolean isValidPolynomial(String polynomial) {
        String[] monomials = splitPattern.split(polynomial);
        for(String monomial : monomials) {
            polynomialMatcher = monomialPattern.matcher(monomial);
            if(monomial.compareTo("") == 0) continue;
            if(!polynomialMatcher.matches()) {
                return false;
            }
        }
        return true;
    }
    private static Monomial parseMonomial(String monomialString) throws NumberFormatException {
        monomialString = monomialString.replace("*", "");
        Number coefficient;
        int degree;
        monomialMatcher = coefficientPattern.matcher(monomialString); //monomial of type (+/-)yy*x
        if(monomialMatcher.find()) {
            coefficient = Integer.parseInt(monomialString.substring(0, monomialMatcher.end() - 1));
            monomialMatcher = degreePattern.matcher(monomialString); //looking for x^yyy
            if(monomialMatcher.find()) {
                degree = Integer.parseInt(monomialString.substring(monomialMatcher.start() + 2));
                return new Monomial(coefficient, degree); //found (+/-)yyy*x^zzz
            } else return new Monomial(coefficient, 1); //found (+/-)yyy*x
        } else {
            monomialMatcher = degreePattern.matcher(monomialString);
            if(monomialMatcher.find()) {
                degree = Integer.parseInt(monomialString.substring(monomialMatcher.start() + 2));
                coefficient = (monomialString.charAt(0) == '-')? -1:1;
                return new Monomial(coefficient, degree);
            } else {
                monomialMatcher = xPattern.matcher(monomialString);
                if(monomialMatcher.matches()) {
                    if(monomialString.charAt(0) == '-') return new Monomial(-1,1);
                    else return new Monomial(1,1);
                } else return new Monomial(Integer.parseInt(monomialString), 0);
            }
        }
    }
    public static Polynomial parsePolynomial (String polynomialString) throws IOException, NumberFormatException{
        Polynomial polynomial = new Polynomial();
        polynomialString = polynomialString.replace(" ", "");
        if(!isValidPolynomial(polynomialString)) {
            throw new IOException("Couldn't parse polynomial");
        }
        polynomialMatcher = polynomialPattern.matcher(polynomialString);
        while(polynomialMatcher.find()){
            polynomial.addMonomial(parseMonomial(polynomialMatcher.group()));
        }
        simplify(polynomial);
        return polynomial;
    }

    public static void main(String[] args) {
        try {
            Polynomial m = parsePolynomial("2x^2 + 3");
            m.integrate();
            System.out.println(m);
        } catch(Exception ex) {
            System.out.println("error:" + ex.getMessage());
        }
    }
}
