package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import View.UpdateHoKhau;
import models.DanhSachDongGop;
import models.DanhSachKhoanThu;
import models.HoDongGop;
import models.HoNopTien;
import services.DongGopService;
import services.KhoanThuService;

public class UpdateHoKhauController {
	private JTable table_1;
	private ArrayList<HoNopTien> hoNopTiens;
	private ArrayList<HoDongGop> hoDongGops;
	private DanhSachDongGop danhSachDongGop;
	private DanhSachKhoanThu danhSachKhoanThu;

	public UpdateHoKhauController(JTable table, ArrayList<HoNopTien> hoNopTiens, ArrayList<HoDongGop> hoDongGops,
			DanhSachDongGop danhSachDongGop, DanhSachKhoanThu danhSachKhoanThu) {
		// TODO Auto-generated constructor stub
		this.table_1 = table;
		this.hoNopTiens = hoNopTiens;
		this.hoDongGops = hoDongGops;
		this.danhSachDongGop = danhSachDongGop;
		this.danhSachKhoanThu = danhSachKhoanThu;
	}

	public void dataHoNopTien() {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID hộ khẩu");
		model.addColumn("Tên hộ khẩu");
		model.addColumn("Số nhân khẩu");
		model.addColumn("Trạng thái");

		for (HoNopTien hoNopTien : hoNopTiens) {
			model.addRow(new Object[] { hoNopTien.getIdHoKhau(), hoNopTien.getTenHoKhau(), hoNopTien.getSoNhanKhau(),
					hoNopTien.getTrangThai() });
		}
		table_1.setModel(model);
	}

	public void dataDongGop() {
		hoDongGops = new ArrayList<HoDongGop>();
		DongGopService dongGopService = new DongGopService();
		hoDongGops = dongGopService.getHoDongGop(danhSachDongGop);
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID hộ khẩu");
		model.addColumn("Tên hộ khẩu");
		model.addColumn("Tiền đóng góp");

		for (HoDongGop hoDongGop : hoDongGops) {
			model.addRow(
					new Object[] { hoDongGop.getIdHoKhau(), hoDongGop.getTenHoKhau(), hoDongGop.getTienDongGop() });
		}
		table_1.setModel(model);
	}

	public void updateHoDongGop() {
		int i = 0;
		ArrayList<Integer> checkArrayList = new ArrayList<Integer>();
		DongGopService dongGopService = new DongGopService();
		for (HoDongGop hoDongGop : hoDongGops) {
			try {
				System.out.println(
						Integer.parseInt(String.valueOf((table_1.getValueAt(hoDongGops.indexOf(hoDongGop), 2)))));
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Sai định dạng trạng thái", "Message", JOptionPane.ERROR_MESSAGE);
			}
			if (Integer.parseInt(String.valueOf((table_1.getValueAt(hoDongGops.indexOf(hoDongGop), 2)))) < 0) {
				i++;
				checkArrayList.add(hoDongGops.indexOf(hoDongGop));
			}
			hoDongGop.setTienDongGop(
					Integer.parseInt(String.valueOf((table_1.getValueAt(hoDongGops.indexOf(hoDongGop), 2)))));
		}
		if (i != 0) {
			String string = "";
			for (Integer k : checkArrayList) {
				string += String.valueOf(k + 1) + ",";
			}
			JOptionPane.showMessageDialog(null, "Sai Trạng thái vị trí: " + string, "Message",
					JOptionPane.ERROR_MESSAGE);
		} else {
			dongGopService.capNhatHoDongGop(danhSachDongGop, hoDongGops);
			JOptionPane.showMessageDialog(null, "Cập nhật thành công");
		}

	}

	public void updateHoNopTien() {
		int i = 0;
		ArrayList<Integer> checkArrayList = new ArrayList<Integer>();
		KhoanThuService khoanThuService = new KhoanThuService();

		for (HoNopTien hoNopTien : hoNopTiens) {
			try {
				System.out.println(
						Integer.parseInt(String.valueOf((table_1.getValueAt(hoNopTiens.indexOf(hoNopTien), 3)))));
			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Sai định dạng trạng thái", "Message", JOptionPane.ERROR_MESSAGE);
			}
			if (Integer.parseInt(String.valueOf((table_1.getValueAt(hoNopTiens.indexOf(hoNopTien), 3)))) != 0
					&& Integer.parseInt(String.valueOf((table_1.getValueAt(hoNopTiens.indexOf(hoNopTien), 3)))) != 1) {

				i++;
				checkArrayList.add(hoNopTiens.indexOf(hoNopTien));
			}
			hoNopTien.setTrangThai(
					Integer.parseInt(String.valueOf((table_1.getValueAt(hoNopTiens.indexOf(hoNopTien), 3)))));

		}
		if (i != 0) {
			String string = "";
			for (Integer k : checkArrayList) {
				string += String.valueOf(k + 1) + ",";
			}
			JOptionPane.showMessageDialog(null, "Sai Trạng thái vị trí: " + string, "Message",
					JOptionPane.ERROR_MESSAGE);
		} else {
			khoanThuService.capNhatHoNopTien(danhSachKhoanThu, hoNopTiens);
			JOptionPane.showMessageDialog(null, "Cập nhật thành công");
		}

	}
}
