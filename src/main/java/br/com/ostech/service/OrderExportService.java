package br.com.ostech.service;

import br.com.ostech.model.Order;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;

public interface OrderExportService {

    byte[] exportToPDF(Order order) throws IOException, JRException;
}
