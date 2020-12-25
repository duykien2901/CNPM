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

public class JpanelMainController {
	private JTable table;
	private JTextField textField;
	public JpanelMainController(JTable table, JTextField textField) {
		this.table = table;
		this.textField = textField;
	}
	public void dataKhoanThu(ArrayList<DanhSachKhoanThu> khoanThus) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Tên khoản thu");
		model.addColumn("Bắt đầu");
		model.addColumn("kết thúc");
		model.addColumn("Số tiền");

		for (DanhSachKhoanThu danhSachKhoanThu : khoanThus) {
			model.addRow(new Object[] { "  " + danhSachKhoanThu.getTenKhoanThu(), "  " + danhSachKhoanThu.getBatDau(),
					"  " + danhSachKhoanThu.getKetThuc(),"  " +  danhSachKhoanThu.getSoTien() });
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
			model.addRow(new Object[] { "  " + danhSachDongGop.getTenDongGop(), "  " + danhSachDongGop.getBatDau(),
					"  " + danhSachDongGop.getKetThuc() });
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
				model.addRow(
						new Object[] { "  " + danhSachKhoanThu.getTenKhoanThu(), "  " + danhSachKhoanThu.getBatDau(),
								"  " + danhSachKhoanThu.getKetThuc(), "  " + danhSachKhoanThu.getSoTien() });
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
				model.addRow(new Object[] {"  " +  danhSachDongGop.getTenDongGop(),"  " +  danhSachDongGop.getBatDau(),
						"  " + danhSachDongGop.getKetThuc() });
			}
		}
		table.setModel(model);
	}
}
