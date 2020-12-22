package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
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
		table_1 = new JTable();
		table_1.setColumnSelectionAllowed(true);
		table_1.setCellSelectionEnabled(true);
		
		 chonSachKhoanThu = new DanhSachKhoanThu();
		 chonDanhSachDongGop = new DanhSachDongGop();
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 0, 827, 730);
		add(desktopPane);
		desktopPane.setBackground(new Color(255, 250, 240));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 146, 709, 340);
		desktopPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table_1);
		
		
		JLabel lblNewLabel = new JLabel("Tìm kiếm: ");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 17));
		lblNewLabel.setBounds(97, 94, 98, 39);
		desktopPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(198, 100, 230, 30);
		desktopPane.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Khoản thu", "Đóng góp"}));
		comboBox.setBounds(607, 98, 156, 30);
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
		desktopPane.add(comboBox);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setFont(new Font("SansSerif", Font.PLAIN, 17));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(String.valueOf(comboBox.getSelectedItem()).equals("Khoản thu")) {
					updateKhoanThu();
				} else {
					updateDongGop();
				}
			}
		});
		btnNewButton_1.setBounds(532, 516, 98, 28);
		desktopPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Hộ khẩu");
		btnNewButton.setBounds(665, 514, 98, 30);
		desktopPane.add(btnNewButton);
		// click update
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateHoKhau updateHoKhau = new UpdateHoKhau();
					if(String.valueOf(comboBox.getSelectedItem()).equals("Khoản thu")) {
						chonSachKhoanThu.setIdKhoanThu(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 0)))));
			            chonSachKhoanThu.setTenKhoanThu(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 1)));
			            chonSachKhoanThu.setBatDau(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
			            chonSachKhoanThu.setKetThuc(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
			            chonSachKhoanThu.setSoTien(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 4)))));
			            updateHoKhau.setId(1);
						updateHoKhau.danhSachKhoanThu = chonSachKhoanThu;
					} else {
						chonDanhSachDongGop.setIdDongGop(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 0)))));
						chonDanhSachDongGop.setTenDongGop(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 1)));
						chonDanhSachDongGop.setBatDau(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
						chonDanhSachDongGop.setKetThuc(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
						updateHoKhau.danhSachDongGop = chonDanhSachDongGop;
						updateHoKhau.setId(0);
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
		
		dataKhoanThu(khoanThus);
		
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
	public void dataKhoanThu(ArrayList<DanhSachKhoanThu> khoanThus) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("Tên khoản thu");
		model.addColumn("Bắt đầu");
		model.addColumn("kết thúc");
		model.addColumn("Số tiền");
		
		for(DanhSachKhoanThu danhSachKhoanThu: khoanThus) {
			model.addRow(new Object[] {danhSachKhoanThu.getIdKhoanThu(), danhSachKhoanThu.getTenKhoanThu(), danhSachKhoanThu.getBatDau(), danhSachKhoanThu.getKetThuc(), danhSachKhoanThu.getSoTien()});
		}
		table_1.setModel(model);
	}
	
	public void dataDongGop(ArrayList<DanhSachDongGop> dongGop) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("Tên đóng góp");
		model.addColumn("Bắt đầu");
		model.addColumn("kết thúc");
		
		for(DanhSachDongGop danhSachDongGop: dongGop) {
			model.addRow(new Object[] {danhSachDongGop.getIdDongGop(),danhSachDongGop.getTenDongGop(), danhSachDongGop.getBatDau(), danhSachDongGop.getKetThuc()});
		}
		table_1.setModel(model);
	}
	
	public void searchKhoanThu(ArrayList<DanhSachKhoanThu> khoanThus) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("Tên khoản thu");
		model.addColumn("Bắt đầu");
		model.addColumn("kết thúc");
		model.addColumn("Số tiền");
		
		for(DanhSachKhoanThu danhSachKhoanThu: khoanThus) {
			if(danhSachKhoanThu.getTenKhoanThu().toLowerCase().indexOf(textField.getText().toLowerCase()) >= 0) {
				model.addRow(new Object[] {danhSachKhoanThu.getIdKhoanThu(),danhSachKhoanThu.getTenKhoanThu(), danhSachKhoanThu.getBatDau(), danhSachKhoanThu.getKetThuc(), danhSachKhoanThu.getSoTien()});
				}
			
		}
		table_1.setModel(model);
	}
	public void searchDongGop(ArrayList<DanhSachDongGop> dongGop) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("Tên khoản thu");
		model.addColumn("Bắt đầu");
		model.addColumn("kết thúc");
		model.addColumn("Số tiền");
		
		for(DanhSachDongGop danhSachDongGop: dongGop) {
			if(danhSachDongGop.getTenDongGop().toLowerCase().indexOf(textField.getText().toLowerCase()) >= 0) {
				model.addRow(new Object[] {danhSachDongGop.getIdDongGop(),danhSachDongGop.getTenDongGop(), danhSachDongGop.getBatDau(), danhSachDongGop.getKetThuc(), danhSachDongGop.getTongTien()});
				}
		}
		table_1.setModel(model);
	}
	
	public void updateKhoanThu() {
		DanhSachKhoanThu danhSachKhoanThu = new DanhSachKhoanThu();
		danhSachKhoanThu.setIdKhoanThu(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 0)))));
		danhSachKhoanThu.setTenKhoanThu(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 1)));
		danhSachKhoanThu.setBatDau(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
		danhSachKhoanThu.setKetThuc(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
		danhSachKhoanThu.setSoTien(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 4)))));
		KhoanThuService khoanThuService = new KhoanThuService();
		khoanThuService.capNhatKhoanThu(danhSachKhoanThu);
	}
	
	public void updateDongGop() {
		DanhSachDongGop danhSachDongGop = new DanhSachDongGop();
		danhSachDongGop.setIdDongGop(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 0)))));
		danhSachDongGop.setTenDongGop(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 1)));
		danhSachDongGop.setBatDau(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
		danhSachDongGop.setKetThuc(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
		DongGopService dongGopService = new DongGopService();
		dongGopService.capNhatDongGop(danhSachDongGop);
	}
	
//	public DanhSachKhoanThu chonKhoanThu() {
//		DanhSachKhoanThu danhSachKhoanThu = new DanhSachKhoanThu();
//		danhSachKhoanThu.setIdKhoanThu(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 0)))));
//		danhSachKhoanThu.setTenKhoanThu(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 1)));
//		danhSachKhoanThu.setBatDau(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
//		danhSachKhoanThu.setKetThuc(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
//		danhSachKhoanThu.setSoTien(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 4)))));
//		return danhSachKhoanThu;
//	}
}
