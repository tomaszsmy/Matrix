package Projekt;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Menu extends JDialog {

    private final JPanel contentPanel = new JPanel();
    private MainWindow window = new MainWindow();
    private JLabel lblMatrixA;
    private JLabel lblKolumnyA;
    private JLabel lblWierszeA;
    private JSpinner ColumnsA;
    private JSpinner RowsA;
    private JLabel lblMatrixB;
    private JLabel lblKolumnyB;
    private JLabel lblWiersze;
    private JSpinner ColumnsB;
    private JSpinner RowsB;
    private JButton btnOk;
    private String action = " ";
    private JButton btnSubtraction;
    private JButton btnEquations;
    private Equations equations = new Equations();


    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {

            Menu dialog = new Menu(); 
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
            dialog.setVisible(true); 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    private void macierzAvisible(boolean x)
    {
        lblMatrixA.setVisible(x);
        lblKolumnyA.setVisible(x);
        lblWierszeA.setVisible(x);
        ColumnsA.setVisible(x);
        RowsA.setVisible(x);
        btnOk.setVisible(x);
    }

    private void macierzBvisible(boolean x) 
    {
        lblMatrixB.setVisible(x);
        lblKolumnyB.setVisible(x);
        lblWiersze.setVisible(x);
        ColumnsB.setVisible(x);
        RowsB.setVisible(x);

    }

    private void resetSpinner() 
    {
        ColumnsA.setValue(1);
        RowsA.setValue(1);
        ColumnsB.setValue(1);
        RowsB.setValue(1);
    }

    public Menu() {
        setTitle("Macierz");

        setBounds(100, 100, 473, 323);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        contentPanel.setLayout(null);
        {
            JButton btnMultiplication = new JButton("Mnozenie");
            btnMultiplication.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    resetSpinner();
                    action = "mnozenie";
                    macierzAvisible(true);
                    macierzBvisible(true);
                    RowsB.setEnabled(false);
                    window.action = "mnozenie";

                }
            });
            btnMultiplication.setBounds(10, 11, 102, 23);
            contentPanel.add(btnMultiplication);
        }
        {
            JButton btnTransposition = new JButton("Transponowanie");
            btnTransposition.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    resetSpinner();
                    action = "transpozycja";
                    macierzAvisible(true);
                    macierzBvisible(false);
                    window.action = "transpozycja";

                }
            });
            btnTransposition.setBounds(118, 43, 168, 23);
            contentPanel.add(btnTransposition);
        }
        {
            lblMatrixA = new JLabel("Macierz A");
            lblMatrixA.setVisible(false);
            lblMatrixA.setBounds(54, 97, 68, 14);
            contentPanel.add(lblMatrixA);
        }
        {
            lblKolumnyA = new JLabel("Kolumny");
            lblKolumnyA.setVisible(false);
            lblKolumnyA.setBounds(33, 134, 67, 14);
            contentPanel.add(lblKolumnyA);
        }
        {
            lblWierszeA = new JLabel("Wiersze");
            lblWierszeA.setVisible(false);
            lblWierszeA.setDoubleBuffered(true);
            lblWierszeA.setBounds(33, 172, 67, 14);
            contentPanel.add(lblWierszeA);
        }
        {
            ColumnsA = new JSpinner();
            ColumnsA.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    if (action.equals("dodawanie")) {
                        ColumnsB.setValue(ColumnsA.getValue());

                    } else if (action.equals("mnozenie")) {
                        RowsB.setValue(ColumnsA.getValue());
                    } else if (action.equals("wyznacznik")) {
                        RowsA.setValue(ColumnsA.getValue());
                    }

                }
            });
            ColumnsA.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentMoved(ComponentEvent arg0) {
                }
            });

            ColumnsA.addAncestorListener(new AncestorListener() {
                public void ancestorAdded(AncestorEvent arg0) {
                    if (action.equals("dodawanie")) {
                        ColumnsB.setValue(ColumnsA.getValue());
                    }

                }

                public void ancestorMoved(AncestorEvent arg0) {
                }

                public void ancestorRemoved(AncestorEvent arg0) {
                }
            });
            ColumnsA.setModel(new SpinnerNumberModel(1, 1, 10, 1));
            ColumnsA.setVisible(false);
            ColumnsA.setBounds(100, 131, 42, 20);
            contentPanel.add(ColumnsA);
        }
        {
            RowsA = new JSpinner();
            RowsA.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    if (action.equals("dodawanie")) {
                        RowsB.setValue(RowsA.getValue());
                    } else if (action.equals("wyznacznik")) {
                        ColumnsA.setValue(RowsA.getValue());
                    }

                }
            });
            RowsA.setModel(new SpinnerNumberModel(1, 1, 10, 1));
            RowsA.setVisible(false);
            RowsA.setBounds(100, 169, 42, 20);
            contentPanel.add(RowsA);
        }
        {
            lblMatrixB = new JLabel("Macierz B");
            lblMatrixB.setVisible(false);
            lblMatrixB.setBounds(205, 97, 57, 14);
            contentPanel.add(lblMatrixB);
        }
        {
            lblKolumnyB = new JLabel("Kolumny");
            lblKolumnyB.setVisible(false);
            lblKolumnyB.setDoubleBuffered(true);
            lblKolumnyB.setBounds(176, 134, 58, 14);
            contentPanel.add(lblKolumnyB);
        }
        {
            lblWiersze = new JLabel("Wiersze");
            lblWiersze.setVisible(false);
            lblWiersze.setBounds(176, 172, 58, 14);
            contentPanel.add(lblWiersze);
        }
        {
            ColumnsB = new JSpinner();
            ColumnsB.setModel(new SpinnerNumberModel(1, 1, 10, 1));
            ColumnsB.setVisible(false);
            ColumnsB.setBounds(244, 131, 42, 20);
            contentPanel.add(ColumnsB);
        }
        {
            RowsB = new JSpinner();
            RowsB.setModel(new SpinnerNumberModel(1, 1, 10, 1));
            RowsB.setVisible(false);
            RowsB.setBounds(244, 169, 42, 20);

            contentPanel.add(RowsB);
        }
        {
            btnOk = new JButton("ok");
            btnOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    window.scoreTable.setVisible(false);

                    if (window.action.equals("transpozycja") || window.action.equals("wyznacznik")) {
                        window.setmacierzA((Integer) ColumnsA.getValue(), (Integer) RowsA.getValue());
                        window.setmacierzB(1, 1);
                        window.setvisiblemacierzB(false);
                        window.frame.setVisible(true);
                    } else {

                        window.setmacierzA((Integer) ColumnsA.getValue(), (Integer) RowsA.getValue());
                        window.setmacierzB((Integer) ColumnsB.getValue(), (Integer) RowsB.getValue());
                        window.setvisiblemacierzB(true);
                        window.frame.setVisible(true);
                    }

                }
            });
            btnOk.setVisible(false);
            btnOk.setBounds(125, 205, 89, 23);
            contentPanel.add(btnOk);
        }

        JButton btnAdding = new JButton("Dodawanie");
        btnAdding.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                resetSpinner();
                action = "dodawanie";
                macierzAvisible(true);
                macierzBvisible(true);
                window.action = "dodawanie";

            }
        });
        btnAdding.setBounds(10, 43, 102, 23);
        contentPanel.add(btnAdding);
        {
            btnSubtraction = new JButton("Odejmowanie");
            btnSubtraction.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    resetSpinner();
                    action = "dodawanie";
                    macierzAvisible(true);
                    macierzBvisible(true);
                    window.action = "odejmowanie";

                }
            });
            btnSubtraction.setBounds(293, 43, 138, 23);
            contentPanel.add(btnSubtraction);
        }

        JButton btnDet = new JButton("Wyznacznik");
        btnDet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                resetSpinner();
                action = "wyznacznik";
                macierzAvisible(true);
                macierzBvisible(false);
                window.action = "wyznacznik";
            }
        });
        btnDet.setBounds(293, 11, 138, 23);
        contentPanel.add(btnDet);

        btnEquations = new JButton("Uklad rownan");
        btnEquations.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                resetSpinner();
                equations.frame.setVisible(true);

            }
        });
        btnEquations.setBounds(295, 239, 138, 23);
        contentPanel.add(btnEquations);

        JButton btnMultiplicationByValue = new JButton("Mnozenie przez liczbe");
        btnMultiplicationByValue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                resetSpinner();
                action = "Mprzezliczbe";
                macierzAvisible(true);
                macierzBvisible(false);
                window.action = "Mprzezliczbe";
            }
        });
        btnMultiplicationByValue.setBounds(118, 11, 168, 23);
        contentPanel.add(btnMultiplicationByValue);

    }
}
