package view;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import org.json.JSONArray;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import objects.JsonDataSource;

public class TelaRelatorio extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JRViewer viewer;

	public TelaRelatorio() throws JRException, HeadlessException, IOException {
		super("Tela Teste ABS",true,true,true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(707, 400);
        this.getContentPane().add(getViewer(gerarJasper()));
	}
	
	private JRViewer getViewer(JasperPrint jasperPrint) {
		viewer = new JRViewer(jasperPrint);
		return viewer;
	}
	
    public JasperPrint gerarJasper() throws JRException, HeadlessException, IOException {
        HashMap<String,Object> parametros = new HashMap<String,Object>();
 
        InputStream jasperFile = ClassLoader.getSystemResourceAsStream("jasper/Rel_Geral.jasper");
        
        URL url = new URL("https://viacep.com.br/ws/09510200/json/");
        BufferedReader br = new BufferedReader(
        new InputStreamReader(url.openConnection().getInputStream(), "UTF-8"));

		String cepAux = "";
		StringBuilder jsonCep = new StringBuilder();

		while ((cepAux = br.readLine()) != null) {
			if (cepAux.contains("\"erro\": true")) {
				JOptionPane.showMessageDialog(TelaPrincipal.desktopPane.getSelectedFrame(), "Cep Não existe!",
						"Aviso de Falha", JOptionPane.ERROR_MESSAGE);
				break;
			} else {
				jsonCep.append(cepAux);
			}
		}
		String jsonString = "["+jsonCep.toString()+"]";

		
		JSONArray jsonArray = new JSONArray(jsonString);
         
        
        JRDataSource dataSource = new JsonDataSource(jsonArray);
        
        parametros.put("dados", jsonArray.toList());
        parametros.put("SUBREPORT_DIR", "jasper/");


        return JasperFillManager.fillReport(jasperFile, parametros, dataSource);
    }
}
