package Projekt;

import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Equations {

	public JFrame frame;
	public JTextField equation[][];
	public JSpinner spinner;
	public JLabel lblUnknow;
	public JLabel niewiadome[][];
	public JLabel wynik;
	public JButton btnOk;
	public JButton btnCalculate;
	public JButton btnReset;
	public JButton btnRand;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the application.
	 */
	public Equations() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void SetButton(boolean x) {

		btnCalculate.setVisible(x);
		btnReset.setVisible(x);
		btnRand.setVisible(x);

	}

	private void initialize() { 

		int xBound = 550;
		int yBound = 350;
		frame = new JFrame();
		frame.setTitle("Uklad");
		frame.setBounds(100, 100, xBound, yBound);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		wynik = new JLabel("Wynik");
		frame.getContentPane().add(wynik);

		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(2, 2, 10, 1));
		spinner.setBounds(180, 89, 51, 20);
		frame.getContentPane().add(spinner);

		lblUnknow = new JLabel("Liczba niewiadomych");
		lblUnknow.setVisible(true);
		lblUnknow.setBounds(145, 70, 150, 14);
		frame.getContentPane().add(lblUnknow);
		
		btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnOk.setVisible(false);
				SetButton(true);
				spinner.setEnabled(false);
				equation = new JTextField[(int) spinner.getValue()][((int) (int) spinner.getValue() + 1)];
				niewiadome = new JLabel[11][11];
				int x = 130, y = 140, xB = xBound, yB = yBound;
				String niewiadome1[] = { "x1", "x2", "x3", "x4", "x5", "x6", "x7", "x8", "x9", "x10" };

				for (int i = 0; i < (int) spinner.getValue(); i++) {

					x = 130;
					for (int j = 0; j < (int) (1 + (int) spinner.getValue()); j++) {
						niewiadome[i][j] = new JLabel("");

						equation[i][j] = new JTextField("");
						equation[i][j].setBounds(x, y, 30, 40);
						niewiadome[i][j].setBounds(x - 15, y, 30, 40);

						if (j == (int) spinner.getValue()) {
							niewiadome[i][j].setText("=");
						} else {
							niewiadome[i][j].setText(niewiadome1[j]);
						}

						frame.getContentPane().add(niewiadome[i][j]);
						niewiadome[i][j].setVisible(true);
						if (j == (int) spinner.getValue()) {
							equation[i][j].setBackground(Color.yellow);
						}

						frame.getContentPane().add(equation[i][j]);
						x += 50;
					}
					y += 50;
					if ((int) spinner.getValue() > 2) {
						yB += 40;
						xB += 25;
					}
					frame.setBounds(100, 100, xB, yB);
				}
				wynik.setBounds(20, y - 30, 600, 60);
				
			}
		});
		btnOk.setBounds(110, 111, 90, 25);
		frame.getContentPane().add(btnOk);

		btnCalculate = new JButton("Licz"); 
		btnCalculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				Matrix equationMatrix = new Matrix(((int) (int) spinner.getValue() + 1), ((int) (int) spinner.getValue() + 1));
				boolean check = true;

				try {
					for (int i = 0; i < (int) spinner.getValue(); i++) {
						for (int j = 0; j < (int) (1 + (int) spinner.getValue()); j++) {
							equationMatrix.matrix[i][j] = Integer.parseInt(equation[i][j].getText());

						}

					}
					check = true;
				} catch (NumberFormatException a) {

					JOptionPane.showMessageDialog(frame, "Złe dane wejściowe");
					check = false;
				} finally {
					if (check)
						wynik.setText(equationMatrix.equations());

				}
			}

		});
		btnCalculate.setBounds(210, 111, 90, 25);
		frame.getContentPane().add(btnCalculate);

		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {

			@Override

			public void actionPerformed(ActionEvent arg0) {
				btnOk.setVisible(true);
				int xBound = 550;
				int yBound = 350;
				frame.setTitle("Uklad");
				frame.setBounds(100, 100, xBound, yBound);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.getContentPane().setLayout(null);

				wynik.setText("Wynik");
				

				lblUnknow.setText("Liczba niewiadomych");
				lblUnknow.setVisible(true);
				lblUnknow.setBounds(145, 70, 150, 14);

				for (int i = 0; i < (int) spinner.getValue(); i++) {

					for (int j = 0; j < (int) (1 + (int) spinner.getValue()); j++) {

						equation[i][j].setText("");
						niewiadome[i][j].setVisible(false);
						equation[i][j].setVisible(false);

					}

				}
				spinner.setModel(new SpinnerNumberModel(2, 2, 10, 1));
				spinner.setBounds(180, 89, 51, 20);
				SetButton(false);
				spinner.setEnabled(true);
			}
		});
		btnReset.setBounds(410, 111, 90, 25);
		frame.getContentPane().add(btnReset);

		btnRand = new JButton("Losowo");
		btnRand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random generator = new Random();

				for (int i = 0; i < (int) spinner.getValue(); i++) {
					for (int j = 0; j < (int) (1 + (int) spinner.getValue()); j++) {
						equation[i][j].setText(String.valueOf(generator.nextInt(9)));
					}
				}

			}
		});
		btnRand.setBounds(310, 111, 90, 25);
		frame.getContentPane().add(btnRand);
		SetButton(false);
		
	}

}
