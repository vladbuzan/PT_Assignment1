package controller;

import model.Polynomial;
import view.MainWindow;
import view.Operation;
import static controller.Utils.*;

public class Controller {
    private MainWindow view;
    public Controller() {
        view = new MainWindow();
        init();
    }

    private void init() {
        view.addComputeBtnListener((event)-> {
            Operation operation = view.getOperation();
            switch (operation) {
                case Add:
                    add();
                    break;
                case Subtract:
                    subtract();
                    break;
                case Integrate:
                    integrate();
                    break;
                case Differentiate:
                    differentiate();
                    break;
                case Divide:
                    divide();
                    break;
                case Multiply:
                    multiply();
                    break;
            }
        });
    }

    private void add() {
        try{
            Polynomial polynomial1 = parsePolynomial(view.getPolynomial1());
            Polynomial polynomial2 = parsePolynomial(view.getPolynomial2());
            polynomial1.add(polynomial2);
            view.updateResult(polynomial1.toString());
        } catch(Exception ex) {
            view.updateResult("Error: " + ex.getMessage());
        }
    }

    private void subtract() {
        try{
            Polynomial polynomial1 = parsePolynomial(view.getPolynomial1());
            Polynomial polynomial2 = parsePolynomial(view.getPolynomial2());
            polynomial1.subtract(polynomial2);
            view.updateResult(polynomial1.toString());
        } catch(Exception ex) {
            view.updateResult("Error: " + ex.getMessage());
        }
    }

    private void differentiate() {
        try {
            Polynomial polynomial = parsePolynomial(view.getPolynomial());
            polynomial.differentiate();
            view.updateResult(polynomial.toString());
        } catch(Exception ex) {
            view.updateResult(ex.getMessage());
        }
    }

    private void integrate() {
        try {
            Polynomial polynomial = parsePolynomial(view.getPolynomial());
            polynomial.integrate();
            view.updateResult(polynomial.toString());
        } catch(Exception ex) {
            view.updateResult("Error: " + ex.getMessage());
        }
    }

    private void multiply() {
        try {
            Polynomial polynomial1 = parsePolynomial(view.getPolynomial1());
            Polynomial polynomial2 = parsePolynomial(view.getPolynomial2());
            polynomial1.multiply(polynomial2);
            view.updateResult(polynomial1.toString());
        } catch(Exception ex) {
            view.updateResult("Error: " + ex.getMessage());
        }
    }

    private void divide() {
        try {
            Polynomial polynomial1 = parsePolynomial(view.getPolynomial1());
            Polynomial polynomial2 = parsePolynomial(view.getPolynomial2());
            String polynomial2String = polynomial2.toString();
            Polynomial rest = polynomial1.divide(polynomial2);
            String restString = (rest.toString().compareTo("0") == 0) ? "" : "rest: ("
                    + rest.toString() + ")/(" + polynomial2String + ")" ;
            String result = "quotient: " + polynomial1 + restString;
            view.updateResult(result);
        } catch (Exception ex) {
            view.updateResult("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}
