package View;

import java.awt.EventQueue;import java.awt.dnd.InvalidDnDOperationException;
import java.beans.PropertyVetoException;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import models.DanhSachDongGop;
import models.DanhSachKhoanThu;
import models.HoDongGop;
import models.HoNopTien;
import services.DongGopService;
import services.KhoanThuService;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class UpdateHoKhau extends JInternalFrame {
	public DanhSachKhoanThu danhSachKhoanThu;
	public DanhSachDongGop danhSachDongGop;
	private JTable table;
	private int id;
	public ArrayList<HoNopTien> hoNopTiens;
	ArrayList<HoDongGop> hoDongGops;
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
	 * @throws PropertyVetoException 
	 */
	public UpdateHoKhau() throws PropertyVetoException {
		setTitle("Hộ khẩu");
		setClosed(true);
		setClosable(true);
		setBounds(20, 20, 732, 541);
		getContentPane().setLayout(null);
		
		hoNopTiens = new ArrayList<HoNopTien>();
		KhoanThuService khoanThuService = new KhoanThuService();
		hoNopTiens = khoanThuService.getHoNopTien();
		
		hoDongGops = new ArrayList<HoDongGop>();
		DongGopService dongGopService = new DongGopService();
		hoDongGops = dongGopService.getHoDongGop();
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 716, 505);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 120, 647, 307);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		if(id == 1) {
			dataHoNopTien(hoNopTiens);
		} else {
			dataDongGop(hoDongGops);
		}
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(id == 1) {
					System.out.println(danhSachKhoanThu.getTenKhoanThu());
				} else {
					updateHoDongGop(hoDongGops, danhSachDongGop);
				}
			}
		});
		btnNewButton.setBounds(587, 452, 97, 25);
		panel.add(btnNewButton);
		
	}
	
	public void dataHoNopTien(ArrayList<HoNopTien> hoNopTiens) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID hộ khẩu");
		model.addColumn("Tên hộ khẩu");
		model.addColumn("Số nhân khẩu");
		model.addColumn("Trạng thái");
		
		for(HoNopTien hoNopTien: hoNopTiens) {
			model.addRow(new Object[] {hoNopTien.getIdHoKhau(), hoNopTien.getTenHoKhau(), hoNopTien.getSoNhanKhau(), hoNopTien.getTrangThai()});
		}
		table.setModel(model);
	}
	
	public void dataDongGop(ArrayList<HoDongGop> hoDongGops) {
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("ID hộ khẩu");
		model.addColumn("Tên hộ khẩu");
		model.addColumn("Tiền đóng góp");
		
		for(HoDongGop hoDongGop: hoDongGops) {
			model.addRow(new Object[] {hoDongGop.getIdHoKhau(), hoDongGop.getTenHoKhau(), hoDongGop.getTienDongGop()});
		}
		table.setModel(model);
	}
	
	public void updateHoDongGop(ArrayList<HoDongGop> hoDongGops, DanhSachDongGop danhSachDongGop) {
		for(HoDongGop hoDongGop: hoDongGops) {
			hoDongGop.setTenHoKhau(String.valueOf(table.getValueAt(hoDongGops.indexOf(hoDongGop), 1)));
			hoDongGop.setTienDongGop(Integer.parseInt(String.valueOf((table.getValueAt(hoDongGops.indexOf(hoDongGop), 2)))));
		}
		DongGopService dongGopService = new DongGopService();
		dongGopService.themHoKhau(danhSachDongGop, hoDongGops);
	}
}
