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

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import com.das.abhijeet.project.JDBC.Student;
import com.das.abhijeet.project.JDBC.StudentDAO;

public class AddStudent extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton backBtn, addBtn;
	private JTextField tf1;
	private JTextField tf3;
	private JComboBox comboBox;
	private JTextField tf2;
	
	private DatabaseConnectionManager dcm = new DatabaseConnectionManager();

	public AddStudent() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddBook.class.getResource("/com/das/abhijeet/project/LibraryManagementSystem/icons/logo.png")));
		setResizable(false);
		setTitle("Digital Library");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setSize(450, 252);
		centerFrame();
//		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		backBtn = new JButton(setImage("/com/das/abhijeet/project/LibraryManagementSystem/icons/tenth.png"));
		backBtn.setBackground(new Color(255, 255, 255));
		backBtn.setBorder(null);
		backBtn.setBounds(400, 0, 23, 23);
		contentPane.add(backBtn);
		backBtn.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 128, 128), new Color(0, 128, 128)), "Add Student", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel.setBounds(42, 11, 348, 189);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel l1 = new JLabel("ID:");
		l1.setHorizontalAlignment(SwingConstants.TRAILING);
		l1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l1.setBounds(10, 24, 111, 22);
		panel.add(l1);
		
		tf1 = new JTextField();
		tf1.setBounds(131, 27, 207, 20);
		panel.add(tf1);
		tf1.setColumns(10);
		
		JLabel l3 = new JLabel("Father's Name:");
		l3.setHorizontalAlignment(SwingConstants.TRAILING);
		l3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l3.setBounds(10, 85, 111, 22);
		panel.add(l3);
		
		tf3 = new JTextField();
		tf3.setColumns(10);
		tf3.setBounds(131, 88, 207, 20);
		panel.add(tf3);
		
		JLabel l4 = new JLabel("Department:");
		l4.setHorizontalAlignment(SwingConstants.TRAILING);
		l4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l4.setBounds(10, 117, 111, 22);
		panel.add(l4);
		
		addBtn = new JButton("Add Book");
		addBtn.setForeground(new Color(255, 255, 255));
		addBtn.setBackground(new Color(0, 128, 128));
		addBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		addBtn.setBounds(120, 152, 111, 23);
		panel.add(addBtn);
		addBtn.addActionListener(this);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"BBA", "MBA", "BSCSE", "BSEEE", "MSCSE", "MSEEE", "BSETE", "MSETE"}));
		comboBox.setBackground(Color.WHITE);
		comboBox.setBounds(131, 119, 207, 22);
		panel.add(comboBox);
		
		JLabel l2 = new JLabel("Name:");
		l2.setHorizontalAlignment(SwingConstants.TRAILING);
		l2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l2.setBounds(10, 55, 111, 22);
		panel.add(l2);
		
		tf2 = new JTextField();
		tf2.setColumns(10);
		tf2.setBounds(131, 58, 207, 20);
		panel.add(tf2);
		
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
		if(e.getSource() == backBtn) {
			setVisible(false);
			new Home().setVisible(true);
		}
		
		if(e.getSource() == addBtn) {
			try {
				long id = Long.parseLong(tf1.getText());
				String name = tf2.getText();
				String fatherName = tf3.getText();
				String department = (String) comboBox.getSelectedItem();
				StudentDAO studentDAO = new StudentDAO(dcm.getConnection());
				Student student = studentDAO.findById(id);
				if (student.getId() == 0) {
					if (!name.equals("") && !fatherName.equals("") && !department.equals("")) {
						student.setId(id);
						student.setName(name);
						student.setFatherName(fatherName);
						student.setDepartment(department);
						studentDAO.create(student);
						tf1.setText("");
						tf2.setText("");
						tf3.setText("");
						comboBox.setSelectedIndex(0);
						JOptionPane.showMessageDialog(null, "Enrollment Confirm");
					} else {
						JOptionPane.showMessageDialog(null, "This Student ID is not enrolled. But you hava to fill up all the fields first.");
					}
				} else {
					JOptionPane.showMessageDialog(null, "This Student ID is already enrolled.");
				}
				
			} catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Invalid input...");
			}
		}
	}
}
