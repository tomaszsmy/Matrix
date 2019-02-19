package Projekt;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Random;
import java.awt.event.ActionEvent;

public class MainWindow {

    public JFrame frame;
    private JLabel label;
    private JTable MatrixAtable;
    private JLabel lblMatrixA;
    private JTable MatrixBtable;
    private JLabel lblMatrixB;
    private DefaultTableModel modelB;
    private DefaultTableModel modelA;
    private DefaultTableModel modelScore;
    private int rowsA;
    private int columnsA;
    private int rowsB;
    private int columnsB;
    private Matrix A, B, Wynik;
    public String action = "";
    public JTable scoreTable;
    JButton btnRandB;

    public MainWindow() {
        initialize();

    }

    public void setmacierzA(int kolumny, int wiersze) {

        columnsA = kolumny;
        rowsA = wiersze;
        A = new Matrix(kolumny, wiersze);
        String columns[];
        columns = new String[kolumny];
        for (int i = 0; i < columns.length; i++) {
            columns[i] = null;
        }

        modelA = new DefaultTableModel(columns, wiersze);
        MatrixAtable.setModel(modelA);

    }

    public void setmacierzB(int kolumny, int wiersze) { 

        columnsB = kolumny;
        rowsB = wiersze;
        String columns[];
        columns = new String[kolumny];
        for (int i = 0; i < columns.length; i++) {
            columns[i] = null;
        }
        modelB = new DefaultTableModel(columns, wiersze);
        MatrixBtable.setModel(modelB);
        B = new Matrix(kolumny, wiersze);

    }

    public void setvisiblemacierzB(boolean x) { 
        MatrixBtable.setVisible(x);
        modelB.setValueAt("0", 0, 0);
        lblMatrixB.setVisible(x);
        btnRandB.setVisible(x);

    }

    private void initialize() {

        frame = new JFrame();
        frame.setTitle("Macierz");
        frame.setBounds(100, 100, 522, 496);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        label = new JLabel("");
        label.setBounds(217, 137, 46, 14);
        frame.getContentPane().add(label);

        MatrixAtable = new JTable();
        MatrixAtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        MatrixAtable.setBounds(35, 29, 175, 160);
        MatrixAtable.setBackground(null);
        frame.getContentPane().add(MatrixAtable);

        lblMatrixA = new JLabel("Macierz A");
        lblMatrixA.setBounds(82, 11, 82, 14);
        frame.getContentPane().add(lblMatrixA);

        MatrixBtable = new JTable();
        MatrixBtable.setBounds(278, 29, 175, 160);
        MatrixBtable.setBackground(null);
        frame.getContentPane().add(MatrixBtable);

        lblMatrixB = new JLabel("Macierz B");
        lblMatrixB.setBounds(328, 11, 76, 14);
        frame.getContentPane().add(lblMatrixB);

        JButton btnCalculate = new JButton("Licz");
        btnCalculate.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {

                try {
                    for (int i = 0; i < columnsA; i++) { 
                        for (int j = 0; j < rowsA; j++) {
                            A.setvalue(i, j,  Double.parseDouble((String) modelA.getValueAt(j, i)));

                        }

                    }

                    for (int i = 0; i < columnsB; i++) { 
                        for (int j = 0; j < rowsB; j++) {

                            B.setvalue(i, j,  Double.parseDouble((String) modelB.getValueAt(j, i)));
                        }
                       
                    }
                } catch (NumberFormatException a) {
                    JOptionPane.showMessageDialog(frame, "Złe dane wejściowe");

                }
                if (action.equals("dodawanie")) { 
                    Wynik = A.adding(B);
                } else if (action.equals("odejmowanie")) {
                    Wynik = A.subtraction(B);
                } else if (action.equals("mnozenie")) {
                    Wynik = A.multiplication(B);

                } else if (action.equals("transpozycja")) {
                    Wynik = A.transposition();

                } else if (action.equals("wyznacznik")) {

                    Wynik = new Matrix(1, 1);
                    Wynik.matrix[0][0] = A.det(A.matrix, A.columns);
                } else {
                    Wynik = A.multiplicationByValue(B);
                }

                String columns[];
                columns = new String[Wynik.rows];
                for (int i = 0; i < columns.length; i++) {
                    columns[i] = null;
                }

                modelScore = new DefaultTableModel(columns, Wynik.columns);
                scoreTable.setModel(modelScore);
                DecimalFormat format = new DecimalFormat("0.#");
                for (int i = 0; i < Wynik.rows; i++) { 
                    for (int j = 0; j < Wynik.columns; j++) {
                        modelScore.setValueAt(format.format(Wynik.getvalue(i, j) ), j, i);

                    }

                }

                scoreTable.setVisible(true);

            }

        });

        btnCalculate.setBounds(199, 201, 89, 23);
        frame.getContentPane().add(btnCalculate);

        scoreTable = new JTable();
        scoreTable.setBounds(152, 263, 175, 160);
        scoreTable.setBackground(null);
        frame.getContentPane().add(scoreTable);

        JButton btnRandA = new JButton("Wypelnij losowo");
        btnRandA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random generator = new Random();

                for (int i = 0; i < columnsA; i++) {
                    for (int j = 0; j < rowsA; j++) {
                        modelA.setValueAt(Integer.toString(generator.nextInt(99)), j, i);
                    }
                }

            }
        });
        btnRandA.setBounds(35, 200, 126, 23);
        frame.getContentPane().add(btnRandA);

        btnRandB = new JButton("Wypelnij losowo");
        btnRandB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Random generator = new Random();

                for (int i = 0; i < columnsB; i++) {
                    for (int j = 0; j < rowsB; j++) {
                        modelB.setValueAt(Integer.toString(generator.nextInt(99)), j, i);
                    }
                }
            }
        });
        btnRandB.setBounds(327, 201, 126, 23);
        frame.getContentPane().add(btnRandB);

    }
}
