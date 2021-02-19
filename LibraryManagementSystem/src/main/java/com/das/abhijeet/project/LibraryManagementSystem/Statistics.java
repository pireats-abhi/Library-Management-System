package com.das.abhijeet.project.LibraryManagementSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.das.abhijeet.project.JDBC.Book;
import com.das.abhijeet.project.JDBC.DatabaseConnectionManager;
import com.das.abhijeet.project.JDBC.IssueBook;
import com.das.abhijeet.project.JDBC.IssueBookDAO;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Statistics extends JFrame {

	private JPanel contentPane;
	private JTable table, table2;
	
	private DefaultTableModel tm, tm2;
	
	private List<IssueBook> issueBooks;
	private JPanel panel_1;
	private JScrollPane scrollPane_1;

	public Statistics() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Statistics.class.getResource("/com/das/abhijeet/project/LibraryManagementSystem/icons/logo.png")));
		setTitle("Digital Library");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(685, 430);
		centerFrame();
//		setBounds(100, 100, 685, 402);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 128), new Color(0, 128, 128)), "Issued Book", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 45, 649, 165);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 22, 629, 132);
		panel.add(scrollPane);
		
		grabIssuedBooks();
		
		table = new JTable();
		scrollPane.setViewportView(table);
		tm = new DefaultTableModel(
				getTableIssueBooksObject(),
				new String[] {
					"ID", "Book ID", "Student ID", "Issued Date"
				}
			) {
				Class[] columnTypes = new Class[] {
					Long.class, Long.class, Long.class, Date.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		table.setModel(tm);
		
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 128), new Color(0, 128, 128)), "Returned Book", TitledBorder.TRAILING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 215, 649, 165);
		contentPane.add(panel_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 22, 629, 132);
		panel_1.add(scrollPane_1);
		
		table2 = new JTable();
		scrollPane_1.setViewportView(table2);
		tm2 = new DefaultTableModel(
				getTableReturnedBooksObject(),
				new String[] {
					"ID", "Book ID", "Student ID", "Issued Date", "Returned Date"
				}
			) {
				Class[] columnTypes = new Class[] {
					Long.class, Long.class, Long.class, Date.class, Date.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		table2.setModel(tm2);
		
		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new Home().setVisible(true);
			}
		});
		backBtn.setForeground(new Color(255, 255, 255));
		backBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		backBtn.setBackground(new Color(0, 128, 128));
		backBtn.setBounds(570, 11, 89, 23);
		contentPane.add(backBtn);
	}
	
	private Object[][] getTableReturnedBooksObject() {
		List<IssueBook> ibs = new ArrayList<> ();
		for(IssueBook issueBook : issueBooks) {
			if (issueBook.isReturn()) {
				ibs.add(issueBook);
			}
		}
		int size = ibs.size();
		Object[][] myObject = new Object[size][5];
		int i = 0;
		for(IssueBook ib : ibs) {
			myObject[i][0] = ib.getId();
			myObject[i][1] = ib.getBookID();
			myObject[i][2] = ib.getStudentID();
			myObject[i][3] = ib.getIssueDate();
			myObject[i][4] = ib.getReturnDate();
			i++;
		}
		return myObject;
	}
	
	private Object[][] getTableIssueBooksObject() {
		List<IssueBook> ibs = new ArrayList<> ();
		for(IssueBook issueBook : issueBooks) {
			if (!issueBook.isReturn()) {
				ibs.add(issueBook);
			}
		}
		int size = ibs.size();
		Object[][] myObject = new Object[size][4];
		int i = 0;
		for(IssueBook ib : ibs) {
			myObject[i][0] = ib.getId();
			myObject[i][1] = ib.getBookID();
			myObject[i][2] = ib.getStudentID();
			myObject[i][3] = ib.getIssueDate();
			i++;
		}
		return myObject;
	}
	
	private void grabIssuedBooks() {
		try {
			Connection connection = getConnection();
			IssueBookDAO issueBookDAO = new IssueBookDAO(connection);
			issueBooks = issueBookDAO.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() throws SQLException {
		DatabaseConnectionManager dcm = new DatabaseConnectionManager();
		return dcm.getConnection();
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
