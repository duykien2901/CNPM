package View;
import image.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.MatteBorder;

import DanhmucSP.Danhmuc;
import controller.ChuyenController;

import javax.swing.border.LineBorder;
import java.awt.Cursor;
import java.awt.Toolkit;

public class Interface extends JFrame {

	private JPanel contentPane;
	public JPanel panel_4;
	private static JPanel panel_5;
	private JLabel lblNewLabel_2;
	private JPanel panel_1;
	private JPanel panel_2;
	private static JPanel panel_3;
	private static JPanel panel_6;
	private static JPanel panel_7;

	/**
	 * Launch the application.
	 */
	static void setDefaulBg() {
		List<JPanel> jPanel =new  ArrayList<JPanel>();
		jPanel.add(panel_3);
		jPanel.add(panel_5);
		jPanel.add(panel_6);
		jPanel.add(panel_7);
		
		for(JPanel jPanel2 : jPanel) {
			jPanel2.setBackground(new Color(50, 205, 50));
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					List<JPanel> listJPanels = new ArrayList<JPanel>();
					
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interface() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\LENOVO\\eclipse-workspace\\image\\iconfinder_Rounded-31_2024644 (1).png"));
		setTitle("Quản lí chi thu");
//		ChuyenController controller = new ChuyenController(root)
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 1216, 815);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(1, 0, 0, 0, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(22, 13, 1151, 742);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(105, 105, 105));
		panel_1.setBounds(0, 0, 325, 742);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 69, 0));
		panel_2.setBounds(0, 0, 325, 70);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quản lí dân cư");
		lblNewLabel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		lblNewLabel.setForeground(new Color(255, 245, 238));
		lblNewLabel.setBackground(new Color(255, 127, 80));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\iconfinder_17_Team_user_manager_squard_4308062.png"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Monospaced", Font.BOLD, 28));
		lblNewLabel.setBounds(0, 0, 325, 70);
		panel_2.add(lblNewLabel);
		
		panel_3 = new JPanel();
		panel_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_3.setBackground(new Color(250, 205, 50));
		panel_3.setBounds(24, 101, 301, 93);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Trang chủ");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaulBg();
				panel_3.setBackground(new Color(250, 205, 50));
//				JpanelMain jpanelMain = new JpanelMain();
//				panel_4.removeAll();
//				panel_4.add(jpanelMain);
//				panel_4.validate();
				ChuyenController controller = new ChuyenController(panel_4);
				controller.setView(panel_5, lblNewLabel_2);
				JpanelMain jpanelMain= new JpanelMain();
				panel_4.removeAll();
				panel_4.add(jpanelMain);
				panel_4.validate();

			}
		});
		
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\iconfinder_house-home-main-menu-start-building_3643769.png"));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Monospaced", Font.BOLD, 28));
		lblNewLabel_1.setBounds(0, 0, 301, 93);
		
		panel_3.add(lblNewLabel_1);
		
		panel_5 = new JPanel();
		panel_5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_5.setBackground(new Color(50, 205, 50));
		panel_5.setBounds(77, 226, 248, 93);
		panel_1.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_2 = new JLabel("Tạo danh sách");
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaulBg();
				panel_5.setBackground(new Color(250, 205, 50));
//				JpanelMain jpanelMain = new JpanelMain();
//				panel_4.removeAll();
//				panel_4.add(jpanelMain);
//				panel_4.validate();
				ChuyenController controller = new ChuyenController(panel_4);
				controller.setView(panel_5, lblNewLabel_2);
				JpanelAdd jpanelAdd= new JpanelAdd();
				panel_4.removeAll();
				panel_4.add(jpanelAdd);
				panel_4.validate();
			}
		});
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\iconfinder_people-plus-add-friend-member_3643746.png"));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Monospaced", Font.BOLD, 22));
		panel_5.add(lblNewLabel_2, BorderLayout.CENTER);
		
		panel_6 = new JPanel();
		panel_6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_6.setBackground(new Color(50, 205, 50));
		panel_6.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_6.setBounds(77, 371, 248, 93);
		panel_1.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_3 = new JLabel("Sửa danh sách");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaulBg();
				panel_6.setBackground(new Color(250, 205, 50));
//				JpanelMain jpanelMain = new JpanelMain();
//				panel_4.removeAll();
//				panel_4.add(jpanelMain);
//				panel_4.validate();
				ChuyenController controller = new ChuyenController(panel_4);
				controller.setView(panel_5, lblNewLabel_2);
				JpanelUpdate jpanelUpdate= new JpanelUpdate();
				panel_4.removeAll();
				panel_4.add(jpanelUpdate);
				panel_4.validate();
			}
		});
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\iconfinder_renew_4168571.png"));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Monospaced", Font.BOLD, 22));
		panel_6.add(lblNewLabel_3, BorderLayout.CENTER);
		
		panel_7 = new JPanel();
		panel_7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_7.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_7.setBackground(new Color(50, 205, 50));
		panel_7.setBounds(77, 517, 248, 93);
		panel_1.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_4 = new JLabel("Thống kê");
		lblNewLabel_4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setDefaulBg();
				panel_7.setBackground(new Color(250, 205, 50));
//				JpanelMain jpanelMain = new JpanelMain();
//				panel_4.removeAll();
//				panel_4.add(jpanelMain);
//				panel_4.validate();
				ChuyenController controller = new ChuyenController(panel_4);
				controller.setView(panel_5, lblNewLabel_2);
				Thongke tk= new Thongke();
				panel_4.removeAll();
				panel_4.add(tk);
				panel_4.validate();
			}
		});
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\image\\news.png"));
		lblNewLabel_4.setForeground(Color.WHITE);
		lblNewLabel_4.setFont(new Font("Monospaced", Font.BOLD, 22));
		panel_7.add(lblNewLabel_4, BorderLayout.CENTER);
		
		panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(119, 136, 153)));
		panel_4.setToolTipText("ss");
		panel_4.setBounds(337, 0, 814, 742);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		ChuyenController controller = new ChuyenController(panel_4);
		controller.setView(panel_5, lblNewLabel_2);
		JpanelMain jpanelMain= new JpanelMain();
		jpanelMain.setLocation(0, 0);
		panel_4.removeAll();
		panel_4.add(jpanelMain);
		panel_4.validate();
//		
//		List<Danhmuc> listItems = new ArrayList<Danhmuc>();
//		listItems.add(new Danhmuc("TrangChu", panel_3, lblNewLabel_1));
	}
}
