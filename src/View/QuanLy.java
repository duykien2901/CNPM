 package View;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;
import java.awt.Button;
import javax.swing.border.MatteBorder;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class QuanLy extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLy frame = new QuanLy();
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
	public QuanLy() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 150, 949, 645);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(221, 160, 221));
		panel.setBounds(0, 0, 431, 598);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\iconfinder_file-manager_28646.png"));
		lblNewLabel.setBounds(220, 226, 134, 128);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quản Lý Dân Cư");
		lblNewLabel_1.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 38));
		lblNewLabel_1.setForeground(new Color(224, 255, 255));
		lblNewLabel_1.setBounds(45, 426, 331, 48);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nhóm 1");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\iconfinder_17_team_group_meeting_business_3964128.png"));
		lblNewLabel_2.setForeground(new Color(240, 248, 255));
		lblNewLabel_2.setFont(new Font("Monospaced", Font.BOLD | Font.ITALIC, 38));
		lblNewLabel_2.setBounds(95, 93, 221, 64);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\iconfinder_person-man_1120619.png"));
		lblNewLabel_3.setBounds(32, 226, 142, 128);
		panel.add(lblNewLabel_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 248, 220));
		panel_1.setBounds(428, 0, 503, 598);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\iconfinder_Rounded-31_2024644 (1).png"));
		lblNewLabel_4.setBounds(220, 30, 143, 131);
		panel_1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("User Name");
		lblNewLabel_5.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\iconfinder_User_4200769.png"));
		lblNewLabel_5.setForeground(new Color(105, 105, 105));
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 19));
		lblNewLabel_5.setBounds(23, 234, 143, 47);
		panel_1.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBackground(new Color(255, 248, 220));
		textField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(112, 128, 144)));
		textField.setFont(new Font("SansSerif", Font.PLAIN, 18));
		textField.setBounds(178, 241, 279, 35);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Password");
		lblNewLabel_6.setIcon(new ImageIcon("C:\\Users\\LENOVO\\Downloads\\iconfinder_icons_password_1564520.png"));
		lblNewLabel_6.setForeground(new Color(105, 105, 105));
		lblNewLabel_6.setFont(new Font("SansSerif", Font.BOLD, 19));
		lblNewLabel_6.setBounds(22, 309, 144, 35);
		panel_1.add(lblNewLabel_6);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(119, 136, 153)));
		passwordField.setBackground(new Color(255, 248, 220));
		passwordField.setBounds(178, 312, 279, 35);
		panel_1.add(passwordField);
		
		JLabel lblNewLabel_7 = new JLabel("Quên mật khẩu ? ");
		lblNewLabel_7.setForeground(new Color(169, 169, 169));
		lblNewLabel_7.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_7.setBounds(291, 499, 158, 35);
		panel_1.add(lblNewLabel_7);
		
		Button button = new Button("Login");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equals("Admin") && String.valueOf(passwordField.getPassword()).equals("123")) {
					Interface interface1;
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					interface1 = new Interface();
					interface1.setVisible(true);
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
				}
			}
		});
		button.setBackground(new Color(192, 192, 192));
		button.setForeground(new Color(105, 105, 105));
		button.setFont(new Font("SansSerif", Font.BOLD, 19));
		button.setBounds(178, 398, 279, 40);
		panel_1.add(button);
	}
}
