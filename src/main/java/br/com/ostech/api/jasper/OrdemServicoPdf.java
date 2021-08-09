package br.com.ostech.api.jasper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.ostech.api.controller.dto.ordemservico.OrdemServicoPdfResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;



@Component
public class OrdemServicoPdf {

	public byte[] relatorioCompleto(OrdemServicoPdfResponse response) throws Exception {

		ArrayList list = new ArrayList();
		list.add(response);

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

		InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/ostech-print.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(list));

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

}
