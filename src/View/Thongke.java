package View;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import controller.ThongKeController;
import models.DanhSachDongGop;
import models.DanhSachKhoanThu;
import models.HoDongGop;
import models.HoKhau;
import models.HoNopTien;
import services.DongGopService;
import services.KhoanThuService;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.ImageIcon;

public class Thongke extends JPanel {
	private ArrayList<DanhSachKhoanThu> khoanThus;
	private ArrayList<DanhSachDongGop> dongGop;
	private JTable table;
	private JTextField textField;
	private ArrayList<HoDongGop> hoDongGops;
	private ArrayList<HoNopTien> hoNopTiens;
	public int id;

	/**
	 * Create the panel.
	 */
	public Thongke() {
		id = 1;
		setBackground(new Color(255, 250, 240));
		setBorder(null);
		setLayout(null);

		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);

		table.setSelectionBackground(new Color(224, 255, 255));
		table.setFont(new Font("SansSerif", Font.PLAIN, 17));
		table.setBackground(new Color(255, 248, 220));
		table.setAutoCreateRowSorter(true);
		table.setRowHeight(30);

		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("SansSerif", Font.PLAIN, 18));
		header.setBackground(new Color(175, 238, 238));
		header.setPreferredSize(new Dimension(40, 40));
		KhoanThuService khoanThuService = new KhoanThuService();
		khoanThus = new ArrayList<DanhSachKhoanThu>();
		khoanThus = khoanThuService.getDanhSach();

		DongGopService dongGopService = new DongGopService();
		dongGop = new ArrayList<DanhSachDongGop>();
		dongGop = dongGopService.getDanhSach();
		
		ThongKeController thongKeController = new ThongKeController(khoanThus, dongGop, table, textField, hoDongGops, hoNopTiens);
		thongKeController.dataDongGop(dongGop);
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(255, 250, 240));
		desktopPane.setBounds(0, 0, 869, 631);
		add(desktopPane);

		JButton btnNewButton = new JButton("Chọn hộ");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = 0;
				thongKeController.dataHo();
			}
		});
		btnNewButton.setBounds(582, 162, 97, 44);
		desktopPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Tổng số tiền");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(id);
				if (id == 1) {
					for (DanhSachKhoanThu danhSachKhoanThu : khoanThus) {
						if (danhSachKhoanThu.getTenKhoanThu()
								.equals(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)))) {
//							DanhSachKhoanThu danhSachKhoanThu2 = new DanhSachKhoanThu();
//							danhSachKhoanThu2.setIdKhoanThu(danhSachKhoanThu.getIdKhoanThu());
							JOptionPane.showMessageDialog(null,
									"Số tiền đã đóng: " + khoanThuService.tongTienThu(danhSachKhoanThu));
							break;
						}
					}
				} else if (id == 2) {
					for (DanhSachDongGop danhSachDongGop : dongGop) {
						if (danhSachDongGop.getTenDongGop()
								.equals(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)))) {
							JOptionPane.showMessageDialog(null,
									"Sô tiền đã ủng hộ: " + dongGopService.tongTienDong(danhSachDongGop));
							break;
						}
					}
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1.setBounds(693, 362, 116, 40);
		desktopPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Tổng số hộ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id == 1) {
					for (DanhSachKhoanThu danhSachKhoanThu : khoanThus) {
						if (danhSachKhoanThu.getTenKhoanThu()
								.equals(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)))) {
//							DanhSachKhoanThu danhSachKhoanThu2 = new DanhSachKhoanThu();
//							danhSachKhoanThu2.setIdKhoanThu(danhSachKhoanThu.getIdKhoanThu());
							JOptionPane.showMessageDialog(null,
									"số hộ đã đóng: " + khoanThuService.tongHoNopTien(danhSachKhoanThu));
							break;
						}
					}
				} else if (id == 2) {
					for (DanhSachDongGop danhSachDongGop : dongGop) {
						if (danhSachDongGop.getTenDongGop()
								.equals(String.valueOf(table.getValueAt(table.getSelectedRow(), 0)))) {
							JOptionPane.showMessageDialog(null,
									"Sô hộ đã ủng hộ: " + dongGopService.tongHoDongGop(danhSachDongGop));
							break;
						}
					}
				}
				System.out.println(id);
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.setBounds(693, 434, 116, 40);
		desktopPane.add(btnNewButton_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBounds(34, 219, 648, 368);
		desktopPane.add(scrollPane);

		scrollPane.setViewportView(table);

		JButton btnNewButton_3 = new JButton("Khoản thu");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = 1;
				thongKeController.dataKhoanThu(khoanThus);
			}
		});
		btnNewButton_3.setBounds(693, 215, 116, 40);
		desktopPane.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Đóng góp");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = 2;
				thongKeController.dataDongGop(dongGop);
			}

		});
		btnNewButton_4.setBounds(693, 288, 116, 40);
		desktopPane.add(btnNewButton_4);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (id == 1) {
					thongKeController.searchKhoanThu(khoanThus);
				} else if (id == 2) {
					thongKeController.searchDongGop(dongGop);
				} else {
					thongKeController.searchHo(hoDongGops);
				}
			}
		});
		textField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 0, 0)));
		textField.setBackground(new Color(255, 250, 240));
		textField.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textField.setBounds(137, 176, 196, 30);
		desktopPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Tìm kiếm:");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel.setBounds(34, 181, 103, 25);
		desktopPane.add(lblNewLabel);

		JButton btnNewButton_5 = new JButton("Chi tiết hộ");
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HoKhau hoKhau = new HoKhau();
				hoKhau.setId(Integer.parseInt(String.valueOf((table.getValueAt(table.getSelectedRow(), 0)))));
				hoKhau.setTenHoKhau((String.valueOf((table.getValueAt(table.getSelectedRow(), 1)))));
				ChiTietThongKe chiTietThongKe = new ChiTietThongKe(hoKhau);
				try {
					Thread.sleep(100);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				desktopPane.add(chiTietThongKe);
				chiTietThongKe.setVisible(true);
			}
		});
		btnNewButton_5.setBounds(455, 162, 103, 44);
		desktopPane.add(btnNewButton_5);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\LENOVO\\eclipse-workspace\\Quanly\\src\\image\\search.png"));
		lblNewLabel_1.setBounds(345, 166, 56, 40);
		desktopPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Thống kê");
		lblNewLabel_2.setIcon(new ImageIcon(
				"C:\\Users\\LENOVO\\eclipse-workspace\\image\\iconfinder_document_text_information_103515.png"));
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblNewLabel_2.setBounds(34, 23, 179, 58);
		desktopPane.add(lblNewLabel_2);

	}

//	public void dataKhoanThu(ArrayList<DanhSachKhoanThu> khoanThus) {
//		DefaultTableModel model = new DefaultTableModel();
//		model.addColumn("Tên khoản thu");
//		model.addColumn("Bắt đầu");
//		model.addColumn("kết thúc");
//		model.addColumn("Số tiền");
//
//		for (DanhSachKhoanThu danhSachKhoanThu : khoanThus) {
//			model.addRow(new Object[] { danhSachKhoanThu.getTenKhoanThu(), danhSachKhoanThu.getBatDau(),
//					danhSachKhoanThu.getKetThuc(), danhSachKhoanThu.getSoTien() });
//		}
//		table.setModel(model);
//		JTableHeader header = table.getTableHeader();
//		header.setFont(new Font("SansSerif", Font.PLAIN, 18));
//		header.setBackground(new Color(175, 238, 238));
//		header.setPreferredSize(new Dimension(40, 40));
//	}
//
//	public void dataDongGop(ArrayList<DanhSachDongGop> dongGop) {
//		DefaultTableModel model = new DefaultTableModel();
//		model.addColumn("Tên đóng góp");
//		model.addColumn("Bắt đầu");
//		model.addColumn("kết thúc");
//
//		for (DanhSachDongGop danhSachDongGop : dongGop) {
//			model.addRow(new Object[] { danhSachDongGop.getTenDongGop(), danhSachDongGop.getBatDau(),
//					danhSachDongGop.getKetThuc() });
//		}
//		table.setModel(model);
//	}
//
//	public void searchKhoanThu(ArrayList<DanhSachKhoanThu> khoanThus) {
//		DefaultTableModel model = new DefaultTableModel();
//		model.addColumn("Tên khoản thu");
//		model.addColumn("Bắt đầu");
//		model.addColumn("kết thúc");
//		model.addColumn("Số tiền");
//
//		for (DanhSachKhoanThu danhSachKhoanThu : khoanThus) {
//			if (danhSachKhoanThu.getTenKhoanThu().toLowerCase().indexOf(textField.getText().toLowerCase()) >= 0) {
//				model.addRow(new Object[] { danhSachKhoanThu.getTenKhoanThu(), danhSachKhoanThu.getBatDau(),
//						danhSachKhoanThu.getKetThuc(), danhSachKhoanThu.getSoTien() });
//			}
//
//		}
//		table.setModel(model);
//	}
//
//	public void searchDongGop(ArrayList<DanhSachDongGop> dongGop) {
//		DefaultTableModel model = new DefaultTableModel();
//		model.addColumn("Tên khoản thu");
//		model.addColumn("Bắt đầu");
//		model.addColumn("kết thúc");
//		for (DanhSachDongGop danhSachDongGop : dongGop) {
//			if (danhSachDongGop.getTenDongGop().toLowerCase().indexOf(textField.getText().toLowerCase()) >= 0) {
//				model.addRow(new Object[] { danhSachDongGop.getTenDongGop(), danhSachDongGop.getBatDau(),
//						danhSachDongGop.getKetThuc() });
//			}
//		}
//		table.setModel(model);
//	}
//
//	public void searchHo(ArrayList<HoDongGop> hoDongGops) {
//		DefaultTableModel model = new DefaultTableModel();
//		model.addColumn("Id hộ");
//		model.addColumn("Tên hộ");
//		for (HoDongGop hoDongGop : hoDongGops) {
//			if (hoDongGop.getTenHoKhau().toLowerCase().indexOf(textField.getText().toLowerCase()) >= 0) {
//				model.addRow(new Object[] { hoDongGop.getIdHoKhau(), hoDongGop.getTenHoKhau() });
//			}
//		}
//		table.setModel(model);
//	}
//
//	public void dataHo() {
//		DongGopService dongGopService = new DongGopService();
//		hoDongGops = new ArrayList<HoDongGop>();
//		hoDongGops = dongGopService.getHoDongGop();
//
//		DefaultTableModel model = new DefaultTableModel();
//		model.addColumn("Id hộ");
//		model.addColumn("Tên hộ");
//		for (HoDongGop hoDongGop : hoDongGops) {
//			model.addRow(new Object[] { hoDongGop.getIdHoKhau(), hoDongGop.getTenHoKhau() });
//		}
//
//		table.setModel(model);
//	}

}
