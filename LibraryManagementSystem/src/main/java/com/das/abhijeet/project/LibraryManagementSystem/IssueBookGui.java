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
import java.sql.SQLException;
import java.util.Date;

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

import com.das.abhijeet.project.JDBC.Book;
import com.das.abhijeet.project.JDBC.BookDAO;
import com.das.abhijeet.project.JDBC.DatabaseConnectionManager;
import com.das.abhijeet.project.JDBC.IssueBook;
import com.das.abhijeet.project.JDBC.IssueBookDAO;
import com.das.abhijeet.project.JDBC.Student;
import com.das.abhijeet.project.JDBC.StudentDAO;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

public class IssueBookGui extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JTextField t4;
	private JTextField t5;
	private JTextField t6;
	private JTextField t7;
	private JTextField t8;
	private JTextField t9;
	private JTextField t10;
	private JDateChooser dateChooser;
	private JButton searchBtn, searchBtn1, issueBtn, backBtn, refreshBtn;

	public IssueBookGui() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				IssueBookGui.class.getResource("/com/das/abhijeet/project/LibraryManagementSystem/icons/logo.png")));
		setTitle("Digital Library");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(655, 331);
		centerFrame();
//		setBounds(100, 100, 655, 331);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel1 = new JPanel();
		panel1.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 128), new Color(0, 128, 128)),
						"Book", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel1.setBackground(Color.WHITE);
		panel1.setBounds(10, 11, 304, 193);
		contentPane.add(panel1);
		panel1.setLayout(null);

		JLabel l1 = new JLabel("Book ID:");
		l1.setHorizontalAlignment(SwingConstants.TRAILING);
		l1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l1.setBounds(28, 23, 73, 17);
		panel1.add(l1);

		JLabel l2 = new JLabel("Name:");
		l2.setHorizontalAlignment(SwingConstants.TRAILING);
		l2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l2.setBounds(28, 51, 73, 17);
		panel1.add(l2);

		JLabel l3 = new JLabel("Author:");
		l3.setHorizontalAlignment(SwingConstants.TRAILING);
		l3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l3.setBounds(28, 79, 73, 17);
		panel1.add(l3);

		JLabel l4 = new JLabel("Edition:");
		l4.setHorizontalAlignment(SwingConstants.TRAILING);
		l4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l4.setBounds(28, 107, 73, 17);
		panel1.add(l4);

		JLabel l5 = new JLabel("Price:");
		l5.setHorizontalAlignment(SwingConstants.TRAILING);
		l5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l5.setBounds(28, 135, 73, 17);
		panel1.add(l5);

		JLabel l6 = new JLabel("Pages:");
		l6.setHorizontalAlignment(SwingConstants.TRAILING);
		l6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l6.setBounds(28, 163, 73, 17);
		panel1.add(l6);

		t1 = new JTextField();
		t1.setBounds(111, 23, 86, 20);
		panel1.add(t1);
		t1.setColumns(10);

		t2 = new JTextField();
		t2.setEditable(false);
		t2.setColumns(10);
		t2.setBounds(111, 51, 183, 20);
		panel1.add(t2);

		t3 = new JTextField();
		t3.setEditable(false);
		t3.setColumns(10);
		t3.setBounds(111, 79, 183, 20);
		panel1.add(t3);

		t4 = new JTextField();
		t4.setEditable(false);
		t4.setColumns(10);
		t4.setBounds(111, 107, 183, 20);
		panel1.add(t4);

		t5 = new JTextField();
		t5.setEditable(false);
		t5.setColumns(10);
		t5.setBounds(111, 135, 183, 20);
		panel1.add(t5);

		t6 = new JTextField();
		t6.setEditable(false);
		t6.setColumns(10);
		t6.setBounds(111, 163, 183, 20);
		panel1.add(t6);

		searchBtn = new JButton("Search");
		searchBtn.setForeground(new Color(255, 255, 255));
		searchBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchBtn.setBackground(new Color(0, 128, 128));
		searchBtn.setBounds(205, 22, 89, 23);
		panel1.add(searchBtn);
		searchBtn.addActionListener(this);

		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 128), new Color(0, 128, 128)),
						"Student", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(325, 11, 304, 193);
		contentPane.add(panel2);

		JLabel l7 = new JLabel("Student ID:");
		l7.setHorizontalAlignment(SwingConstants.TRAILING);
		l7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l7.setBounds(10, 22, 91, 17);
		panel2.add(l7);

		t7 = new JTextField();
		t7.setColumns(10);
		t7.setBounds(111, 22, 86, 20);
		panel2.add(t7);

		searchBtn1 = new JButton("Search");
		searchBtn1.setForeground(Color.WHITE);
		searchBtn1.setFont(new Font("Tahoma", Font.BOLD, 14));
		searchBtn1.setBackground(new Color(0, 128, 128));
		searchBtn1.setBounds(205, 21, 89, 23);
		panel2.add(searchBtn1);
		searchBtn1.addActionListener(this);

		JLabel l8 = new JLabel("Name:");
		l8.setHorizontalAlignment(SwingConstants.TRAILING);
		l8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l8.setBounds(28, 50, 73, 17);
		panel2.add(l8);

		t8 = new JTextField();
		t8.setEditable(false);
		t8.setColumns(10);
		t8.setBounds(111, 50, 183, 20);
		panel2.add(t8);

		JLabel l9 = new JLabel("Father's Name:");
		l9.setHorizontalAlignment(SwingConstants.TRAILING);
		l9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l9.setBounds(0, 78, 101, 17);
		panel2.add(l9);

		t9 = new JTextField();
		t9.setEditable(false);
		t9.setColumns(10);
		t9.setBounds(111, 78, 183, 20);
		panel2.add(t9);

		JLabel l10 = new JLabel("Department:");
		l10.setHorizontalAlignment(SwingConstants.TRAILING);
		l10.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l10.setBounds(10, 106, 91, 17);
		panel2.add(l10);

		t10 = new JTextField();
		t10.setEditable(false);
		t10.setColumns(10);
		t10.setBounds(111, 106, 183, 20);
		panel2.add(t10);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 128), new Color(0, 128, 128)),
						"/", TitledBorder.TRAILING, TitledBorder.BOTTOM, null, new Color(0, 128, 128)));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(10, 215, 619, 67);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel l11 = new JLabel("Date of Issue:");
		l11.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l11.setBounds(9, 21, 85, 23);
		panel.add(l11);

		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton()
				.setIcon(setImage("/com/das/abhijeet/project/LibraryManagementSystem/icons/calendar.png"));
		dateChooser.getCalendarButton().setBackground(new Color(255, 255, 255));
		dateChooser.getCalendarButton().setBorder(null);
		dateChooser.setBounds(104, 24, 208, 20);
		dateChooser.setDateFormatString("dd-MM-yyyy");
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editor.setEditable(false);
		panel.add(dateChooser);

		issueBtn = new JButton("Issue");
		issueBtn.setForeground(Color.WHITE);
		issueBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		issueBtn.setBackground(new Color(0, 128, 128));
		issueBtn.setBounds(322, 23, 89, 23);
		panel.add(issueBtn);
		issueBtn.addActionListener(this);

		backBtn = new JButton("Back");
		backBtn.setForeground(Color.WHITE);
		backBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		backBtn.setBackground(new Color(0, 128, 128));
		backBtn.setBounds(421, 23, 89, 23);
		panel.add(backBtn);
		backBtn.addActionListener(this);

		refreshBtn = new JButton("Refresh");
		refreshBtn.setForeground(Color.WHITE);
		refreshBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		refreshBtn.setBackground(new Color(0, 128, 128));
		refreshBtn.setBounds(520, 23, 89, 23);
		panel.add(refreshBtn);
		refreshBtn.addActionListener(this);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backBtn) {
			setVisible(false);
			new Home().setVisible(true);
		}

		if (e.getSource() == searchBtn) {
			if (!t1.getText().equals("")) {
				try {
					long input = Long.parseLong(t1.getText());
					Connection connection = getConnection();
					BookDAO bookDAO = new BookDAO(connection);
					Book book = bookDAO.findById(input);
					if (book.getId() != 0) {
						t2.setText(book.getName());
						t3.setText(book.getAuthor());
						t4.setText(book.getEdition());
						t5.setText(Double.toString(book.getPrice()));
						t6.setText(book.getPages());
					} else {
						setTextForSearchBtn();
						JOptionPane.showMessageDialog(null, "Book ID is incorrect.");
					}
				} catch (Exception e1) {
					setTextForSearchBtn();
					JOptionPane.showMessageDialog(null, "Invalid input.");
				}
			} else {
				setTextForSearchBtn();
				JOptionPane.showMessageDialog(null, "Please input a book ID.");
			}
		}

		if (e.getSource() == searchBtn1) {
			if (!t7.getText().equals("")) {
				try {
					long input = Long.parseLong(t7.getText());
					Connection connection = getConnection();
					StudentDAO studentDAO = new StudentDAO(connection);
					Student student = studentDAO.findById(input);
					if (student.getId() != 0) {
						t8.setText(student.getName());
						t9.setText(student.getFatherName());
						t10.setText(student.getDepartment());
					} else {
						setTextForSearchBtn1();
						JOptionPane.showMessageDialog(null, "Student ID is incorrect.");
					}
				} catch (Exception e1) {
					setTextForSearchBtn1();
					JOptionPane.showMessageDialog(null, "Invalid input.");
				}
			} else {
				setTextForSearchBtn1();
				JOptionPane.showMessageDialog(null, "Please input a student ID.");
			}
		}

		if (e.getSource() == issueBtn) {
			Date date = dateChooser.getDate();
			if (date != null && !t2.getText().equals("") && !t8.getText().equals("")) {
				try {
					java.sql.Date d = new java.sql.Date (date.getTime());
					long bookID = Long.parseLong(t1.getText());
					long studentID = Long.parseLong(t7.getText());
					if (isBookIdValid(t2.getText(), bookID)
							&& isStudentIdValid(t8.getText(), studentID)) {
						IssueBook issueBook = new IssueBook();
						issueBook.setIssueDate(d);
						issueBook.setBookID(bookID);
						issueBook.setStudentID(studentID);
						
						Connection connection = getConnection();
						IssueBookDAO issueBookDAO = new IssueBookDAO(connection);
						issueBookDAO.create(issueBook);
						
						setTextForSearchBtn();
						setTextForSearchBtn1();
						dateChooser.getDateEditor().setDate(null);
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Something wrong...");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Please fill up all the information correctly.");
			}
		}

		if (e.getSource() == refreshBtn) {
			setTextForSearchBtn();
			setTextForSearchBtn1();
			dateChooser.getDateEditor().setDate(null);
		}

	}

	private boolean isBookIdValid(String bookName, long bookId) {
		try {
			Connection connection = getConnection();
			BookDAO bookDAO = new BookDAO(connection);
			Book book = bookDAO.findById(bookId);
			if (book.getName().equals(bookName)) {
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Book ID and Book name doesn't match");
		}

		return false;
	}

	private boolean isStudentIdValid(String studentName, long studentID) {
		try {
			Connection connection = getConnection();
			StudentDAO studentDAO = new StudentDAO(connection);
			Student student = studentDAO.findById(studentID);
			if (student.getName().equals(studentName)) {
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Student ID and Student name doesn't match");
		}

		return false;
	}

	private void setTextForSearchBtn1() {
		t7.setText("");
		t8.setText("");
		t9.setText("");
		t10.setText("");
	}

	private void setTextForSearchBtn() {
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		t5.setText("");
		t6.setText("");
	}

	private Connection getConnection() throws SQLException {
		DatabaseConnectionManager dcm = new DatabaseConnectionManager();
		return dcm.getConnection();
	}
}
