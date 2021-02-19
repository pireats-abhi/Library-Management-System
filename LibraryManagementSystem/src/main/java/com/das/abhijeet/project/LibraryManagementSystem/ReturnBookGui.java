package com.das.abhijeet.project.LibraryManagementSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;

import javax.swing.ImageIcon;
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

import com.das.abhijeet.project.JDBC.DatabaseConnectionManager;
import com.das.abhijeet.project.JDBC.IssueBook;
import com.das.abhijeet.project.JDBC.IssueBookDAO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class ReturnBookGui extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JDateChooser t4;
	private JButton searchBtn, returnBookBtn, refreshBtn;

	private IssueBook issueBook;
	private JButton backBtn;

	public ReturnBookGui() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				ReturnBookGui.class.getResource("/com/das/abhijeet/project/LibraryManagementSystem/icons/logo.png")));
		setTitle("Digital Library");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		panel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 128), new Color(0, 128, 128)),
						"Return Book", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel l1 = new JLabel("Book ID:");
		l1.setHorizontalAlignment(SwingConstants.TRAILING);
		l1.setForeground(new Color(0, 128, 128));
		l1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l1.setBounds(10, 23, 163, 23);
		panel.add(l1);

		JLabel l2 = new JLabel("Student ID:");
		l2.setHorizontalAlignment(SwingConstants.TRAILING);
		l2.setForeground(new Color(0, 128, 128));
		l2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l2.setBounds(10, 57, 163, 23);
		panel.add(l2);

		searchBtn = new JButton("Search");
		searchBtn.setForeground(new Color(255, 255, 255));
		searchBtn.setBackground(new Color(0, 128, 128));
		searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		searchBtn.setBounds(10, 91, 394, 23);
		panel.add(searchBtn);
		searchBtn.addActionListener(this);

		JLabel l3 = new JLabel("Issue Date:");
		l3.setHorizontalAlignment(SwingConstants.TRAILING);
		l3.setForeground(new Color(0, 128, 128));
		l3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l3.setBounds(10, 125, 163, 23);
		panel.add(l3);

		JLabel l4 = new JLabel("Return Date:");
		l4.setHorizontalAlignment(SwingConstants.TRAILING);
		l4.setForeground(new Color(0, 128, 128));
		l4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l4.setBounds(10, 159, 163, 23);
		panel.add(l4);

		returnBookBtn = new JButton("Return Book");
		returnBookBtn.setEnabled(false);
		returnBookBtn.setForeground(new Color(255, 255, 255));
		returnBookBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		returnBookBtn.setBackground(new Color(0, 128, 128));
		returnBookBtn.setBounds(113, 193, 188, 23);
		panel.add(returnBookBtn);
		returnBookBtn.addActionListener(this);

		t1 = new JTextField();
		t1.setBounds(183, 26, 221, 20);
		panel.add(t1);
		t1.setColumns(10);

		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(183, 60, 221, 20);
		panel.add(t2);

		t3 = new JTextField();
		t3.setEditable(false);
		t3.setColumns(10);
		t3.setBounds(183, 128, 221, 20);
		panel.add(t3);

		t4 = new JDateChooser();
		t4.getCalendarButton()
				.setIcon(setImage("/com/das/abhijeet/project/LibraryManagementSystem/icons/calendar.png"));
		t4.getCalendarButton().setBackground(new Color(255, 255, 255));
		t4.getCalendarButton().setBorder(null);
		t4.setBounds(183, 162, 221, 20);
		t4.setDateFormatString("dd-MM-yyyy");
		JTextFieldDateEditor editor = (JTextFieldDateEditor) t4.getDateEditor();
		editor.setEditable(false);
		panel.add(t4);

		refreshBtn = new JButton("Refresh");
		refreshBtn.setForeground(Color.WHITE);
		refreshBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		refreshBtn.setBackground(new Color(0, 128, 128));
		refreshBtn.setBounds(311, 193, 93, 23);
		panel.add(refreshBtn);
		
		backBtn = new JButton("Back");
		backBtn.setForeground(Color.WHITE);
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		backBtn.setBackground(new Color(0, 128, 128));
		backBtn.setBounds(10, 193, 93, 23);
		panel.add(backBtn);
		refreshBtn.addActionListener(this);
		backBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == searchBtn) {
			if (!t1.getText().equals("") && !t2.getText().equals("")) {
				try {
					long bookId = Long.parseLong(t1.getText());
					long studentId = Long.parseLong(t2.getText());
					Connection connection = getConnection();
					IssueBookDAO issueBookDAO = new IssueBookDAO(connection);
					issueBook = issueBookDAO.findBook(bookId, studentId);
					if (issueBook.getId() != 0) {
						t1.setEditable(false);
						t2.setEditable(false);
						searchBtn.setEnabled(false);
						t3.setText(issueBook.getIssueDate().toString());
						returnBookBtn.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "There is no book issued.");
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something wrong.");
				}
			}
		}

		if (e.getSource() == refreshBtn) {
			t1.setText("");
			t1.setEditable(true);
			t2.setText("");
			t2.setEditable(true);
			searchBtn.setEnabled(true);
			t3.setText("");
			t4.getDateEditor().setDate(null);
			returnBookBtn.setEnabled(false);
		}
		
		if (e.getSource() == returnBookBtn) {
			Date date = t4.getDate();
			if(date != null) {
				try {
					java.sql.Date d = new java.sql.Date (date.getTime());
					Connection connection = getConnection();
					IssueBookDAO issueBookDAO = new IssueBookDAO(connection);
					issueBook.setReturnDate(d);
					issueBook.setReturn(true);
					issueBookDAO.update(issueBook);
					t1.setText("");
					t1.setEditable(true);
					t2.setText("");
					t2.setEditable(true);
					searchBtn.setEnabled(true);
					t3.setText("");
					t4.getDateEditor().setDate(null);
					returnBookBtn.setEnabled(false);
					JOptionPane.showMessageDialog(null, "successfully return.");
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "something went wrong.");
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Please input return date.");
			}
			
		}
		
		if (e.getSource() == backBtn) {
			setVisible(false);
			new Home().setVisible(true);
		}

	}

	private Connection getConnection() throws SQLException {
		DatabaseConnectionManager dcm = new DatabaseConnectionManager();
		return dcm.getConnection();
	}

	private ImageIcon setImage(String path) {
		ImageIcon icon = new ImageIcon(Home.class.getResource(path));
		Image image = icon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		icon = new ImageIcon(image);
		return icon;
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
