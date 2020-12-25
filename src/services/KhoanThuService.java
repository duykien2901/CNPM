package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import models.DanhSachKhoanThu;
import models.HoNopTien;

public class KhoanThuService {

	public boolean themKhoanThu(DanhSachKhoanThu dsKhoanThu) {
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlInsert = "INSERT INTO khoan_thu(ten_khoan_thu, ngay_bat_dau, ngay_ket_thuc, muc_thu)" 
	                + " values (?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
			preparedStatement.setString(1, dsKhoanThu.getTenKhoanThu());
			preparedStatement.setString(2, dsKhoanThu.getBatDau());
			preparedStatement.setString(3, dsKhoanThu.getKetThuc());
			preparedStatement.setInt(4, dsKhoanThu.getSoTien());
			preparedStatement.execute();
			
			preparedStatement.close();
	        connection.close();
		}  catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		
		return true;
	}
	
	public void themHoKhau(DanhSachKhoanThu dsKhoanThu, ArrayList<HoNopTien> hoNopTien) {
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlSearch = "select ID from khoan_thu where ten_khoan_thu = " + "'" + dsKhoanThu.getTenKhoanThu() + "'";
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSearch);
			ResultSet rs = preparedStatement.executeQuery();
			rs.first();
			int idKhoanThu = rs.getInt("ID");
			String sqlInsert = "INSERT INTO quan_ly_thu_phi(id_khoanthu, id_hokhau, so_tien_thu, trang_thai)"
					+ " values (?, ?, ?, ?)";
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
			for(int i = 0; i < hoNopTien.size(); i++) {
				preparedStatement.setInt(1, idKhoanThu);
				preparedStatement.setInt(2, hoNopTien.get(i).getIdHoKhau());
				preparedStatement.setInt(3, hoNopTien.get(i).getSoNhanKhau() * dsKhoanThu.getSoTien());
				preparedStatement.setInt(4, hoNopTien.get(i).getTrangThai());
				
				preparedStatement.execute();
			}
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<DanhSachKhoanThu> getDanhSach() {
		ArrayList<DanhSachKhoanThu> dsKhoanThu = new ArrayList<DanhSachKhoanThu>();
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlSelect = "select * from khoan_thu";
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				DanhSachKhoanThu dsKT = new DanhSachKhoanThu();
				dsKT.setIdKhoanThu(rs.getInt("ID"));
				dsKT.setTenKhoanThu(rs.getString("ten_khoan_thu"));
				dsKT.setBatDau(rs.getString("ngay_bat_dau"));
				dsKT.setKetThuc(rs.getString("ngay_ket_thuc"));
				dsKT.setSoTien(rs.getInt("muc_thu"));
				dsKhoanThu.add(dsKT);
			}
			
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsKhoanThu;
	}
	
	public ArrayList<HoNopTien> getHoNopTien() {
		ArrayList<HoNopTien> hoNopTien = new ArrayList<HoNopTien>();
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlSelect = "select ID, ten_chu_ho, so_nhan_khau from ho_khau";
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sqlSelect);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				HoNopTien hoNT = new HoNopTien();
				hoNT.setIdHoKhau(rs.getInt("ID"));
				hoNT.setTenHoKhau(rs.getString("ten_chu_ho"));
				hoNT.setSoNhanKhau(rs.getInt("so_nhan_khau"));
				hoNT.setTrangThai(0);
				hoNopTien.add(hoNT);
			}
			
			stmt.close();
			connection.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hoNopTien;
	}
	
	public ArrayList<HoNopTien> getHoNopTien(DanhSachKhoanThu dsKhoanThu) {
		ArrayList<HoNopTien> hoNopTien = new ArrayList<HoNopTien>();
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlSelect = "select ID, ten_chu_ho, so_nhan_khau, trang_thai from ho_khau, quan_ly_thu_phi " 
					+ "where ID = id_hokhau and id_khoanthu = " + dsKhoanThu.getIdKhoanThu();
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				HoNopTien hoNT = new HoNopTien();
				hoNT.setIdHoKhau(rs.getInt("ID"));
				hoNT.setTenHoKhau(rs.getString("ten_chu_ho"));
				hoNT.setSoNhanKhau(rs.getInt("so_nhan_khau"));
				hoNT.setTrangThai(rs.getInt("trang_thai"));
				hoNopTien.add(hoNT);
			}
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hoNopTien;
	}
	
	public void capNhatKhoanThu(DanhSachKhoanThu dsKhoanThu) {
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlUpdate = "update khoan_thu set "
					+ "ten_khoan_thu = ?, "
					+ "ngay_bat_dau = ?, "
					+ "ngay_ket_thuc = ?, " 
					+ "muc_thu = ? "
					+ "where ID = " + dsKhoanThu.getIdKhoanThu();
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlUpdate);
			preparedStatement.setString(1, dsKhoanThu.getTenKhoanThu());
			preparedStatement.setString(2, dsKhoanThu.getBatDau());
			preparedStatement.setString(3, dsKhoanThu.getKetThuc());
			preparedStatement.setInt(4, dsKhoanThu.getSoTien());
			preparedStatement.execute();
			
			preparedStatement.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void capNhatHoNopTien(DanhSachKhoanThu dsKhoanThu, ArrayList<HoNopTien> hoNopTien) {
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			for(int i = 0; i < hoNopTien.size(); i++) {
				String sqlUpdate = "update quan_ly_thu_phi" + " set "
						+ "trang_thai = " + hoNopTien.get(i).getTrangThai()
						+ " where id_hokhau = " + hoNopTien.get(i).getIdHoKhau()
						+ " and id_khoanthu = " + dsKhoanThu.getIdKhoanThu();
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlUpdate);
				preparedStatement.execute();
				preparedStatement.close();
			}
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int tongTienThu(DanhSachKhoanThu dsKhoanThu) {
		int sum = 0;
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlSelect = "select SUM(so_tien_thu) tong_tien from quan_ly_thu_phi"
					+ " where id_khoanthu = " + dsKhoanThu.getIdKhoanThu()
					+ " and trang_thai = 1";
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);
			ResultSet rs = preparedStatement.executeQuery();
			rs.first();
			sum = rs.getInt("tong_tien");
			
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}
	
	public int tongHoNopTien(DanhSachKhoanThu dsKhoanThu) {
		int sum = 0;
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlSelect = "select count(*) tong_ho_nop_tien from quan_ly_thu_phi"
					+ " where id_khoanthu = " + dsKhoanThu.getIdKhoanThu()
					+ " and trang_thai = 1";
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);
			ResultSet rs = preparedStatement.executeQuery();
			rs.first();
			sum = rs.getInt("tong_ho_nop_tien");
			
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}
	
}
