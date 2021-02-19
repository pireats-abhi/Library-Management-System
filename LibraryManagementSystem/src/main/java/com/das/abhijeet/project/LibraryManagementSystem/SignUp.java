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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.das.abhijeet.project.JDBC.Account;
import com.das.abhijeet.project.JDBC.AccountDAO;
import com.das.abhijeet.project.JDBC.DatabaseConnectionManager;
import java.awt.Toolkit;

public class SignUp extends JFrame implements ActionListener {

	private JPanel contentPane, panel;
	private JLabel l1, l2, l3, l4;
	private JTextField name;
	private JTextField answer;
	private JPasswordField password;
	private JButton backBtn, createBtn;
	private JComboBox<String> comboBox;
	private DatabaseConnectionManager dcm = new DatabaseConnectionManager();

	public SignUp() {
		super("Digital Library");
		setIconImage(Toolkit.getDefaultToolkit().getImage(SignUp.class.getResource("/com/das/abhijeet/project/LibraryManagementSystem/icons/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(450, 300);
		centerFrame();
//		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setForeground(new Color(0, 128, 128));
		panel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 128, 128), new Color(0, 128, 128)),
						"Create Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		panel.setLayout(null);

		l1 = new JLabel("Name:");
		l1.setHorizontalAlignment(SwingConstants.TRAILING);
		l1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l1.setBounds(128, 42, 72, 17);
		panel.add(l1);

		name = new JTextField();
		name.setBounds(210, 42, 148, 20);
		panel.add(name);
		name.setColumns(10);

		l2 = new JLabel("Password:");
		l2.setHorizontalAlignment(SwingConstants.TRAILING);
		l2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l2.setBounds(128, 73, 72, 17);
		panel.add(l2);

		l3 = new JLabel("Security Question:");
		l3.setHorizontalAlignment(SwingConstants.TRAILING);
		l3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l3.setBounds(52, 103, 148, 17);
		panel.add(l3);

		l4 = new JLabel("Answer:");
		l4.setHorizontalAlignment(SwingConstants.TRAILING);
		l4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l4.setBounds(128, 134, 72, 17);
		panel.add(l4);

		answer = new JTextField();
		answer.setColumns(10);
		answer.setBounds(210, 134, 148, 20);
		panel.add(answer);

		backBtn = new JButton("Back");
		backBtn.addActionListener(this);
		backBtn.setBackground(new Color(255, 255, 255));
		backBtn.setForeground(new Color(0, 128, 128));
		backBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		backBtn.setBounds(112, 165, 89, 23);
		panel.add(backBtn);

		createBtn = new JButton("Create");
		createBtn.addActionListener(this);
		createBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		createBtn.setForeground(new Color(255, 255, 255));
		createBtn.setBackground(new Color(0, 128, 128));
		createBtn.setBounds(210, 165, 89, 23);
		panel.add(createBtn);

		password = new JPasswordField();
		password.setBounds(210, 73, 148, 20);
		panel.add(password);

		comboBox = new JComboBox<String>();
		comboBox.setToolTipText("");
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "What is your pet name?",
				"Whar is your nick name?", "Who is your idol?", "Your favourite music artist?" }));
		comboBox.setBounds(210, 102, 148, 22);
		panel.add(comboBox);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == createBtn) {
			if (name.getText().length() != 0 && password.getPassword().length >= 8 && answer.getText().length() != 0) {
				Account acc = new Account();
				acc.setName(name.getText());
				acc.setPassword(String.valueOf(password.getPassword()));
				acc.setSq((String) comboBox.getSelectedItem());
				acc.setSa(answer.getText());

				try {
					Connection connection = dcm.getConnection();
					AccountDAO accountDAO = new AccountDAO(connection);
					acc = accountDAO.create(acc);
					JOptionPane.showMessageDialog(null, "Your user id is " + acc.getId());
					setVisible(false);
					new LoginUser().setVisible(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
					throw new RuntimeException(e1);
				}
			} else {
				JOptionPane.showMessageDialog(null, "Invalid insert...");
			}
		}

		if (e.getSource() == backBtn) {
			setVisible(false);
			new LoginUser().setVisible(true);
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
