package com.das.abhijeet.project.LibraryManagementSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.das.abhijeet.project.JDBC.Book;
import com.das.abhijeet.project.JDBC.BookDAO;
import com.das.abhijeet.project.JDBC.DatabaseConnectionManager;
import com.das.abhijeet.project.JDBC.Student;
import com.das.abhijeet.project.JDBC.StudentDAO;

public class StudentDetails extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	
	private DatabaseConnectionManager dcm = new DatabaseConnectionManager();
	private JTable table;
	private JTable table_1 = new JTable();
	private JButton btnSearch, backBtn, dltBtn;
	private JPanel panel;
	private JScrollPane scrollPane;
	
	private DefaultTableModel tm;
	
	private List<Student> students;

	public StudentDetails() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(BookDetails.class.getResource("/com/das/abhijeet/project/LibraryManagementSystem/icons/logo.png")));
		setTitle("Digital Library");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(450, 300);
		centerFrame();
//		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 128), new Color(0, 128, 128)), "Students", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		backBtn = new JButton("Back");
		backBtn.setForeground(new Color(0, 128, 128));
		backBtn.addActionListener(this);
		backBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		backBtn.setBackground(new Color(255, 255, 255));
		backBtn.setBounds(10, 18, 89, 23);
		panel.add(backBtn);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setForeground(new Color(0, 128, 128));
		textField.setBounds(107, 19, 126, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(this);
		btnSearch.setForeground(new Color(0, 128, 128));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setBounds(240, 18, 89, 23);
		panel.add(btnSearch);
		
		dltBtn = new JButton("X");
		dltBtn.setForeground(new Color(0, 128, 128));
		dltBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		dltBtn.setBackground(Color.WHITE);
		dltBtn.setBounds(337, 18, 67, 23);
		panel.add(dltBtn);
		dltBtn.addActionListener(this);
		
		grabStudents();
		
		generateTable();
	}
	private void generateTable() {
		scrollPane = new JScrollPane();
		scrollPane.setBounds(13, 45, 388, 180);
		panel.add(scrollPane);
		table_1.setEnabled(false);
		scrollPane.setViewportView(table_1);
		tm = new DefaultTableModel(
				getTableObject(),
				new String[] {
					"Student ID", "Name", "Father's Name", "Department"
				}
			) {
				Class[] columnTypes = new Class[] {
					Long.class, String.class, String.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		table_1.setModel(tm);
		
//		table_1.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int row = table_1.getSelectedRow();
//				System.out.println("1");
//				System.out.println(table_1.getModel().getValueAt(row, 1).toString());
//				long id = (long) table_1.getModel().getValueAt(row, 0);
//				System.out.println("2");
//				JOptionPane.showMessageDialog(null, id);
//				System.out.println("3");
//				System.out.println("------------");
//			}
//		});
	}
	
	private Object[][] getTableObject() {
		int size = students.size();
		Object[][] myObject = new Object[size][6];
		int i = 0;
		for(Student student : students) {
			myObject[i][0] = student.getId();
			myObject[i][1] = student.getName();
			myObject[i][2] = student.getFatherName();
			myObject[i][3] = student.getDepartment();
			i++;
		}
		return myObject;
	}
	
	private void grabStudents() {
		try {
			Connection connection = dcm.getConnection();
			StudentDAO studentDAO = new StudentDAO(connection);
			students = studentDAO.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void grabSelectedStudent(Long id) {
		try {
			Connection connection = dcm.getConnection();
			StudentDAO studentDAO = new StudentDAO(connection);
			Student student = studentDAO.findById(id);
			if(student.getId() == 0) {
				JOptionPane.showMessageDialog(null, "Student ID is not enrolled.");
			} else {
				List<Student> st = new ArrayList<>();
				st.add(student);
				students = st;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Long input;
		try {
			input = Long.parseLong(textField.getText());
		} catch (NumberFormatException e2) {
			input = (long) 0;
			textField.setText("");
		}
		if(e.getSource() == btnSearch) {
			if(input != 0) {
				extracted(input);
				textField.setText("");
			} else {
				extracted();
				JOptionPane.showMessageDialog(null, "Invalid input.");
			}
		}
		
		if(e.getSource() == backBtn) {
			setVisible(false);
			new Home().setVisible(true);
		}
		
		if(e.getSource() == dltBtn) {
			
			try {
				Connection connection = dcm.getConnection();
				JDialog.setDefaultLookAndFeelDecorated(true);
				long response = Long.parseLong(JOptionPane.showInputDialog("Which Student ID do you want to delete?"));
				StudentDAO studentDAO = new StudentDAO(connection);
				Student student = studentDAO.findById(response);
				if (student.getId() != 0) {
					studentDAO.delete(response);
					textField.setText("");
					extracted();
					JOptionPane.showMessageDialog(null, "Student deleted.");
				} else {
					JOptionPane.showMessageDialog(null, "Student ID is invalid.");
				}
				
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Student are not deleted.");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Something went wrong.");
			}
		}
		
	}

	private void extracted(Long input) {
		grabSelectedStudent(input);
		table_1.removeAll();
		scrollPane.removeAll();
		generateTable();
	}

	private void extracted() {
		grabStudents();
		table_1.removeAll();
		scrollPane.removeAll();
		generateTable();
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
