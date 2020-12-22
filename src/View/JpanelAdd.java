package View;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import com.toedter.calendar.JDateChooser;

import models.DanhSachDongGop;
import models.DanhSachKhoanThu;
import services.DongGopService;
import services.KhoanThuService;

import javax.swing.border.MatteBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JpanelAdd extends JPanel {
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox comboBox;
	private JDateChooser dateChooser;
	private JDateChooser dateChooser_1;

	/**
	 * Create the panel.
	 */
	public JpanelAdd() {
		setBounds(337, 0, 814, 742);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(new Color(255, 255, 240));
		setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Tên khoản thu: ");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(183, 248, 108, 25);
		add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (comboBox.getSelectedItem().equals("Khoản thu")) {
				} else {
					textField_2.setText("Không phải điền");
				}

			}
		});
		textField_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		textField_1.setBounds(342, 242, 274, 36);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Số tiền thu : ");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(183, 318, 108, 25);
		add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("SansSerif", Font.PLAIN, 18));
		textField_2.setBounds(342, 312, 274, 36);
		add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Ngày bắt đầu : ");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(183, 392, 108, 25);
		add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Hạn kết thúc :");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(183, 465, 108, 25);
		add(lblNewLabel_4);

		JButton btnNewButton = new JButton("Lưu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertDb();
			}
		});
		btnNewButton.setBounds(679, 610, 108, 36);
		add(btnNewButton);

		dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("SansSerif", Font.PLAIN, 18));
		dateChooser.setBorder(new EmptyBorder(0, 0, 0, 0));
		dateChooser.setBounds(342, 381, 274, 36);
		add(dateChooser);

		dateChooser_1 = new JDateChooser();
		dateChooser_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		dateChooser_1.setBounds(342, 454, 274, 36);
		add(dateChooser_1);

		comboBox = new JComboBox();

		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 17));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Khoản thu", "Đóng góp" }));
		comboBox.setBounds(611, 91, 147, 30);
		add(comboBox);

		JLabel lblNewLabel = new JLabel("(*)");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(628, 242, 27, 31);
		add(lblNewLabel);

		JLabel lblNewLabel_5 = new JLabel("(*)");
		lblNewLabel_5.setForeground(Color.RED);
		lblNewLabel_5.setBounds(628, 312, 27, 31);
		add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("(*)");
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setBounds(628, 381, 27, 31);
		add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("(*)");
		lblNewLabel_7.setForeground(Color.RED);
		lblNewLabel_7.setBounds(628, 459, 27, 31);
		add(lblNewLabel_7);
//		setData(comboBox);
	}

	public void setData(JComboBox jComboBox) {
		if (jComboBox.getSelectedItem().equals("Đóng góp")) {
			textField_1.setText("Không phải điền");
		} else {
			textField_1.setText("");
		}
	}
	public void insertDb() {
		if (textField_1.getText().equals("") || textField_2.getText().equals("")
				|| dateChooser.getDate().equals("") || dateChooser_1.getDate().equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa điền đủ thông tin");
		} else if (String.valueOf(comboBox.getSelectedItem()).equals("Khoản thu")) {
			DanhSachKhoanThu kt = new DanhSachKhoanThu();
			kt.setTenKhoanThu(textField_1.getText());
			kt.setSoTien(Integer.parseInt(textField_2.getText()));
			kt.setBatDau(new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate()));
			kt.setKetThuc(new SimpleDateFormat("dd/MM/yyyy").format(dateChooser_1.getDate()));
			KhoanThuService khoanThuService = new KhoanThuService();
			khoanThuService.themKhoanThu(kt);
			JOptionPane.showMessageDialog(null, "Thêm thành công");
		} else {
			DanhSachDongGop dg = new DanhSachDongGop();
			dg.setTenDongGop(textField_1.getText());
			dg.setBatDau(new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate()));
			dg.setKetThuc(new SimpleDateFormat("dd/MM/yyyy").format(dateChooser_1.getDate()));
			DongGopService dongGopService = new DongGopService();
			dongGopService.themDongGop(dg);
			JOptionPane.showMessageDialog(null, "Thêm thành công");
		}
	}
}
