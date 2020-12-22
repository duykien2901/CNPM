package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;

import DanhmucSP.Danhmuc;
import View.JpanelAdd;
import View.JpanelMain;
import View.JpanelUpdate;

public class ChuyenController {
	private JPanel root;
	public ChuyenController(JPanel root) {
		super();
		this.root = root;
	}
	
	public void setView(JPanel jpnItem, JLabel jlbItem) {
		jlbItem.setBackground(new Color(96, 100, 191));
		jlbItem.setBackground(new Color(96, 100, 191));
		// Xoa tat ca
		root.removeAll();
		root.setLayout(new BorderLayout());
		root.add(new JpanelUpdate());
		root.validate(); // xac thuc vung con
		root.repaint();
	}
}
//	public void setEvent(List<Danhmuc> listItems) {
//		for(Danhmuc item: listItems) {
//			
//		}
//	}
//
//	class labelEvent implements MouseListener{
//		
//		private JPanel node;
//		
//		private String kind;
//		private JLabel JpbItem;
//		private JPanel JPnItem;
//		
//		public labelEvent(String kind, JLabel jpbItem, JPanel jPnItem) {
//			super();
//			this.kind = kind;
//			this.JpbItem = jpbItem;
//			this.JPnItem = jPnItem;
//		}
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			// TODO Auto-generated method stub
//			switch (kind) {
//			case "TrangChu":
//				node = new JpanelMain();
//				break;
//			case "Add":
//				node = new JpanelAdd();
//			default:
//				break;
//			}
//			root.removeAll();
//			root.setLayout(new BorderLayout());
//			root.add(node);
//			root.validate(); // xac thuc vung con
//			root.repaint();
//		}
//		
//		@Override
//		public void mousePressed(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//	
//		@Override
//		public void mouseReleased(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//	
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//	
//		@Override
//		public void mouseExited(MouseEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//	}
//}
