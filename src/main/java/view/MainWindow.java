package view;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private final static  String[] operations = {"Add", "Subtract", "Differentiate", "Integrate", "Multiply"};
    private final static String[] forms = {"Form 1", "Form 2"};

    private JLabel polynomialLabel1;
    private JTextField polynomial1TF;
    private JLabel polynomialLabel2;
    private JLabel polynomialLabel3;
    private JTextField getPolynomial2TF;
    private JPanel mainPanel;
    private JPanel panelForm1;
    private JPanel panelForm2;
    private JPanel cards;

    private JPanel operationSelectPanel;
    private JComboBox operationSelectCB;
    private JButton computeButton;

    public MainWindow() {
        super("Polynomial Calculator");
        mainPanel = new JPanel();
        panelForm1 = new JPanel();
        panelForm2 = new JPanel();
        operationSelectPanel = new JPanel();
        cards = new JPanel(new CardLayout());
        polynomialLabel1 = new JLabel("Polynomial: ");
        polynomialLabel2 = new JLabel("Polynomial 1: ");
        polynomialLabel3 = new JLabel("Polynomial 2: ");
        operationSelectCB = new JComboBox(operations);
        operationSelectPanel.add(operationSelectCB);

        panelForm1.add(polynomialLabel1);
        panelForm2.add(polynomialLabel2);
        panelForm2.add(polynomialLabel3);
        cards.add(panelForm1, forms[0]);
        cards.add(panelForm2, forms[1]);
        mainPanel.add(operationSelectPanel);
        mainPanel.add(cards);
        operationSelectCB.addItemListener(evt -> {
           CardLayout cardLayout = (CardLayout)(cards.getLayout());
           String item = (String)evt.getItem();
           if(item.equals(operations[2]) || item.equals(operations[3])) {
               cardLayout.show(cards, forms[0]);
           } else {
               cardLayout.show(cards, forms[1]);
           }
        });
        setContentPane(mainPanel);
        setSize(350,300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public static void main(String[] args) {
        MainWindow lol = new MainWindow();
    }
}
