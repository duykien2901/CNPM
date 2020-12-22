package services;

import java.sql.SQLException;
import java.util.ArrayList;

import models.DanhSachKhoanThu;
import models.HoNopTien;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		DanhSachKhoanThu ds = new DanhSachKhoanThu();
		ds.setTenKhoanThu("Ung ho mien Trung");
		KhoanThuService ser = new KhoanThuService();
//		ser.themKhoanThu(ds);
		ArrayList<HoNopTien> hoNopTien = ser.getHoNopTien();
		ser.themHoKhau(ds, hoNopTien);
	}

}
