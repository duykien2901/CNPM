package services;

import java.sql.SQLException;
import java.util.ArrayList;

import models.DanhSachDongGop;
import models.DanhSachKhoanThu;
import models.HoDongGop;
import models.HoNopTien;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
//		DanhSachKhoanThu ds = new DanhSachKhoanThu();
//		ds.setTenKhoanThu("Tien gas");
//		KhoanThuService ser = new KhoanThuService();
//		ArrayList<HoNopTien> hoDong = ser.getHoNopTien();
//		DongGopService dongGopService = new DongGopService();
//		DanhSachDongGop danhSachDongGop = new DanhSachDongGop();
//		danhSachDongGop.setIdDongGop(3);
//		ArrayList<HoDongGop> hoDongGops = dongGopService.getHoDongGop(danhSachDongGop);
//		for(HoDongGop hoDongGop : hoDongGops) {
//			System.out.println(hoDongGop.getTenHoKhau());
//		}
//		hoDongGops.get(0).setTienDongGop(2000000);
//		
//		dongGopService.capNhatHoDongGop(hoDongGops);
		DanhSachKhoanThu ds = new DanhSachKhoanThu();
		
		ds.setIdKhoanThu(4);
		KhoanThuService ser = new KhoanThuService();
		ArrayList<HoNopTien> hoNopTien = ser.getHoNopTien(ds);
		hoNopTien.get(2).setTrangThai(1);
		ser.capNhatHoNopTien(ds, hoNopTien);
		
	}

}
