package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainWindow extends JFrame {
    private final static  String[] operations = {"Add", "Subtract", "Differentiate", "Integrate", "Multiply"};
    private final static String[] forms = {"Form 1", "Form 2"};

    private JTextField polynomial1TF;
    private JTextField polynomial2TF;
    private JTextField polynomial3TF;
    private JTextField resultTF;
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
        panelForm1 = new JPanel(new BorderLayout());
        panelForm2 = new JPanel(new BorderLayout());
        operationSelectPanel = new JPanel();
        cards = new JPanel(new CardLayout());
        JLabel polynomialLabel1 = new JLabel("Polynomial: ");
        JLabel polynomialLabel2 = new JLabel("Polynomial 1: ");
        JLabel polynomialLabel3 = new JLabel("Polynomial 2: ");
        polynomial1TF = new JTextField("          Enter polynomial here              ");
        polynomial1TF.setHorizontalAlignment(0);
        polynomial2TF = new JTextField("          Enter polynomial here              ");
        polynomial2TF.setHorizontalAlignment(0);
        polynomial3TF = new JTextField("          Enter polynomial here              ");
        polynomial3TF.setHorizontalAlignment(0);
        resultTF = new JTextField("Result");
        resultTF.setHorizontalAlignment(JTextField.CENTER);
        operationSelectCB = new JComboBox(operations);
        operationSelectPanel.add(operationSelectCB);
        computeButton = new JButton("Compute");
        panelForm1.add(polynomialLabel1, BorderLayout.CENTER);
        panelForm1.add(polynomial1TF, BorderLayout.EAST);
        JPanel polynomialsTFPanel = new JPanel(new GridLayout(2, 1));
        polynomialsTFPanel.add(polynomialLabel2);
        polynomialsTFPanel.add(polynomialLabel3);
        panelForm2.add(polynomialsTFPanel, BorderLayout.CENTER);
        JPanel polynomialsPanel = new JPanel(new GridLayout(2, 1));
        polynomialsPanel.add(polynomial2TF);
        polynomialsPanel.add(polynomial3TF);
        panelForm2.add(polynomialsPanel, BorderLayout.EAST);
        cards.add(panelForm2, forms[1]);
        cards.add(panelForm1, forms[0]);
        mainPanel.add(operationSelectPanel);
        mainPanel.add(cards);
        JPanel computeButtonPanel = new JPanel();
        computeButtonPanel.add(computeButton);
        mainPanel.add(computeButtonPanel);
        mainPanel.add(resultTF);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
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
        setSize(300,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public static void main(String[] args) {
        MainWindow lol = new MainWindow();
    }
}
