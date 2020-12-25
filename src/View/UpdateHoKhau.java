package View;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.dnd.InvalidDnDOperationException;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.UpdateHoKhauController;
import models.DanhSachDongGop;
import models.DanhSachKhoanThu;
import models.HoDongGop;
import models.HoNopTien;
import services.DongGopService;
import services.KhoanThuService;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class UpdateHoKhau extends JInternalFrame {
	public DanhSachKhoanThu danhSachKhoanThu;
	public DanhSachDongGop danhSachDongGop;
	public JTable table_1;
	private int id;
	public ArrayList<HoNopTien> hoNopTiens;
	public ArrayList<HoDongGop> hoDongGops;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateHoKhau frame = new UpdateHoKhau();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws PropertyVetoException
	 */
	public UpdateHoKhau() {
	};

	public UpdateHoKhau(int id, int select) throws PropertyVetoException {
		setTitle("Hộ khẩu");
		setClosed(true);
		setClosable(true);
		setBounds(20, 20, 732, 541);

		hoNopTiens = new ArrayList<HoNopTien>();
		KhoanThuService khoanThuService = new KhoanThuService();
		danhSachKhoanThu = new DanhSachKhoanThu();
		danhSachKhoanThu.setIdKhoanThu(id);
		hoNopTiens = khoanThuService.getHoNopTien(danhSachKhoanThu);

		hoDongGops = new ArrayList<HoDongGop>();
		DongGopService dongGopService = new DongGopService();
		danhSachDongGop = new DanhSachDongGop();
		danhSachDongGop.setIdDongGop(id);
		hoDongGops = dongGopService.getHoDongGop(danhSachDongGop);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 240));
		panel.setBounds(0, 0, 716, 505);
		getContentPane().add(panel);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 78, 692, 349);
		panel.add(scrollPane);

		table_1 = new JTable();
		table_1.setColumnSelectionAllowed(true);
		table_1.setCellSelectionEnabled(true);
		table_1.setSelectionBackground(new Color(224, 255, 255));
		table_1.setFont(new Font("SansSerif", Font.PLAIN, 17));
		table_1.setBackground(new Color(255, 248, 220));
		table_1.setAutoCreateRowSorter(true);
		table_1.setRowHeight(25);

		JTableHeader header = table_1.getTableHeader();
		header.setFont(new Font("SansSerif", Font.PLAIN, 18));
		header.setBackground(new Color(175, 238, 238));
		header.setPreferredSize(new Dimension(40, 40));
		
		UpdateHoKhauController updateHoKhauController = new UpdateHoKhauController(table_1, hoNopTiens, hoDongGops, danhSachDongGop, danhSachKhoanThu);
		scrollPane.setViewportView(table_1);
		JButton btnNewButton = new JButton("Cập nhật");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (select == 1) {
					updateHoKhauController.updateHoNopTien();

				} else {
					updateHoKhauController.updateHoDongGop();
					
				}

			}
		});
		btnNewButton.setBounds(587, 452, 117, 40);
		panel.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Danh Sách Hộ Khẩu");
		lblNewLabel.setIcon(new ImageIcon(
				"C:\\Users\\LENOVO\\eclipse-workspace\\Quanly\\src\\image\\iconfinder_shopping-shop-buy-discount-38_4038801.png"));
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblNewLabel.setBounds(12, 12, 266, 54);
		panel.add(lblNewLabel);
		if(select == 1) {
			updateHoKhauController.dataHoNopTien();
		} else {
			updateHoKhauController.dataDongGop();
		}
	}

//	public void dataHoNopTien() {
//		DefaultTableModel model = new DefaultTableModel();
//		model.addColumn("ID hộ khẩu");
//		model.addColumn("Tên hộ khẩu");
//		model.addColumn("Số nhân khẩu");
//		model.addColumn("Trạng thái");
//
//		for (HoNopTien hoNopTien : hoNopTiens) {
//			model.addRow(new Object[] { hoNopTien.getIdHoKhau(), hoNopTien.getTenHoKhau(), hoNopTien.getSoNhanKhau(),
//					hoNopTien.getTrangThai() });
//		}
//		table_1.setModel(model);
//	}
//
//	public void dataDongGop() {
//		hoDongGops = new ArrayList<HoDongGop>();
//		DongGopService dongGopService = new DongGopService();
//		hoDongGops = dongGopService.getHoDongGop(danhSachDongGop);
//		DefaultTableModel model = new DefaultTableModel();
//		model.addColumn("ID hộ khẩu");
//		model.addColumn("Tên hộ khẩu");
//		model.addColumn("Tiền đóng góp");
//
//		for (HoDongGop hoDongGop : hoDongGops) {
//			model.addRow(
//					new Object[] { hoDongGop.getIdHoKhau(), hoDongGop.getTenHoKhau(), hoDongGop.getTienDongGop() });
//		}
//		table_1.setModel(model);
//	}
//
//	public void updateHoDongGop() {
//		int i = 0;
//		ArrayList<Integer> checkArrayList = new ArrayList<Integer>();
//		DongGopService dongGopService = new DongGopService();
//		for (HoDongGop hoDongGop : hoDongGops) {
//			try {
//				System.out.println(
//						Integer.parseInt(String.valueOf((table_1.getValueAt(hoDongGops.indexOf(hoDongGop), 2)))));
//			} catch (Exception e) {
//				// TODO: handle exception
//				JOptionPane.showMessageDialog(null, "Sai định dạng trạng thái", "Message", JOptionPane.ERROR_MESSAGE);
//			}
//			if(Integer.parseInt(String.valueOf((table_1.getValueAt(hoDongGops.indexOf(hoDongGop), 2)))) < 0) {
//				i++;
//				checkArrayList.add(hoDongGops.indexOf(hoDongGop));
//			}
//			hoDongGop.setTienDongGop(
//					Integer.parseInt(String.valueOf((table_1.getValueAt(hoDongGops.indexOf(hoDongGop), 2)))));
//		}
//		if (i != 0) {
//			String string = "";
//			for (Integer k : checkArrayList) {
//				string += String.valueOf(k + 1) + ",";
//			}
//			JOptionPane.showMessageDialog(null, "Sai Trạng thái vị trí: " + string, "Message", JOptionPane.ERROR_MESSAGE);
//		} else {
//			dongGopService.capNhatHoDongGop(danhSachDongGop, hoDongGops);
//			JOptionPane.showMessageDialog(null, "Cập nhật thành công");
//		}
//		
//		
//	}
//
//	public void updateHoNopTien() {
//		int i = 0;
//		ArrayList<Integer> checkArrayList = new ArrayList<Integer>();
//		KhoanThuService khoanThuService = new KhoanThuService();
//
//		for (HoNopTien hoNopTien : hoNopTiens) {
//			try {
//				System.out.println(
//						Integer.parseInt(String.valueOf((table_1.getValueAt(hoNopTiens.indexOf(hoNopTien), 3)))));
//			} catch (Exception e) {
//				// TODO: handle exception
//				JOptionPane.showMessageDialog(null, "Sai định dạng trạng thái", "Message", JOptionPane.ERROR_MESSAGE);
//			}
//			if (Integer.parseInt(String.valueOf((table_1.getValueAt(hoNopTiens.indexOf(hoNopTien), 3)))) != 0
//					&& Integer.parseInt(String.valueOf((table_1.getValueAt(hoNopTiens.indexOf(hoNopTien), 3)))) != 1) {
//
//				i++;
//				checkArrayList.add(hoNopTiens.indexOf(hoNopTien));
//			}
//			hoNopTien.setTrangThai(
//					Integer.parseInt(String.valueOf((table_1.getValueAt(hoNopTiens.indexOf(hoNopTien), 3)))));
//
//		}
//		if (i != 0) {
//			String string = "";
//			for (Integer k : checkArrayList) {
//				string += String.valueOf(k + 1) + ",";
//			}
//			JOptionPane.showMessageDialog(null, "Sai Trạng thái vị trí: " + string, "Message", JOptionPane.ERROR_MESSAGE);
//		} else {
//			khoanThuService.capNhatHoNopTien(danhSachKhoanThu, hoNopTiens);
//			JOptionPane.showMessageDialog(null, "Cập nhật thành công");
//		}
//
//	}
}
