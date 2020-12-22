package models;

import java.sql.Date;

public class DanhSachDongGop {
	private int idDongGop;
	private String tenDongGop;
	private String batDau;
	private String ketThuc;
	private int tongTien;
	
	public DanhSachDongGop() {
		// TODO Auto-generated constructor stub
	}
	
	public DanhSachDongGop(int idDongGop, String tenDongGop, String batDau, String ketThuc, int tongTien) {
		this.idDongGop = idDongGop;
		this.tenDongGop = tenDongGop;
		this.batDau = batDau;
		this.ketThuc = ketThuc;
		this.tongTien = tongTien;
	}
	
	public int getIdDongGop() {
		return idDongGop;
	}
	public void setIdDongGop(int idDongGop) {
		this.idDongGop = idDongGop;
	}
	public String getTenDongGop() {
		return tenDongGop;
	}
	public void setTenDongGop(String tenDongGop) {
		this.tenDongGop = tenDongGop;
	}
	public String getBatDau() {
		return batDau;
	}

	public void setBatDau(String batDau) {
		this.batDau = batDau;
	}

	public String getKetThuc() {
		return ketThuc;
	}

	public void setKetThuc(String ketThuc) {
		this.ketThuc = ketThuc;
	}

	public int getTongTien() {
		return tongTien;
	}
	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}
	
	
	
}
