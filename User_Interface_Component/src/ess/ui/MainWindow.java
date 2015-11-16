package ess.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ess.data.Composite;

public class MainWindow extends JFrame implements ICompositeView {

	private static final long serialVersionUID = 1L;
	
	private JLabel[] txtInfos;
	private JPanel pnlTop, pnlInfo, pnlCenter;
	
	private Composite composite;
	
	private String[] sampleText = new String[]{"Dieser Verbund bricht Regel No. 1 weil er hässlich ist", 
			"Dieser Verbund ist nur ein Beispiel", "Dieser Verbund dient als Testverbund, um das Layout "
			+ "anständig hinzubekommen"};
	
	public MainWindow(Composite composite) {
		this.composite = composite;
		txtInfos = new JLabel[3];
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Roemischer Verbund");
		getContentPane().setBackground(Color.WHITE);
		
		createAndAddWidgets();
		
		pack();   
		setLocationRelativeTo(null);
	}
	
	private void createAndAddWidgets() {
		
		pnlTop = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlTop.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pnlTop.setBackground(new Color(0,0,102));
		getContentPane().add(pnlTop, BorderLayout.BEFORE_FIRST_LINE);
		
		pnlInfo = new JPanel(new GridLayout(0,1,3,3));
		pnlInfo.setOpaque(false);
		pnlTop.add(pnlInfo);
		
		for (int i = 0; i < txtInfos.length; i++) {
			txtInfos[i] = new JLabel(sampleText[i]);
			txtInfos[i].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
			txtInfos[i].setOpaque(false);
			txtInfos[i].setForeground(Color.LIGHT_GRAY);
			pnlInfo.add(txtInfos[i]);
		}
		
		pnlCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlCenter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		pnlCenter.setOpaque(false);
		getContentPane().add(pnlCenter, BorderLayout.CENTER);
		
		CompositePanel cPanel = new CompositePanel(composite.getSurface());
		pnlCenter.add(cPanel);
	}

	@Override
	public void display() {
		
		setVisible(true);
	}
	
}
