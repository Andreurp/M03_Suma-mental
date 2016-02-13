package net.andreu.mental;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class App {

	private static final int MAX_NUM = 10;
	private int num1, num2, resultat;
	private String[] operador = { "+", "-", "x" };
	private String signe;
	private int cont = 0;
	private int seg = 0;

	private Random rm = new Random();

	private JFrame frame;
	private JLabel operacio = new JLabel("", SwingConstants.CENTER);
	private JLabel temps = new JLabel("", SwingConstants.CENTER);
	private JLabel correcio = new JLabel("", SwingConstants.CENTER);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		num1 = rm.nextInt(MAX_NUM);
		num2 = rm.nextInt(MAX_NUM);
		signe = operador[rm.nextInt(operador.length)];
		String.valueOf(num1);
		String.valueOf(num2);
		operacio.setText(num1 + signe + num2);

		final Timer time = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seg = seg + 1;
				String segons = String.valueOf(seg);
				temps.setText(segons);
			}
		});

		time.start();

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		frame.getContentPane().setLayout(gridBagLayout);

		JLabel titol = new JLabel("Quan dóna...", SwingConstants.CENTER);
		titol.setFont(new Font("Serif", Font.PLAIN, 40));
		GridBagConstraints mida = new GridBagConstraints();
		mida.fill = GridBagConstraints.BOTH;
		mida.weightx = 1;
		mida.weighty = 1;
		mida.gridwidth = 3;
		mida.gridheight = 1;
		mida.gridx = 1;
		mida.gridy = 1;
		frame.getContentPane().add(titol, mida);

		operacio.setFont(new Font("Serif", Font.PLAIN, 20));
		mida.fill = GridBagConstraints.BOTH;
		mida.gridwidth = 3;
		mida.gridheight = 1;
		mida.gridx = 1;
		mida.gridy = 2;
		frame.getContentPane().add(operacio, mida);

		correcio.setFont(new Font("Serif", Font.PLAIN, 20));
		mida.fill = GridBagConstraints.BOTH;
		mida.gridwidth = 3;
		mida.gridheight = 1;
		mida.gridx = 1;
		mida.gridy = 3;
		frame.getContentPane().add(correcio, mida);

		final JTextField resposta = new JTextField();
		resposta.setHorizontalAlignment(JTextField.CENTER);
		mida.fill = GridBagConstraints.BOTH;
		mida.gridwidth = 1;
		mida.gridheight = 1;
		mida.gridx = 1;
		mida.gridy = 4;
		frame.getContentPane().add(resposta, mida);

		JButton fet = new JButton("Fet");
		mida.fill = GridBagConstraints.BOTH;
		mida.gridwidth = 1;
		mida.gridheight = 1;
		mida.gridx = 1;
		mida.gridy = 5;
		frame.getContentPane().add(fet, mida);
		fet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				switch (signe) {
				case "+":
					resultat = num1 + num2;
					break;
				case "-":
					resultat = num1 - num2;
					break;
				case "x":
					resultat = num1 * num2;
					break;
				}

				if (resposta.getText().equals(String.valueOf(resultat))) {
					cont++;
					num1 = rm.nextInt(MAX_NUM);
					num2 = rm.nextInt(MAX_NUM);
					signe = operador[rm.nextInt(operador.length)];
					String.valueOf(num1);
					String.valueOf(num2);
					operacio.setText(num1 + signe + num2);
					correcio.setForeground(Color.green);
					correcio.setText("Correcte!!!");
					if (cont == 3) {
						time.stop();
						JOptionPane.showMessageDialog(frame, "Has trigat " + String.valueOf(seg) + " segons. ","Felicitats!", JOptionPane.PLAIN_MESSAGE);
						System.exit(0);
					}
				} else {
					correcio.setForeground(Color.red);
					correcio.setText("Error: sulució incorrecta!");
				}
			}
		});

		temps.setFont(new Font("Serif", Font.PLAIN, 20));
		mida.fill = GridBagConstraints.BOTH;
		mida.gridwidth = 3;
		mida.gridheight = 1;
		mida.gridx = 1;
		mida.gridy = 6;
		frame.getContentPane().add(temps, mida);
	}
}