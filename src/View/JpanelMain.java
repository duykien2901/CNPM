package View;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.DanhSachDongGop;
import models.DanhSachKhoanThu;
import services.DongGopService;
import services.KhoanThuService;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;

public class JpanelMain extends JPanel {
	private JTable table;
	private JTextField textField;
	private ArrayList<DanhSachKhoanThu> khoanThus;
	private ArrayList<DanhSachDongGop> dongGop;
	/**
	 * Create the panel.
	 */
	public JpanelMain() {
		setBounds(337, 0, 822, 742);
		setBackground(new Color(255, 250, 240));
		setLayout(null);
		
		// add table
		KhoanThuService khoanThuService = new KhoanThuService();
		khoanThus = new ArrayList<DanhSachKhoanThu>();
		khoanThus = khoanThuService.getDanhSach();
		
		DongGopService dongGopService = new DongGopService();
		dongGop = new ArrayList<DanhSachDongGop>();
		dongGop = dongGopService.getDanhSach();
		
		table = new JTable();
		table.setSelectionBackground(new Color(224, 255, 255));
		table.setFont(new Font("SansSerif", Font.PLAIN, 17));
		table.setBackground(new Color(255, 248, 220));
		table.setAutoCreateRowSorter(true);
		table.setRowHeight(40);
	
		
		
		JLabel lblUs = new JLabel("Username");
		lblUs.setIcon(new ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\image\\iconfinder_User_4200769.png"));
		lblUs.setHorizontalAlignment(SwingConstants.CENTER);
		lblUs.setFont(new Font("Monospaced", Font.BOLD, 22));
		lblUs.setBounds(481, 13, 169, 44);
		add(lblUs);
		
		JPanel panel = new JPanel();
		panel.setBounds(new Rectangle(1, 1, 1, 1));
		panel.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(0, 0, 0)));
		panel.setBounds(24, 197, 773, 414);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("SansSerif", Font.PLAIN, 18));
		panel.add(scrollPane, BorderLayout.CENTER);
		
		
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton("Đăng xuất");
		btnNewButton.setBackground(new Color(222, 184, 135));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 14));
		btnNewButton.setBounds(674, 20, 108, 35);
		add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(String.valueOf(comboBox.getSelectedItem()).equals("Khoản thu")) {
					dataDongGop(dongGop);
				} else {
					dataKhoanThu(khoanThus);
				}
			}
		});
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		comboBox.setBackground(new Color(211, 211, 211));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Khoản thu", "Đóng góp"}));
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 17));
		comboBox.setBounds(644, 133, 153, 35);
		add(comboBox);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(String.valueOf(comboBox.getSelectedItem()).equals("Khoản thu")) {
					searchKhoanThu(khoanThus);
				} else {
					searchDongGop(dongGop);
				}
			}
		});
		textField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		textField.setBackground(new Color(255, 250, 240));
		textField.setFont(new Font("SansSerif", Font.PLAIN, 20));
		textField.setBounds(179, 132, 221, 35);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Trang chủ");
		lblNewLabel.setForeground(new Color(255, 160, 122));
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\Quanly\\src\\image\\iconfinder_house-home-main-menu-start-building_3643769.png"));
		lblNewLabel.setBounds(57, 13, 202, 44);
		add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Tìm kiếm:");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(85, 131, 92, 35);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\Quanly\\src\\image\\search.png"));
		lblNewLabel_3.setBounds(404, 122, 48, 44);
		add(lblNewLabel_3);
		
		dataKhoanThu(khoanThus);
	}
	public void dataKhoanThu(ArrayList<DanhSachKhoanThu> khoanThus) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Tên khoản thu");
		model.addColumn("Bắt đầu");
		model.addColumn("kết thúc");
		model.addColumn("Số tiền");
		
		for(DanhSachKhoanThu danhSachKhoanThu: khoanThus) {
			model.addRow(new Object[] {"  " + danhSachKhoanThu.getTenKhoanThu(), danhSachKhoanThu.getBatDau(), danhSachKhoanThu.getKetThuc(), danhSachKhoanThu.getSoTien()});
		}
		table.setModel(model);
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("SansSerif", Font.PLAIN, 18));
		
		header.setPreferredSize(new Dimension(40, 40));
	}
	
	public void dataDongGop(ArrayList<DanhSachDongGop> dongGop) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Tên đóng góp");
		model.addColumn("Bắt đầu");
		model.addColumn("kết thúc");
		model.addColumn("Tổng số tiền");
		
		for(DanhSachDongGop danhSachDongGop: dongGop) {
			model.addRow(new Object[] {danhSachDongGop.getTenDongGop(), danhSachDongGop.getBatDau(), danhSachDongGop.getKetThuc(), danhSachDongGop.getTongTien()});
		}
		table.setModel(model);
	}
	
	public void searchKhoanThu(ArrayList<DanhSachKhoanThu> khoanThus) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Tên khoản thu");
		model.addColumn("Bắt đầu");
		model.addColumn("kết thúc");
		model.addColumn("Số tiền");
		
		for(DanhSachKhoanThu danhSachKhoanThu: khoanThus) {
			if(danhSachKhoanThu.getTenKhoanThu().toLowerCase().indexOf(textField.getText().toLowerCase()) >= 0) {
				model.addRow(new Object[] {danhSachKhoanThu.getTenKhoanThu(), danhSachKhoanThu.getBatDau(), danhSachKhoanThu.getKetThuc(), danhSachKhoanThu.getSoTien()});
				}
			
		}
		table.setModel(model);
	}
	public void searchDongGop(ArrayList<DanhSachDongGop> dongGop) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Tên khoản thu");
		model.addColumn("Bắt đầu");
		model.addColumn("kết thúc");
		model.addColumn("Số tiền");
		for(DanhSachDongGop danhSachDongGop: dongGop) {
			if(danhSachDongGop.getTenDongGop().toLowerCase().indexOf(textField.getText().toLowerCase()) >= 0) {
				model.addRow(new Object[] {danhSachDongGop.getTenDongGop(), danhSachDongGop.getBatDau(), danhSachDongGop.getKetThuc(), danhSachDongGop.getTongTien()});
				}
		}
		table.setModel(model);
	}
}
