package models;

import java.sql.Date;

public class DanhSachKhoanThu {
	private int idKhoanThu;
	private String tenKhoanThu;
	private String batDau;
	private String ketThuc;
	private int soTien;
	
	public DanhSachKhoanThu() {
		// TODO Auto-generated constructor stub
	}
	
	public DanhSachKhoanThu(String tenKhoanThu, String batDau, String ketThuc, int soTien) {
		setTenKhoanThu(tenKhoanThu);
		setBatDau(batDau);
		setKetThuc(ketThuc);
		setSoTien(soTien);
	}
	
	public int getIdKhoanThu() {
		return idKhoanThu;
	}
	public void setIdKhoanThu(int idKhoanThu) {
		this.idKhoanThu = idKhoanThu;
	}
	public String getTenKhoanThu() {
		return tenKhoanThu;
	}
	public void setTenKhoanThu(String tenKhoanThu) {
		this.tenKhoanThu = tenKhoanThu;
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
	public int getSoTien() {
		return soTien;
	}
	public void setSoTien(int soTien) {
		this.soTien = soTien;
	}
	

	
}
