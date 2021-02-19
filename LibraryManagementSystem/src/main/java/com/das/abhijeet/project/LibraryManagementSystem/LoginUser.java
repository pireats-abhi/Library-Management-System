package com.das.abhijeet.project.LibraryManagementSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.das.abhijeet.project.JDBC.Account;
import com.das.abhijeet.project.JDBC.AccountDAO;
import com.das.abhijeet.project.JDBC.DatabaseConnectionManager;
import java.awt.Toolkit;

public class LoginUser extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton b1, b2, b3;
	private DatabaseConnectionManager dcm = new DatabaseConnectionManager();

	public LoginUser() {
		super("Digital Library");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginUser.class.getResource("/com/das/abhijeet/project/LibraryManagementSystem/icons/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(450, 300);
		centerFrame();
//		setBounds(350, 250, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel l1 = new JLabel("ID:");
		l1.setForeground(new Color(255, 255, 255));
		l1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l1.setBounds(86, 57, 83, 22);
		contentPane.add(l1);

		textField = new JTextField();
		textField.setBounds(179, 60, 171, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel l2 = new JLabel("Password:");
		l2.setForeground(new Color(255, 255, 255));
		l2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l2.setBounds(86, 90, 83, 22);
		contentPane.add(l2);

		passwordField = new JPasswordField();
		passwordField.setBounds(179, 93, 171, 20);
		contentPane.add(passwordField);

		b1 = new JButton("Login");
		b1.setBackground(new Color(255, 255, 255));
		b1.addActionListener(this);
		b1.setForeground(new Color(0, 100, 0));
		b1.setFont(new Font("Tahoma", Font.BOLD, 14));
		b1.setBounds(125, 133, 89, 23);
		contentPane.add(b1);

		b2 = new JButton("Sign Up");
		b2.setBackground(new Color(255, 255, 255));
		b2.addActionListener(this);
		b2.setForeground(new Color(210, 105, 30));
		b2.setFont(new Font("Tahoma", Font.BOLD, 14));
		b2.setBounds(224, 133, 89, 23);
		contentPane.add(b2);

		JLabel l3 = new JLabel("Trouble in Login?");
		l3.setBackground(new Color(255, 255, 255));
		l3.setForeground(new Color(255, 255, 255));
		l3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		l3.setBounds(61, 179, 136, 20);
		contentPane.add(l3);

		b3 = new JButton("Forget Password");
		b3.setBackground(new Color(255, 255, 255));
		b3.addActionListener(this);
		b3.setForeground(new Color(165, 42, 42));
		b3.setFont(new Font("Tahoma", Font.BOLD, 14));
		b3.setBounds(193, 178, 157, 23);
		contentPane.add(b3);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			if (textField.getText().length() != 0 && passwordField.getPassword().length != 0) {
				long id;
				try {
					id = Long.parseLong(textField.getText().trim());
				} catch (NumberFormatException e1) {
					id = 0;
				}
				char[] ps = passwordField.getPassword();
				String password = String.valueOf(ps);

				try {
					Connection connection = dcm.getConnection();
					AccountDAO accountDAO = new AccountDAO(connection);
					Account account = accountDAO.findById(id);
					if (account.getPassword() != null && account.getPassword().equals(password)) {
						this.setVisible(false);
						new Loading().setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Invalid login...");
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "Invalid Username and Password...");
			}
		}

		if (e.getSource() == b2) {
			this.setVisible(false);
			new SignUp().setVisible(true);
		}

		if (e.getSource() == b3) {
			this.setVisible(false);
			new Forget().setVisible(true);
		}

	}

	private void centerFrame() {

		Dimension windowSize = getSize();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Point centerPoint = ge.getCenterPoint();

		int dx = centerPoint.x - windowSize.width / 2;
		int dy = centerPoint.y - windowSize.height / 2;
		setLocation(dx, dy);
	}
}
