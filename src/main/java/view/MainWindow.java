package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private final static  String[] operations = {"Add", "Subtract", "Differentiate", "Integrate", "Multiply", "Divide"};
    private final static String[] forms = {"Form 1", "Form 2"};

    private JTextField polynomial1TF;
    private JTextField polynomial2TF;
    private JTextField polynomial3TF;
    private JTextField resultTF;
    private JPanel mainPanel;
    private JPanel panelForm1;
    private JPanel panelForm2;
    private JPanel cards;
    private boolean form1;
    private JPanel operationSelectPanel;
    private JComboBox operationSelectCB;
    private JButton computeButton;

    public MainWindow() {
        super("Polynomial Calculator");
        form1 = false;
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
        operationSelectCB = new JComboBox(Operation.values());
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
           Operation item = (Operation)evt.getItem();
           if(item.equals(Operation.Differentiate) || item.equals(Operation.Integrate)) {
               cardLayout.show(cards, forms[0]);
               form1 = true;
           } else {
               cardLayout.show(cards, forms[1]);
               form1 = false;
           }
        });
        setContentPane(mainPanel);
        setSize(300,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
    public String getPolynomial() {
        return polynomial1TF.getText();
    }
    public String getPolynomial1() {
        return polynomial2TF.getText();
    }
    public String getPolynomial2() {
        return polynomial3TF.getText();
    }
    public void addComputeBtnListener(ActionListener listener) {
        computeButton.addActionListener(listener);
    }
    public Operation getOperation(){
        return (Operation)operationSelectCB.getSelectedItem();
    }
}
