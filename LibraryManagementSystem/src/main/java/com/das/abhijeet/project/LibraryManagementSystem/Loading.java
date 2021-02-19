package com.das.abhijeet.project.LibraryManagementSystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class Loading extends JFrame implements Runnable {

	private JPanel contentPane;
	private JProgressBar progressBar;

	Thread t;

	public Loading() {
		super("Digital Library");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Loading.class.getResource("/com/das/abhijeet/project/LibraryManagementSystem/icons/logo.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(450, 300);
		centerFrame();
//		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		progressBar = new JProgressBar();
		progressBar.setToolTipText("");
		progressBar.setBackground(new Color(0, 0, 139));
		progressBar.setForeground(new Color(255, 255, 255));
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		progressBar.setBounds(46, 119, 342, 31);
		contentPane.add(progressBar);

		JLabel lblNewLabel = new JLabel("Digital Libraray...");
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(77, 78, 276, 43);
		contentPane.add(lblNewLabel);

		t = new Thread(this);
		setUploading();
	}

	public void setUploading() {
		t.start();
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i <= 100; i++) {
				int m = progressBar.getMaximum();
				int v = progressBar.getValue();
				if (v < m) {
					progressBar.setValue(progressBar.getValue() + 1);
				} else {
					setVisible(false);
                    new Home().setVisible(true);
				}
				Thread.sleep(25);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
