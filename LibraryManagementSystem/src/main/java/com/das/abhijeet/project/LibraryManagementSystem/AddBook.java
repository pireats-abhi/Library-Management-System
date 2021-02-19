package com.das.abhijeet.project.LibraryManagementSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

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
import java.awt.Toolkit;

public class AddBook extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton backBtn, addBtn;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTextField tf4;
	private JTextField tf5;
	
	private DatabaseConnectionManager dcm = new DatabaseConnectionManager();

	public AddBook() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddBook.class.getResource("/com/das/abhijeet/project/LibraryManagementSystem/icons/logo.png")));
		setResizable(false);
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
		
		backBtn = new JButton(setImage("/com/das/abhijeet/project/LibraryManagementSystem/icons/tenth.png"));
		backBtn.setBackground(new Color(255, 255, 255));
		backBtn.setBorder(null);
		backBtn.setBounds(400, 0, 23, 23);
		contentPane.add(backBtn);
		backBtn.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 128, 128), new Color(0, 128, 128)), "Add Book", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel.setBounds(42, 11, 348, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel l1 = new JLabel("Name:");
		l1.setHorizontalAlignment(SwingConstants.TRAILING);
		l1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l1.setBounds(10, 24, 77, 22);
		panel.add(l1);
		
		tf1 = new JTextField();
		tf1.setBounds(97, 27, 241, 20);
		panel.add(tf1);
		tf1.setColumns(10);
		
		JLabel l2 = new JLabel("Author:");
		l2.setHorizontalAlignment(SwingConstants.TRAILING);
		l2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l2.setBounds(10, 57, 77, 22);
		panel.add(l2);
		
		tf2 = new JTextField();
		tf2.setColumns(10);
		tf2.setBounds(97, 60, 241, 20);
		panel.add(tf2);
		
		JLabel l3 = new JLabel("Edition:");
		l3.setHorizontalAlignment(SwingConstants.TRAILING);
		l3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l3.setBounds(10, 90, 77, 22);
		panel.add(l3);
		
		tf3 = new JTextField();
		tf3.setColumns(10);
		tf3.setBounds(97, 93, 241, 20);
		panel.add(tf3);
		
		JLabel l4 = new JLabel("Price:");
		l4.setHorizontalAlignment(SwingConstants.TRAILING);
		l4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l4.setBounds(10, 123, 77, 22);
		panel.add(l4);
		
		tf4 = new JTextField();
		tf4.setColumns(10);
		tf4.setBounds(97, 126, 241, 20);
		panel.add(tf4);
		
		JLabel l5 = new JLabel("Pages:");
		l5.setHorizontalAlignment(SwingConstants.TRAILING);
		l5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		l5.setBounds(10, 156, 77, 22);
		panel.add(l5);
		
		tf5 = new JTextField();
		tf5.setColumns(10);
		tf5.setBounds(97, 159, 241, 20);
		panel.add(tf5);
		
		addBtn = new JButton("Add Book");
		addBtn.setForeground(new Color(255, 255, 255));
		addBtn.setBackground(new Color(0, 128, 128));
		addBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		addBtn.setBounds(120, 195, 111, 23);
		panel.add(addBtn);
		addBtn.addActionListener(this);
	}
	
	private ImageIcon setImage(String path) {
		ImageIcon icon = new ImageIcon(Home.class.getResource(path));
		Image image = icon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		icon = new ImageIcon(image);
		return icon;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backBtn) {
			setVisible(false);
			new Home().setVisible(true);
		}
		if(e.getSource() == addBtn) {
			try {
				Book book = new Book();
				book.setName(tf1.getText());
				book.setAuthor(tf2.getText());
				book.setEdition(tf3.getText());
				double price = Double.parseDouble(tf4.getText());
				book.setPrice(price);
				book.setPages(tf5.getText());
				
				Connection connection = dcm.getConnection();
				BookDAO bookDAO = new BookDAO(connection);
				bookDAO.create(book);
				setVisible(false);
				new Home().setVisible(true);
			} catch(Exception e1) {
				JOptionPane.showMessageDialog(null, "Invalid input...");
			}
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
