package services;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import models.DanhSachDongGop;
import models.DanhSachKhoanThu;
import models.HoDongGop;
import models.HoNopTien;

public class DongGopService {
	
	public boolean themDongGop(DanhSachDongGop dsDongGop) {
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlInsert = "INSERT INTO dong_gop(ten_dong_gop, ngay_bat_dau, ngay_ket_thuc)" 
	                + " values (?, ?, ?)";
			
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
			preparedStatement.setString(1, dsDongGop.getTenDongGop());
			preparedStatement.setString(2, dsDongGop.getBatDau());
			preparedStatement.setString(3, dsDongGop.getKetThuc());
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
	
	public void themHoKhau(DanhSachDongGop dsDongGop, ArrayList<HoDongGop> hoDongGop) {
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlSearch = "select ID from dong_gop where ten_dong_gop = " + "'" + dsDongGop.getTenDongGop() + "'";
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSearch);
			ResultSet rs = preparedStatement.executeQuery();
			int idDongGop = rs.getInt("ID");
			String sqlInsert = "INSERT INTO quan_ly_dong_gop(id_donggop, id_hokhau, so_tien_dong)"
					+ " values (?, ?, ?)";
			preparedStatement = (PreparedStatement) connection.prepareStatement(sqlInsert);
			for(int i = 0; i < hoDongGop.size(); i++) {
				preparedStatement.setInt(1, idDongGop);
				preparedStatement.setInt(2, hoDongGop.get(i).getIdHoKhau());
				preparedStatement.setInt(3, hoDongGop.get(i).getTienDongGop());
				
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
	
	public ArrayList<DanhSachDongGop> getDanhSach() {
		ArrayList<DanhSachDongGop> dsDongGop = new ArrayList<DanhSachDongGop>();
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlSelect = "select * from dong_gop";
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				DanhSachDongGop dsDG = new DanhSachDongGop();
				dsDG.setIdDongGop(rs.getInt("ID"));
				dsDG.setTenDongGop(rs.getString("ten_dong_gop"));
				dsDG.setBatDau(rs.getString("ngay_bat_dau"));
				dsDG.setKetThuc(rs.getString("ngay_ket_thuc"));
				dsDongGop.add(dsDG);
			}
			
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsDongGop;
	}
	
	public ArrayList<HoDongGop> getHoDongGop() {
		ArrayList<HoDongGop> hoDongGop = new ArrayList<HoDongGop>();
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlSelect = "select ID, ten_chu_ho from ho_khau";
			PreparedStatement stmt = (PreparedStatement) connection.prepareStatement(sqlSelect);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				HoDongGop hoDG = new HoDongGop();
				hoDG.setIdHoKhau(rs.getInt("ID"));
				hoDG.setTenHoKhau(rs.getString("ten_chu_ho"));
				hoDG.setTienDongGop(0);
				hoDongGop.add(hoDG);
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
		return hoDongGop;
	}
	
	public void capNhatDongGop(DanhSachDongGop dsDongGop) {
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlUpdate = "update dong_gop set "
					+ "ten_dong_gop = ?, "
					+ "ngay_bat_dau = ?, "
					+ "ngay_ket_thuc = ? " 
					+ "where ID = " + dsDongGop.getIdDongGop();
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlUpdate);
			preparedStatement.setString(1, dsDongGop.getTenDongGop());
			preparedStatement.setString(2, dsDongGop.getBatDau());
			preparedStatement.setString(3, dsDongGop.getKetThuc());
			preparedStatement.execute();
			
			preparedStatement.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void capNhatHoDongGop(DanhSachDongGop dsDongGop, ArrayList<HoDongGop> hoDongGop) {
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			for(int i = 0; i < hoDongGop.size(); i++) {
				String sqlUpdate = "update quan_ly_dong_gop" + " set "
						+ "so_tien_dong = ?, " + hoDongGop.get(i).getTienDongGop()
						+ "where id_hokhau = " + hoDongGop.get(i).getIdHoKhau()
						+ "and id_donggop = " + dsDongGop.getIdDongGop();
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlUpdate);
//				preparedStatement.setInt(1, hoNopTien.get(i).getSoNhanKhau());
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
	
	public int tongTienThu(DanhSachDongGop dsDongGop) {
		int sum = 0;
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlSelect = "select SUM(so_tien_dong) tong_tien from quan_ly_dong_gop "
					+ "where id_donggop = " + dsDongGop.getIdDongGop();
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
	
	public int tongHoDongGop(DanhSachDongGop dsDongGop) {
		int sum = 0;
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlSelect = "select SUM(so_tien_dong) tong_ho_dong_gop from quan_ly_dong_gop "
					+ "where id_donggop = " + dsDongGop.getIdDongGop();
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);
			ResultSet rs = preparedStatement.executeQuery();
			rs.first();
			sum = rs.getInt("tong_ho_dong_gop");
			
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}
	
	public ArrayList<DanhSachDongGop> cacHoDaDong(HoDongGop hoDongGop) {
		ArrayList<DanhSachDongGop> dsDongGop = new ArrayList<DanhSachDongGop>();
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlSelect = "select ID, ten_dong_gop, ngay_bat_dau, ngay_ket_thuc from quan_ly_dong_gop, dong_gop"
					+ " where id_hokhau = " + hoDongGop.getIdHoKhau()
					+ " and id_hokhau = ID";
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				DanhSachDongGop dsDG = new DanhSachDongGop();
				dsDG.setIdDongGop(rs.getInt("ID"));
				dsDG.setTenDongGop(rs.getString("ten_dong_gop"));
				dsDG.setBatDau(rs.getString("ngay_bat_dau"));
				dsDG.setKetThuc(rs.getString("ngay_ket_thuc"));
				dsDongGop.add(dsDG);
			}
			
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsDongGop;
	}
	
	public ArrayList<Integer> soTienDongGop(HoDongGop hoDongGop) {
		ArrayList<Integer> soTienDong = new ArrayList<Integer>();
		try {
			Connection connection = (Connection) MysqlConnection.getMysqlConnection();
			String sqlSelect = "select so_tien_dong from quan_ly_dong_gop"
					+ " where id_hokhau = " + hoDongGop.getIdHoKhau();
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sqlSelect);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				soTienDong.add(rs.getInt("so_tien_dong"));
			}
			
			preparedStatement.close();
			connection.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return soTienDong;
	}
}