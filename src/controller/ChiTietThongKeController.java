package controller;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.DanhSachDongGop;
import models.DanhSachKhoanThu;
import models.HoDongGop;
import models.HoKhau;
import models.HoNopTien;
import models.KhoanTien;
import services.DongGopService;
import services.KhoanThuService;

public class ChiTietThongKeController {
	private JTable table;
	public HoKhau hoKhau;
	public KhoanTien khoanTien;

	public ChiTietThongKeController(JTable table, HoKhau hoKhau, KhoanTien khoanTien) {
		super();
		this.table = table;
		this.hoKhau = hoKhau;
		this.khoanTien = khoanTien;
	}

//	public void setData() {
//		ArrayList<DanhSachDongGop> danhSachDongGops = new ArrayList<DanhSachDongGop>();
//		ArrayList<DanhSachKhoanThu> danhSachKhoanThus = new ArrayList<DanhSachKhoanThu>();
//		
//		ArrayList<HoDongGop> hoDongGops = new ArrayList<HoDongGop>();
//		ArrayList<HoNopTien> hoNopTiens = new ArrayList<HoNopTien>();
//		
//		ArrayList<KhoanTien> khoanTiens = new ArrayList<KhoanTien>();
//		
//		DongGopService dongGopService = new DongGopService();
//		KhoanThuService khoanThuService = new KhoanThuService();
//		
//		danhSachDongGops = dongGopService.getDanhSach();
//		danhSachKhoanThus = khoanThuService.getDanhSach();
//		
//		for(DanhSachDongGop danhSachDongGop: danhSachDongGops) {
//			hoDongGops = dongGopService.getHoDongGop(danhSachDongGop);
//			for(HoDongGop hoDongGop : hoDongGops) {
//				if(hoKhau.getId() == hoDongGop.getIdHoKhau()) {
//					KhoanTien khoanTien = new KhoanTien();
//					khoanTien.setTenKhoan(danhSachDongGop.getTenDongGop());
//					khoanTien.setSoTien(hoDongGop.getTienDongGop());
//					khoanTiens.add(khoanTien);
//				}
//			}
//		}
//		
//		for(DanhSachKhoanThu danhSachKhoanThu: danhSachKhoanThus) {
//			hoNopTiens = khoanThuService.getHoNopTien(danhSachKhoanThu);
//			for(HoNopTien hoNopTien: hoNopTiens) {
//				if(hoKhau.getId() == hoNopTien.getIdHoKhau()) {
//					if(hoNopTien.getTrangThai() == 1) {
//						KhoanTien khoanTien = new KhoanTien();
//						khoanTien.setSoTien(danhSachKhoanThu.getSoTien() * hoNopTien.getSoNhanKhau());
//						khoanTien.setTenKhoan(danhSachKhoanThu.getTenKhoanThu());
//						khoanTiens.add(khoanTien);
//					}else {
//						KhoanTien khoanTien = new KhoanTien();
//						khoanTien.setSoTien(0);
//						khoanTien.setTenKhoan(danhSachKhoanThu.getTenKhoanThu());
//						khoanTiens.add(khoanTien);
//					}
//				}
//			}
//		}
//		
//		
//		DefaultTableModel model = new DefaultTableModel();
//		model.addColumn("Tên khoản");
//		model.addColumn("Số tiền");
//		for (KhoanTien khoanTien : khoanTiens) {
//			model.addRow(new Object[] { khoanTien.getTenKhoan(), khoanTien.getSoTien() });
//		}
//		table.setModel(model);
//	}
}
