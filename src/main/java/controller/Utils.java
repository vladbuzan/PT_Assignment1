package controller;

import model.Polynomial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TODO parsing stuff
public class Utils {
    private static boolean isValidPolynomial(String polynomial) {
        String polynomialClone = polynomial.replace(" ", "");
        String[] monomials = polynomialClone.split("\\+|\\-");
        for(String a : monomials) {
            System.out.println(a);
        }
        return true;
    }
    public static Polynomial parsePolynomial (String polynomialString){
        Pattern pattern = Pattern.compile("((\\+?|\\-?)\\d{0,6}?\\*?x(\\^\\d{1,6})?)|((\\+|\\-)\\d{1,6})");
        Matcher matcher = pattern.matcher(polynomialString);
        while(matcher.find()){
            System.out.println(matcher.group());
        }
        return new Polynomial();
    }

    public static void main(String[] args) {
        System.out.println(isValidPolynomial("+3x   + 5"));
    }
}
