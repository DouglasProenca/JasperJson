package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.sf.jasperreports.engine.JRException;

public class TelaPrincipal extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	protected static  JDesktopPane desktopPane;
	private JMenuBar barraMenu;
	private JMenu menu;
	private JMenuItem jasper;

	public TelaPrincipal() {
		this.setLocationRelativeTo(null);
		this.setSize(new Dimension(800, 500));
		this.setExtendedState(MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().add(getDesktopPane());
		this.setJMenuBar(getBarraMenu());
	}
	
	private JDesktopPane getDesktopPane() {
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(19,138,178));
		return desktopPane;
	}
	
	private JMenuBar getBarraMenu() {
		barraMenu = new JMenuBar();
		barraMenu.add(getMenu());
		return barraMenu;
	}
	
	private JMenu getMenu() {
		menu = new JMenu("Menu");
		menu.add(getJasper());
		return menu;
	}
	
	private JMenuItem getJasper() {
		jasper = new JMenuItem("Jasper");
		jasper.addActionListener(this);
		jasper.setActionCommand("jasper");
		return jasper;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		default:
			try {
				TelaRelatorio relatorio = new TelaRelatorio();
				relatorio.setVisible(true);
				desktopPane.add(relatorio);
			} catch (JRException | IOException | HeadlessException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public static void main(String args[]) {
		TelaPrincipal principal = new TelaPrincipal();
		principal.setVisible(true);
	}
}
