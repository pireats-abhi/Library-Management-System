package com.das.abhijeet.project.LibraryManagementSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Home extends JFrame implements ActionListener {

	private JPanel contentPane;
	private ImageIcon icon;
	private Image image;
	private JButton b1, b2, b3, b4, b5,b6;
	private JMenuItem mi1, mi2, mi3, mi4, mi5, mi6;

	public Home() {
		super("Digital Library");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Home.class.getResource("/com/das/abhijeet/project/LibraryManagementSystem/icons/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(450, 358);
		centerFrame();
//		setBounds(100, 100, 450, 358);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(0, 128, 128));
		menuBar.setBounds(0, 0, 434, 32);
		contentPane.add(menuBar);
		
		JMenu m1 = new JMenu("Records");
		m1.setForeground(new Color(255, 255, 255));
		menuBar.add(m1);
		
		mi1 = new JMenuItem("Book Details");
		mi1.addActionListener(this);
		mi1.setForeground(new Color(255, 255, 255));
		mi1.setBackground(new Color(0, 128, 128));
		m1.add(mi1);
		
		mi2 = new JMenuItem("Student Details");
		mi2.addActionListener(this);
		mi2.setForeground(new Color(255, 255, 255));
		mi2.setBackground(new Color(0, 128, 128));
		m1.add(mi2);
		
		JMenu m2 = new JMenu("Help");
		m2.setForeground(new Color(255, 255, 255));
		menuBar.add(m2);
		
		mi3 = new JMenuItem("Read me");
		mi3.addActionListener(this);
		mi3.setForeground(new Color(255, 255, 255));
		mi3.setBackground(new Color(0, 128, 128));
		m2.add(mi3);
		
		mi4 = new JMenuItem("About us");
		mi4.addActionListener(this);
		mi4.setForeground(new Color(255, 255, 255));
		mi4.setBackground(new Color(0, 128, 128));
		m2.add(mi4);
		
		JMenu m3 = new JMenu("Exit");
		m3.setForeground(new Color(255, 255, 255));
		menuBar.add(m3);
		
		mi5 = new JMenuItem("Logout");
		mi5.addActionListener(this);
		mi5.setForeground(new Color(255, 255, 255));
		mi5.setBackground(new Color(0, 128, 128));
		m3.add(mi5);
		
		mi6 = new JMenuItem("Exit");
		mi6.addActionListener(this);
		mi6.setForeground(new Color(255, 255, 255));
		mi6.setBackground(new Color(0, 128, 128));
		m3.add(mi6);
		
		JLabel l1 = new JLabel("Library Management System");
		l1.setForeground(new Color(0, 128, 128));
		l1.setFont(new Font("Microsoft JhengHei UI Light", Font.BOLD, 18));
		l1.setHorizontalAlignment(SwingConstants.CENTER);
		l1.setBounds(0, 40, 434, 32);
		contentPane.add(l1);
		
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 255, 255));
		panel1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 128, 128), new Color(0, 128, 128)), "Operation", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel1.setBounds(10, 75, 414, 104);
		contentPane.add(panel1);
		panel1.setLayout(null);
		
		b1 = new JButton(setImage("/com/das/abhijeet/project/LibraryManagementSystem/icons/second.png"));
		b1.setBackground(new Color(255, 255, 255));
		b1.setBorder(null);
		b1.setBounds(72, 23, 70, 70);
		panel1.add(b1);
		b1.addActionListener(this);
		
		b2 = new JButton(setImage("/com/das/abhijeet/project/LibraryManagementSystem/icons/third.png"));
		b2.setBackground(new Color(255, 255, 255));
		b2.setBorder(null);
		b2.setBounds(166, 23, 70, 70);
		panel1.add(b2);
		b2.addActionListener(this);
		
		b3 = new JButton(setImage("/com/das/abhijeet/project/LibraryManagementSystem/icons/fourth.png"));
		b3.setBackground(new Color(255, 255, 255));
		b3.setBorder(null);
		b3.setBounds(270, 23, 70, 70);
		panel1.add(b3);
		b3.addActionListener(this);
		
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(255, 255, 255));
		panel2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(0, 128, 128), new Color(0, 128, 128)), "Action", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
		panel2.setBounds(10, 201, 414, 104);
		contentPane.add(panel2);
		panel2.setLayout(null);
		
		b4 = new JButton(setImage("/com/das/abhijeet/project/LibraryManagementSystem/icons/fifth.png"));
		b4.setBackground(new Color(255, 255, 255));
		b4.setBorder(null);
		b4.setBounds(72, 23, 70, 70);
		panel2.add(b4);
		b4.addActionListener(this);
		
		b5 = new JButton(setImage("/com/das/abhijeet/project/LibraryManagementSystem/icons/sixth.png"));
		b5.setBackground(new Color(255, 255, 255));
		b5.setBorder(null);
		b5.setBounds(166, 23, 70, 70);
		panel2.add(b5);
		b5.addActionListener(this);
		
		b6 = new JButton(setImage("/com/das/abhijeet/project/LibraryManagementSystem/icons/seventh.png"));
		b6.setBackground(new Color(255, 255, 255));
		b6.setBorder(null);
		b6.setBounds(270, 23, 70, 70);
		panel2.add(b6);
		b6.addActionListener(this);
	}
	
	private ImageIcon setImage(String path) {
		icon = new ImageIcon(Home.class.getResource(path));
		image = icon.getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT);
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
		String msg = e.getActionCommand();
		if (msg.equals("Logout")) {
			setVisible(false);
			new LoginUser().setVisible(true);
		} else if (msg.equals("Exit")) {
			System.exit(ABORT);
		} else if (msg.equals("Read me")) {
			new ReadMe().setVisible(true);
		} else if (msg.equals("About us")) {
			new ReadMe().setVisible(true);
		} else if (msg.equals("Book Details")) {
			setVisible(false);
			new BookDetails().setVisible(true);
		} else if (msg.equals("Student Details")) {
			setVisible(false);
			new StudentDetails().setVisible(true);
		}
		
		if (e.getSource() == b1) {
			setVisible(false);
			new AddBook().setVisible(true);
		}
		if (e.getSource() == b2) {
			setVisible(false);
			new Statistics().setVisible(true);
		}
		if (e.getSource() == b3) {
			setVisible(false);
			new AddStudent().setVisible(true);
		}
		if (e.getSource() == b4) {
			setVisible(false);
			new IssueBookGui().setVisible(true);
		}
		if (e.getSource() == b5) {
			setVisible(false);
			new ReturnBookGui().setVisible(true);
		}
		if (e.getSource() == b6) {
			new ReadMe().setVisible(true);
		}
		
	}
}
