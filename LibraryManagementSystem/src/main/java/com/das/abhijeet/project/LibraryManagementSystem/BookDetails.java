package com.das.abhijeet.project.LibraryManagementSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
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

public class BookDetails extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	
	private DatabaseConnectionManager dcm = new DatabaseConnectionManager();
	private JTable table;
	private JTable table_1 = new JTable();
	private JButton btnSearch, backBtn, dltBtn;
	private JPanel panel;
	private JScrollPane scrollPane;
	
	private DefaultTableModel tm;
	
	private List<Book> books;

	public BookDetails() {
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
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 128), new Color(0, 128, 128)), "Books", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 128)));
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
		
		grabBooks();
		
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
					"Book ID", "Book Name", "Author", "Edition", "Price", "Pages"
				}
			) {
				Class[] columnTypes = new Class[] {
					Long.class, String.class, String.class, String.class, Double.class, String.class
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
		int size = books.size();
		Object[][] myObject = new Object[size][6];
		int i = 0;
		for(Book book : books) {
			myObject[i][0] = book.getId();
			myObject[i][1] = book.getName();
			myObject[i][2] = book.getAuthor();
			myObject[i][3] = book.getEdition();
			myObject[i][4] = book.getPrice();
			myObject[i][5] = book.getPages();
			i++;
		}
		return myObject;
	}
	
	private void grabBooks() {
		try {
			Connection connection = dcm.getConnection();
			BookDAO bookDAO = new BookDAO(connection);
			books = bookDAO.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void grabSelectedBooks(String bookName) {
		try {
			Connection connection = dcm.getConnection();
			BookDAO bookDAO = new BookDAO(connection);
			books = bookDAO.findByName(bookName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String input = textField.getText();
		if(e.getSource() == btnSearch) {
			if(!input.equals("")) {
				grabSelectedBooks(input);
				table_1.removeAll();
				scrollPane.removeAll();
				generateTable();
			} else {
				grabBooks();
				table_1.removeAll();
				scrollPane.removeAll();
				generateTable();
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
				long response = Long.parseLong(JOptionPane.showInputDialog("Which book ID do you want to delete?"));
				BookDAO bookDAO = new BookDAO(connection);
				Book book = bookDAO.findById(response);
				if (book.getId() != 0) {
					bookDAO.delete(response);
					textField.setText("");
					grabBooks();
					table_1.removeAll();
					scrollPane.removeAll();
					generateTable();
					JOptionPane.showMessageDialog(null, "Book deleted.");
				} else {
					JOptionPane.showMessageDialog(null, "Book ID is invalid.");
				}
				
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "Books are not deleted.");
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "Something went wrong.");
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
