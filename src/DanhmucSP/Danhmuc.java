package DanhmucSP;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Danhmuc {
	private String kind;
	private JPanel jpb;
	private JLabel jlb;
	public Danhmuc(String kind, JPanel jpb, JLabel jlb) {
		super();
		this.kind = kind;
		this.jpb = jpb;
		this.jlb = jlb;
	}
	
	public JPanel getJpb() {
		return jpb;
	}
	public void setJpb(JPanel jpb) {
		this.jpb = jpb;
	}
	public JLabel getJlb() {
		return jlb;
	}
	public void setJlb(JLabel jlb) {
		this.jlb = jlb;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}
	
}
