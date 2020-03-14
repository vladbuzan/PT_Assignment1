package controller;

import view.MainWindow;
import view.Operation;

public class Controller {
    private MainWindow view;
    public Controller() {
        view = new MainWindow();
        view.addComputeBtnListener((event)->{
            Operation operation = view.getOperation();
            switch(operation) {
                case Add:
                    break;
                case Subtract:
                    break;
                case Integrate:
                    break;
                case Differentiate:
                    break;
                case Divide:
                    break;
                case Multiply:
                    break;
            }
        });
    }


    public static void main(String[] args) {
        Controller ct = new Controller();
    }
}
