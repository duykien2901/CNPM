package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import models.DanhSachDongGop;
import models.DanhSachKhoanThu;
import models.HoDongGop;
import models.HoNopTien;
import services.DongGopService;

public class ThongKeController {
	private ArrayList<DanhSachKhoanThu> khoanThus;
	private ArrayList<DanhSachDongGop> dongGop;
	private JTable table;
	private JTextField textField;
	private ArrayList<HoDongGop> hoDongGops;
	private ArrayList<HoNopTien> hoNopTiens;
	public int id;	
	
	public ThongKeController(ArrayList<DanhSachKhoanThu> khoanThus, ArrayList<DanhSachDongGop> dongGop, JTable table,
			JTextField textField, ArrayList<HoDongGop> hoDongGops, ArrayList<HoNopTien> hoNopTiens) {
		super();
		this.khoanThus = khoanThus;
		this.dongGop = dongGop;
		this.table = table;
		this.textField = textField;
		this.hoDongGops = hoDongGops;
		this.hoNopTiens = hoNopTiens;
	}
	
	public void dataKhoanThu(ArrayList<DanhSachKhoanThu> khoanThus) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Tên khoản thu");
		model.addColumn("Bắt đầu");
		model.addColumn("kết thúc");
		model.addColumn("Số tiền");
		for (DanhSachKhoanThu danhSachKhoanThu : khoanThus) {
			model.addRow(new Object[] { danhSachKhoanThu.getTenKhoanThu(), danhSachKhoanThu.getBatDau(),
					danhSachKhoanThu.getKetThuc(), danhSachKhoanThu.getSoTien() });
		}
		table.setModel(model);
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("SansSerif", Font.PLAIN, 18));
		header.setBackground(new Color(175, 238, 238));
		header.setPreferredSize(new Dimension(40, 40));
	}

	public void dataDongGop(ArrayList<DanhSachDongGop> dongGop) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Tên đóng góp");
		model.addColumn("Bắt đầu");
		model.addColumn("kết thúc");

		for (DanhSachDongGop danhSachDongGop : dongGop) {
			model.addRow(new Object[] { danhSachDongGop.getTenDongGop(), danhSachDongGop.getBatDau(),
					danhSachDongGop.getKetThuc() });
		}
		table.setModel(model);
	}

	public void searchKhoanThu(ArrayList<DanhSachKhoanThu> khoanThus) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Tên khoản thu");
		model.addColumn("Bắt đầu");
		model.addColumn("kết thúc");
		model.addColumn("Số tiền");

		for (DanhSachKhoanThu danhSachKhoanThu : khoanThus) {
			if (danhSachKhoanThu.getTenKhoanThu().toLowerCase().indexOf(textField.getText().toLowerCase()) >= 0) {
				model.addRow(new Object[] { danhSachKhoanThu.getTenKhoanThu(), danhSachKhoanThu.getBatDau(),
						danhSachKhoanThu.getKetThuc(), danhSachKhoanThu.getSoTien() });
			}

		}
		table.setModel(model);
	}

	public void searchDongGop(ArrayList<DanhSachDongGop> dongGop) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Tên khoản thu");
		model.addColumn("Bắt đầu");
		model.addColumn("kết thúc");
		for (DanhSachDongGop danhSachDongGop : dongGop) {
			if (danhSachDongGop.getTenDongGop().toLowerCase().indexOf(textField.getText().toLowerCase()) >= 0) {
				model.addRow(new Object[] { danhSachDongGop.getTenDongGop(), danhSachDongGop.getBatDau(),
						danhSachDongGop.getKetThuc() });
			}
		}
		table.setModel(model);
	}

	public void searchHo(ArrayList<HoDongGop> hoDongGops) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Id hộ");
		model.addColumn("Tên hộ");
		for (HoDongGop hoDongGop : hoDongGops) {
			if (hoDongGop.getTenHoKhau().toLowerCase().indexOf(textField.getText().toLowerCase()) >= 0) {
				model.addRow(new Object[] { hoDongGop.getIdHoKhau(), hoDongGop.getTenHoKhau() });
			}
		}
		table.setModel(model);
	}

	public void dataHo() {
		DongGopService dongGopService = new DongGopService();
		hoDongGops = new ArrayList<HoDongGop>();
		hoDongGops = dongGopService.getHoDongGop();

		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Id hộ");
		model.addColumn("Tên hộ");
		for (HoDongGop hoDongGop : hoDongGops) {
			model.addRow(new Object[] { hoDongGop.getIdHoKhau(), hoDongGop.getTenHoKhau() });
		}

		table.setModel(model);
	}

}
