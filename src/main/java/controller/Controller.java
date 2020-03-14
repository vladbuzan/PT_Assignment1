package controller;

import model.Polynomial;
import view.MainWindow;
import view.Operation;

import static view.Operation.*;

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
                    Add();
                    break;
                case Subtract:
                    Subtract();
                    break;
                case Integrate:
                    Integrate();
                    break;
                case Differentiate:
                    Differentiate();
                    break;
                case Divide:
                    Divide();
                    break;
                case Multiply:
                    Multiply();
                    break;
            }
        });
    }

    private void Multiply() {
    }
    //TODO division oh god
    private void Divide() {
    }

    private void Differentiate() {
        try {
            Polynomial polynomial = Utils.parsePolynomial(view.getPolynomial());
            polynomial.differentiate();
            view.updateResult(polynomial.toString());
        } catch(Exception ex) {
            view.displayError();
        }
    }

    private void Integrate() {
        try {
            Polynomial polynomial = Utils.parsePolynomial(view.getPolynomial());
            polynomial.integrate();
            view.updateResult(polynomial.toString());
        } catch(Exception ex) {
            view.displayError();
        }
    }

    private void Add() {
        try{
            Polynomial polynomial1 = Utils.parsePolynomial(view.getPolynomial1());
            Polynomial polynomial2 = Utils.parsePolynomial(view.getPolynomial2());
            polynomial1.add(polynomial2);
            view.updateResult(polynomial1.toString());
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
            view.displayError();
        }

    }

    private void Subtract() {
        try{
            Polynomial polynomial1 = Utils.parsePolynomial(view.getPolynomial1());
            Polynomial polynomial2 = Utils.parsePolynomial(view.getPolynomial2());
            polynomial1.subtract(polynomial2);
            view.updateResult(polynomial1.toString());
        } catch(Exception ex) {
            view.displayError();
        }
    }


    public static void main(String[] args) {
        Controller ct = new Controller();
    }
}
