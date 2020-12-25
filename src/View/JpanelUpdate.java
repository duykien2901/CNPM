package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.JpanelUpdateController;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import image.*;
import models.DanhSachDongGop;
import models.DanhSachKhoanThu;
import services.DongGopService;
import services.KhoanThuService;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;
public class JpanelUpdate extends JPanel {
	public static UpdateHoKhau updateHoKhau;
	private JTable table;
	private JDesktopPane desktopPane;
	public JTable table_1;
	private JTextField textField;
	private ArrayList<DanhSachKhoanThu> khoanThus;
	private ArrayList<DanhSachDongGop> dongGop;
	public static DanhSachDongGop chonDanhSachDongGop;
	public static DanhSachKhoanThu chonSachKhoanThu;
	private JComboBox comboBox;
	/**
	 * Create the panel.
	 */
	public JpanelUpdate() {
		setBounds(337, 0, 827, 742);
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(new Color(255, 250, 240));
		setLayout(null);
		
		KhoanThuService khoanThuService = new KhoanThuService();
		khoanThus = new ArrayList<DanhSachKhoanThu>();
		khoanThus = khoanThuService.getDanhSach();
		
		DongGopService dongGopService = new DongGopService();
		dongGop = new ArrayList<DanhSachDongGop>();
		dongGop = dongGopService.getDanhSach();
		
		textField = new JTextField();
		table_1 = new JTable();
		table_1.setColumnSelectionAllowed(true);
		table_1.setCellSelectionEnabled(true);
		table_1.setSelectionBackground(new Color(224, 255, 255));
		table_1.setFont(new Font("SansSerif", Font.PLAIN, 17));
		table_1.setBackground(new Color(255, 248, 220));
		table_1.setAutoCreateRowSorter(true);
		table_1.setRowHeight(30);
		
		JTableHeader header = table_1.getTableHeader();
		header.setFont(new Font("SansSerif", Font.PLAIN, 18));
		header.setBackground(new Color(175, 238, 238));
		header.setPreferredSize(new Dimension(40, 40));
		
		chonSachKhoanThu = new DanhSachKhoanThu();
		chonDanhSachDongGop = new DanhSachDongGop();
		
		JpanelUpdateController jpanelUpdateController = new JpanelUpdateController(table_1, textField);
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 827, 742);
		add(desktopPane);
		desktopPane.setBackground(new Color(255, 250, 240));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 215, 709, 340);
		desktopPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table_1);
		
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm: ");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 19));
		lblNewLabel.setBounds(54, 156, 98, 30);
		desktopPane.add(lblNewLabel);
		
		
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (String.valueOf(comboBox.getSelectedItem()).equals("Khoản thu")) {
					jpanelUpdateController.searchKhoanThu(khoanThus);
				} else {
					jpanelUpdateController.searchDongGop(dongGop);
				}
			}
		});
		textField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		textField.setFont(new Font("SansSerif", Font.PLAIN, 17));
		textField.setBackground(new Color(255, 250, 240));
		textField.setBounds(146, 158, 230, 30);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Khoản thu", "Đóng góp"}));
		comboBox.setBounds(607, 156, 156, 39);
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(String.valueOf(comboBox.getSelectedItem()).equals("Khoản thu")) {
					jpanelUpdateController.dataDongGop(dongGop);
				} else {
					jpanelUpdateController.dataKhoanThu(khoanThus);
				}
			}
		});
		desktopPane.add(comboBox);
		
		JButton btnNewButton_1 = new JButton("Cập nhật");
		btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 17));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(comboBox.getSelectedItem()).equals("Khoản thu")) {
					try {
						jpanelUpdateController.updateKhoanThu();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					try {
						jpanelUpdateController.updateDongGop();
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setBounds(531, 589, 100, 39);
		desktopPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Hộ khẩu");
		btnNewButton.setBounds(665, 589, 98, 39);
		desktopPane.add(btnNewButton);
		// click update
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateHoKhau updateHoKhau;
					if(String.valueOf(comboBox.getSelectedItem()).equals("Khoản thu")) {
						chonSachKhoanThu.setIdKhoanThu(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 0)))));
			            chonSachKhoanThu.setTenKhoanThu(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 1)));
			            chonSachKhoanThu.setBatDau(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
			            chonSachKhoanThu.setKetThuc(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
			            chonSachKhoanThu.setSoTien(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 4)))));
			            updateHoKhau = new UpdateHoKhau(chonSachKhoanThu.getIdKhoanThu(), 1);
//			            updateHoKhau.setId(1);
//			            updateHoKhau.danhSachKhoanThu = chonSachKhoanThu;
//			            updateHoKhau.dataHoNopTien();
						
					} else {
						chonDanhSachDongGop.setIdDongGop(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 0)))));
						chonDanhSachDongGop.setTenDongGop(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 1)));
						chonDanhSachDongGop.setBatDau(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
						chonDanhSachDongGop.setKetThuc(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
						updateHoKhau = new UpdateHoKhau(chonDanhSachDongGop.getIdDongGop(), 0);
//						updateHoKhau.danhSachDongGop = chonDanhSachDongGop;
//						updateHoKhau.dataDongGop();
//						updateHoKhau.setId(0);
					}
					try {
						Thread.sleep(100);
					} catch (Exception e2) {
						// TODO: handle exception
					}
					desktopPane.add(updateHoKhau);
					updateHoKhau.setVisible(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 17));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\Quanly\\src\\image\\search.png"));
		lblNewLabel_1.setBounds(377, 156, 55, 30);
		desktopPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Cập nhật");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\image\\iconfinder_renew_4168571.png"));
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblNewLabel_2.setBounds(54, 28, 214, 55);
		desktopPane.add(lblNewLabel_2);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(comboBox.getSelectedItem()).equals("Khoản thu")) {
					jpanelUpdateController.dataKhoanThu(khoanThus);
				} else {
					
					jpanelUpdateController.dataDongGop(dongGop);
				}
			}
		});
		btnNewButton_2.setBackground(new Color(102, 205, 170));
		btnNewButton_2.setIcon(new ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\Quanly\\src\\image\\iconfinder_refresh_134221.png"));
		btnNewButton_2.setBounds(708, 28, 55, 44);
		desktopPane.add(btnNewButton_2);
		
		
		jpanelUpdateController.dataKhoanThu(khoanThus);
		
//		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
//			@Override
//	        public void valueChanged(ListSelectionEvent event) {
//	            // do some actions here, for example
//	            // print first column value from selected row
//				if (event.getValueIsAdjusting()) {
//			        return;
//			     }
//	           
//	            chonSachKhoanThu.setIdKhoanThu(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 0)))));
//	            chonSachKhoanThu.setTenKhoanThu(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 1)));
//	            chonSachKhoanThu.setBatDau(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
//	            chonSachKhoanThu.setKetThuc(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
//	            chonSachKhoanThu.setSoTien(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 4)))));
//	            
//	        }
//	    });
		}
//	public void dataKhoanThu(ArrayList<DanhSachKhoanThu> khoanThus) {
//		DefaultTableModel model = new DefaultTableModel();
//		model.addColumn("id");
//		model.addColumn("Tên khoản thu");
//		model.addColumn("Bắt đầu");
//		model.addColumn("kết thúc");
//		model.addColumn("Số tiền");
//		
//		for(DanhSachKhoanThu danhSachKhoanThu: khoanThus) {
//			model.addRow(new Object[] {danhSachKhoanThu.getIdKhoanThu(), danhSachKhoanThu.getTenKhoanThu(),danhSachKhoanThu.getBatDau(),  danhSachKhoanThu.getKetThuc(),  danhSachKhoanThu.getSoTien()});
//		}
//		table_1.setModel(model);
//	}
//	
//	public void dataDongGop(ArrayList<DanhSachDongGop> dongGop) {
//		DefaultTableModel model = new DefaultTableModel();
//		model.addColumn("id");
//		model.addColumn("Tên đóng góp");
//		model.addColumn("Bắt đầu");
//		model.addColumn("kết thúc");
//		
//		for(DanhSachDongGop danhSachDongGop: dongGop) {
//			model.addRow(new Object[] {danhSachDongGop.getIdDongGop(), danhSachDongGop.getTenDongGop(), danhSachDongGop.getBatDau(),danhSachDongGop.getKetThuc()});
//		}
//		table_1.setModel(model);
//	}
//	
//	public void searchKhoanThu(ArrayList<DanhSachKhoanThu> khoanThus) {
//		DefaultTableModel model = new DefaultTableModel();
//		model.addColumn("id");
//		model.addColumn("Tên khoản thu");
//		model.addColumn("Bắt đầu");
//		model.addColumn("kết thúc");
//		model.addColumn("Số tiền");
//		
//		for(DanhSachKhoanThu danhSachKhoanThu: khoanThus) {
//			if(danhSachKhoanThu.getTenKhoanThu().toLowerCase().indexOf(textField.getText().toLowerCase()) >= 0) {
//				model.addRow(new Object[] {danhSachKhoanThu.getIdKhoanThu(), danhSachKhoanThu.getTenKhoanThu(), danhSachKhoanThu.getBatDau(), danhSachKhoanThu.getKetThuc(), danhSachKhoanThu.getSoTien()});
//				}
//			
//		}
//		table_1.setModel(model);
//	}
//	public void searchDongGop(ArrayList<DanhSachDongGop> dongGop) {
//		DefaultTableModel model = new DefaultTableModel();
//		model.addColumn("id");
//		model.addColumn("Tên khoản thu");
//		model.addColumn("Bắt đầu");
//		model.addColumn("kết thúc");
//		model.addColumn("Số tiền");
//		
//		for(DanhSachDongGop danhSachDongGop: dongGop) {
//			if(danhSachDongGop.getTenDongGop().toLowerCase().indexOf(textField.getText().toLowerCase()) >= 0) {
//				model.addRow(new Object[] {danhSachDongGop.getIdDongGop(),danhSachDongGop.getTenDongGop(), danhSachDongGop.getBatDau(), danhSachDongGop.getKetThuc(), danhSachDongGop.getTongTien()});
//				}
//		}
//		table_1.setModel(model);
//	}
//	
//	public void updateKhoanThu() throws ParseException {
//		if(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 1))).equals("") ||
//				String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 2))).equals("") ||
//				String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 3))).equals("") ||
//				String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 4))).equals("")
//				) {
//			JOptionPane.showMessageDialog(null, "Có phần tử trống");
//		} else {
//			Date date1 =  new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
//			Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
//			try {
//				System.out.println(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 4)))));
//
//			} catch (Exception e) {
//				// TODO: handle exception
//				JOptionPane.showMessageDialog(null, "Sai định dạng", "Message", JOptionPane.ERROR_MESSAGE);
//			}
//			if (Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 4)))) <= 0) {
//				JOptionPane.showMessageDialog(null, "Tiền phải lớn hơn 0", "Message", JOptionPane.ERROR_MESSAGE);
//			} else if (!date1.before(date2)) {
//				JOptionPane.showMessageDialog(null, "Nhập lại ngày", "Message", JOptionPane.ERROR_MESSAGE);
//			} else {
//			DanhSachKhoanThu danhSachKhoanThu = new DanhSachKhoanThu();
//			danhSachKhoanThu.setIdKhoanThu(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 0)))));
//			danhSachKhoanThu.setTenKhoanThu(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 1)));
//			danhSachKhoanThu.setBatDau(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
//			danhSachKhoanThu.setKetThuc(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
//			danhSachKhoanThu.setSoTien(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 4)))));
//			KhoanThuService khoanThuService = new KhoanThuService();
//			khoanThuService.capNhatKhoanThu(danhSachKhoanThu);
//			JOptionPane.showMessageDialog(null, "Sửa mới thành công");
//			}
//		}
//		
//	}
//	
//	public void updateDongGop() throws ParseException {
//		if(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 1))).equals("") ||
//				String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 2))).equals("") ||
//				String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 3))).equals("") 
//				) {
//			JOptionPane.showMessageDialog(null, "Có phần tử trống", "Message", JOptionPane.ERROR_MESSAGE);
//		} else {
//			Date date1 =  new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
//			Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
//			if (!date1.before(date2)) {
//				JOptionPane.showMessageDialog(null, "Nhập lại ngày", "Message", JOptionPane.ERROR_MESSAGE);
//			}else {
//				DanhSachDongGop danhSachDongGop = new DanhSachDongGop();
//				danhSachDongGop.setIdDongGop(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 0)))));
//				danhSachDongGop.setTenDongGop(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 1)));
//				danhSachDongGop.setBatDau(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
//				danhSachDongGop.setKetThuc(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
//				DongGopService dongGopService = new DongGopService();
//				dongGopService.capNhatDongGop(danhSachDongGop);
//				JOptionPane.showMessageDialog(null, "Sửa thành công");
//			}
//		
//		}
//		
//	}
}
