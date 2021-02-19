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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.das.abhijeet.project.JDBC.Account;
import com.das.abhijeet.project.JDBC.AccountDAO;
import com.das.abhijeet.project.JDBC.DatabaseConnectionManager;
import java.awt.Toolkit;

public class Forget extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField tf1, tf2, tf3, tf4, tf5;
	private JButton searchBtn, retriveBtn, doneBtn;
	private DatabaseConnectionManager dcm = new DatabaseConnectionManager();
	Account account;

	public Forget() {
		super("Digital Library");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Forget.class.getResource("/com/das/abhijeet/project/LibraryManagementSystem/icons/logo.png")));
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

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setForeground(new Color(0, 128, 128));
		panel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 128, 128), new Color(0, 128, 128)),
						"Forget Password", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel l1 = new JLabel("User ID:");
		l1.setHorizontalAlignment(SwingConstants.TRAILING);
		l1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l1.setBounds(85, 26, 80, 21);
		panel.add(l1);

		tf1 = new JTextField();
		tf1.setBounds(177, 28, 101, 20);
		panel.add(tf1);
		tf1.setColumns(10);

		searchBtn = new JButton("Search");
		searchBtn.addActionListener(this);
		searchBtn.setForeground(new Color(255, 255, 255));
		searchBtn.setBackground(new Color(0, 128, 128));
		searchBtn.setBounds(288, 27, 89, 23);
		panel.add(searchBtn);

		JLabel l2 = new JLabel("Name:");
		l2.setHorizontalAlignment(SwingConstants.TRAILING);
		l2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l2.setBounds(85, 57, 80, 21);
		panel.add(l2);

		tf2 = new JTextField();
		tf2.setEditable(false);
		tf2.setForeground(new Color(0, 0, 0));
		tf2.setColumns(10);
		tf2.setBounds(177, 59, 200, 20);
		panel.add(tf2);

		JLabel l3 = new JLabel("Security Question:");
		l3.setHorizontalAlignment(SwingConstants.TRAILING);
		l3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l3.setBounds(52, 88, 113, 21);
		panel.add(l3);

		tf3 = new JTextField();
		tf3.setEditable(false);
		tf3.setColumns(10);
		tf3.setBounds(177, 90, 200, 20);
		panel.add(tf3);

		JLabel l4 = new JLabel("Answer:");
		l4.setHorizontalAlignment(SwingConstants.TRAILING);
		l4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l4.setBounds(85, 119, 80, 21);
		panel.add(l4);

		tf4 = new JTextField();
		tf4.setEnabled(false);
		tf4.setColumns(10);
		tf4.setBounds(177, 121, 101, 20);
		panel.add(tf4);

		retriveBtn = new JButton("Retrive");
		retriveBtn.addActionListener(this);
		retriveBtn.setEnabled(false);
		retriveBtn.setForeground(new Color(255, 255, 255));
		retriveBtn.setBackground(new Color(0, 128, 128));
		retriveBtn.setBounds(288, 120, 89, 23);
		panel.add(retriveBtn);

		JLabel l5 = new JLabel("Password:");
		l5.setHorizontalAlignment(SwingConstants.TRAILING);
		l5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l5.setBounds(85, 151, 80, 21);
		panel.add(l5);

		tf5 = new JTextField();
		tf5.setEditable(false);
		tf5.setColumns(10);
		tf5.setBounds(177, 152, 200, 20);
		panel.add(tf5);

		doneBtn = new JButton("I Got My Password");
		doneBtn.addActionListener(this);
		doneBtn.setForeground(new Color(255, 255, 255));
		doneBtn.setBackground(new Color(0, 128, 128));
		doneBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		doneBtn.setBounds(30, 183, 347, 34);
		panel.add(doneBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchBtn) {
			if (tf1.getText().length() != 0) {
				long id;
				try {
					id = Long.parseLong(tf1.getText().trim());
				} catch (NumberFormatException e1) {
					id = 0;
				}
				try {
					Connection connection = dcm.getConnection();
					AccountDAO accountDAO = new AccountDAO(connection);
					account = accountDAO.findById(id);
					if (account.getId() != 0) {
						tf2.setText(account.getName());
						tf3.setText(account.getSq());
						tf4.setEnabled(true);
						retriveBtn.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "Input corrent user ID...");
					}

				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(null, "Input corrent user ID...");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Input corrent user ID...");
			}
		}

		if (e.getSource() == retriveBtn) {
			if (tf4.getText().length() != 0) {
				if (account.getSa().equals(tf4.getText())) {
					tf5.setText(account.getPassword());
				} else {
					JOptionPane.showMessageDialog(null, "Your answer is INCORRECT...");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Your answer is INCORRECT...");
			}
		}

		if (e.getSource() == doneBtn) {
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
