package controller;

import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import models.DanhSachDongGop;
import models.DanhSachKhoanThu;
import services.DongGopService;
import services.KhoanThuService;

public class JpanelAddController {
	JTextField textField_1;
	JTextField textField_2;
	JDateChooser dateChooser;
	JDateChooser dateChooser_1;
	JComboBox comboBox;
	
	public JpanelAddController(JTextField textField_1, JTextField textField_2, JDateChooser dateChooser, JDateChooser dateChooser_1, JComboBox comboBox) {
		this.textField_1 = textField_1;
		this.textField_2 = textField_2;
		this.dateChooser = dateChooser;
		this.dateChooser_1 = dateChooser_1;
		this.comboBox = comboBox;
	}

	public JTextField getTextField_1() {
		return textField_1;
	}

	public void setTextField_1(JTextField textField_1) {
		this.textField_1 = textField_1;
	}

	public JTextField getTextField_2() {
		return textField_2;
	}

	public void setTextField_2(JTextField textField_2) {
		this.textField_2 = textField_2;
	}

	public JDateChooser getDateChooser() {
		return dateChooser;
	}

	public void setDateChooser(JDateChooser dateChooser) {
		this.dateChooser = dateChooser;
	}

	public JDateChooser getDateChooser_1() {
		return dateChooser_1;
	}

	public void setDateChooser_1(JDateChooser dateChooser_1) {
		this.dateChooser_1 = dateChooser_1;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
	}

	public void insertDb() {
//		System.out.println(dateChooser.getDate().before(dateChooser_1.getDate()));
		if (textField_1.getText().equals("") || textField_2.getText().equals("") || dateChooser.getDate().equals("")
				|| dateChooser_1.getDate().equals("")) {
			JOptionPane.showMessageDialog(null, "Chưa điền đủ thông tin", "Message", JOptionPane.ERROR_MESSAGE);

		} else if (String.valueOf(comboBox.getSelectedItem()).equals("Khoản thu")) {
			try {
				System.out.println(Integer.parseInt(textField_2.getText()));

			} catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "Sai định dạng", "Message", JOptionPane.ERROR_MESSAGE);
			}
			if (Integer.parseInt(textField_2.getText()) <= 0) {
				JOptionPane.showMessageDialog(null, "Tiền phải lớn hơn 0", "Message", JOptionPane.ERROR_MESSAGE);
			} else if (!dateChooser.getDate().before(dateChooser_1.getDate())) {
				JOptionPane.showMessageDialog(null, "Nhập lại ngày", "Message", JOptionPane.ERROR_MESSAGE);
			} else {
				DanhSachKhoanThu kt = new DanhSachKhoanThu();
				kt.setTenKhoanThu(textField_1.getText());
				kt.setSoTien(Integer.parseInt(textField_2.getText()));
				kt.setBatDau(new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate()));
				kt.setKetThuc(new SimpleDateFormat("dd/MM/yyyy").format(dateChooser_1.getDate()));
				KhoanThuService khoanThuService = new KhoanThuService();
				khoanThuService.themKhoanThu(kt);
				khoanThuService.themHoKhau(kt, khoanThuService.getHoNopTien());
				JOptionPane.showMessageDialog(null, "Thêm thành công");
			}

		} else if (!dateChooser.getDate().before(dateChooser_1.getDate())) {
			JOptionPane.showMessageDialog(null, "Nhập lại ngày", "Message", JOptionPane.ERROR_MESSAGE);
		} else {
			DanhSachDongGop dg = new DanhSachDongGop();
			dg.setTenDongGop(textField_1.getText());
			dg.setBatDau(new SimpleDateFormat("dd/MM/yyyy").format(dateChooser.getDate()));
			dg.setKetThuc(new SimpleDateFormat("dd/MM/yyyy").format(dateChooser_1.getDate()));
			DongGopService dongGopService = new DongGopService();
			dongGopService.themDongGop(dg);
			dongGopService.themHoKhau(dg, dongGopService.getHoDongGop());
			JOptionPane.showMessageDialog(null, "Thêm thành công");
		}
	}
}
