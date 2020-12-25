package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.ChiTietThongKeController;
import models.DanhSachDongGop;
import models.DanhSachKhoanThu;
import models.HoDongGop;
import models.HoKhau;
import models.HoNopTien;
import models.KhoanTien;
import services.DongGopService;
import services.KhoanThuService;

public class ChiTietThongKe extends JInternalFrame {
	private JTable table;
	public HoKhau hoKhau;
	public KhoanTien khoanTien;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChiTietThongKe frame = new ChiTietThongKe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ChiTietThongKe() {
		super();
	}

	public ChiTietThongKe(HoKhau hoKhau) {
		this.hoKhau = hoKhau;
		setClosable(true);
		setBounds(100, 100, 636, 468);
		getContentPane().setLayout(null);
		setBackground(new Color(255, 250, 240));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 116, 596, 287);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setSelectionBackground(new Color(224, 255, 255));
		table.setFont(new Font("SansSerif", Font.PLAIN, 17));
		table.setBackground(new Color(255, 248, 220));
		table.setAutoCreateRowSorter(true);
		table.setRowHeight(30);
		
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("SansSerif", Font.PLAIN, 18));
		header.setBackground(new Color(175, 238, 238));
		header.setPreferredSize(new Dimension(40, 40));
		
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel(hoKhau.getTenHoKhau() + "!!");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 28, 300, 45);
		getContentPane().add(lblNewLabel);
		
		ChiTietThongKeController thongKeController = new ChiTietThongKeController(table, hoKhau, khoanTien);
		thongKeController.setData();

	}
	
	public void setData() {
		ArrayList<DanhSachDongGop> danhSachDongGops = new ArrayList<DanhSachDongGop>();
		ArrayList<DanhSachKhoanThu> danhSachKhoanThus = new ArrayList<DanhSachKhoanThu>();
		
		ArrayList<HoDongGop> hoDongGops = new ArrayList<HoDongGop>();
		ArrayList<HoNopTien> hoNopTiens = new ArrayList<HoNopTien>();
		
		ArrayList<KhoanTien> khoanTiens = new ArrayList<KhoanTien>();
		
		DongGopService dongGopService = new DongGopService();
		KhoanThuService khoanThuService = new KhoanThuService();
		
		danhSachDongGops = dongGopService.getDanhSach();
		danhSachKhoanThus = khoanThuService.getDanhSach();
		
		for(DanhSachDongGop danhSachDongGop: danhSachDongGops) {
			hoDongGops = dongGopService.getHoDongGop(danhSachDongGop);
			for(HoDongGop hoDongGop : hoDongGops) {
				if(hoKhau.getId() == hoDongGop.getIdHoKhau()) {
					KhoanTien khoanTien = new KhoanTien();
					khoanTien.setTenKhoan(danhSachDongGop.getTenDongGop());
					khoanTien.setSoTien(hoDongGop.getTienDongGop());
					khoanTiens.add(khoanTien);
				}
			}
		}
		
		for(DanhSachKhoanThu danhSachKhoanThu: danhSachKhoanThus) {
			hoNopTiens = khoanThuService.getHoNopTien(danhSachKhoanThu);
			for(HoNopTien hoNopTien: hoNopTiens) {
				if(hoKhau.getId() == hoNopTien.getIdHoKhau()) {
					if(hoNopTien.getTrangThai() == 1) {
						KhoanTien khoanTien = new KhoanTien();
						khoanTien.setSoTien(danhSachKhoanThu.getSoTien() * hoNopTien.getSoNhanKhau());
						khoanTien.setTenKhoan(danhSachKhoanThu.getTenKhoanThu());
						khoanTiens.add(khoanTien);
					}else {
						KhoanTien khoanTien = new KhoanTien();
						khoanTien.setSoTien(0);
						khoanTien.setTenKhoan(danhSachKhoanThu.getTenKhoanThu());
						khoanTiens.add(khoanTien);
					}
				}
			}
		}
		
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("Tên khoản");
		model.addColumn("Số tiền");
		for (KhoanTien khoanTien : khoanTiens) {
			model.addRow(new Object[] { khoanTien.getTenKhoan(), khoanTien.getSoTien() });
		}
		table.setModel(model);
	}
}
