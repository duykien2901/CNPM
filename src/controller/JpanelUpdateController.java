package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import models.DanhSachDongGop;
import models.DanhSachKhoanThu;
import services.DongGopService;
import services.KhoanThuService;

public class JpanelUpdateController {
	private JTable table_1;
	private JTextField textField;
	
	public JpanelUpdateController(JTable table, JTextField textField) {
		this.table_1 = table;
		this.textField = textField;
	}
	public void dataKhoanThu(ArrayList<DanhSachKhoanThu> khoanThus) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("Tên khoản thu");
		model.addColumn("Bắt đầu");
		model.addColumn("kết thúc");
		model.addColumn("Số tiền");
		
		for(DanhSachKhoanThu danhSachKhoanThu: khoanThus) {
			model.addRow(new Object[] {danhSachKhoanThu.getIdKhoanThu(), danhSachKhoanThu.getTenKhoanThu(),danhSachKhoanThu.getBatDau(),  danhSachKhoanThu.getKetThuc(),  danhSachKhoanThu.getSoTien()});
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
			model.addRow(new Object[] {danhSachDongGop.getIdDongGop(), danhSachDongGop.getTenDongGop(), danhSachDongGop.getBatDau(),danhSachDongGop.getKetThuc()});
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
				model.addRow(new Object[] {danhSachKhoanThu.getIdKhoanThu(), danhSachKhoanThu.getTenKhoanThu(), danhSachKhoanThu.getBatDau(), danhSachKhoanThu.getKetThuc(), danhSachKhoanThu.getSoTien()});
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
	
	public void updateKhoanThu() throws ParseException {
		if(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 1))).equals("") ||
				String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 2))).equals("") ||
				String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 3))).equals("") ||
				String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 4))).equals("")
				) {
			JOptionPane.showMessageDialog(null, "Có phần tử trống");
		} else {
			Date date1 =  new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
			Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
			try {
				System.out.println(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 4)))));

			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Sai định dạng", "Message", JOptionPane.ERROR_MESSAGE);
			}
			if (Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 4)))) <= 0) {
				JOptionPane.showMessageDialog(null, "Tiền phải lớn hơn 0", "Message", JOptionPane.ERROR_MESSAGE);
			} else if (!date1.before(date2)) {
				JOptionPane.showMessageDialog(null, "Nhập lại ngày", "Message", JOptionPane.ERROR_MESSAGE);
			} else {
			DanhSachKhoanThu danhSachKhoanThu = new DanhSachKhoanThu();
			danhSachKhoanThu.setIdKhoanThu(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 0)))));
			danhSachKhoanThu.setTenKhoanThu(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 1)));
			danhSachKhoanThu.setBatDau(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
			danhSachKhoanThu.setKetThuc(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
			danhSachKhoanThu.setSoTien(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 4)))));
			KhoanThuService khoanThuService = new KhoanThuService();
			khoanThuService.capNhatKhoanThu(danhSachKhoanThu);
			JOptionPane.showMessageDialog(null, "Sửa mới thành công");
			}
		}
		
	}
	
	public void updateDongGop() throws ParseException {
		if(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 1))).equals("") ||
				String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 2))).equals("") ||
				String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 3))).equals("") 
				) {
			JOptionPane.showMessageDialog(null, "Có phần tử trống", "Message", JOptionPane.ERROR_MESSAGE);
		} else {
			Date date1 =  new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
			Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
			if (!date1.before(date2)) {
				JOptionPane.showMessageDialog(null, "Nhập lại ngày", "Message", JOptionPane.ERROR_MESSAGE);
			}else {
				DanhSachDongGop danhSachDongGop = new DanhSachDongGop();
				danhSachDongGop.setIdDongGop(Integer.parseInt(String.valueOf((table_1.getValueAt(table_1.getSelectedRow(), 0)))));
				danhSachDongGop.setTenDongGop(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 1)));
				danhSachDongGop.setBatDau(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 2)));
				danhSachDongGop.setKetThuc(String.valueOf(table_1.getValueAt(table_1.getSelectedRow(), 3)));
				DongGopService dongGopService = new DongGopService();
				dongGopService.capNhatDongGop(danhSachDongGop);
				JOptionPane.showMessageDialog(null, "Sửa thành công");
			}
		
		}
		
	}
}
