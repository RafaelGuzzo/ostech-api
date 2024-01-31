package br.com.ostech.service.impl;

import br.com.ostech.model.Order;
import br.com.ostech.model.SystemConfiguration;
import br.com.ostech.repository.SystemConfigurationRepository;
import br.com.ostech.service.OrderExportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class OrderExportServiceImpl implements OrderExportService {

    private static String LOGO = "LOGO";
    private static String DESCRIPTION = "DESCRIPTION";
    private static String REPORT_LOCALE = "REPORT_LOCALE";
    private static String LANGUAGE = "pt";
    private static String COUNTRY = "BR";

    @Value("${export.order.pdf}")
    private String path;

    private final SystemConfigurationRepository systemConfigurationRepository;

    @Autowired
    public OrderExportServiceImpl(SystemConfigurationRepository systemConfigurationRepository) {
        this.systemConfigurationRepository = systemConfigurationRepository;
    }

    @Override
    public byte[] exportToPDF(Order order) throws IOException, JRException {
        SystemConfiguration systemConfiguration = systemConfigurationRepository.findAll().stream().findFirst().get();

        InputStream inputStream = new ClassPathResource(path).getInputStream();

        Map<String, Object> parameters = new HashMap<>();
        parameters.put(LOGO, systemConfiguration.getLogo());
        parameters.put(DESCRIPTION, systemConfiguration.getDescription());
        parameters.put(REPORT_LOCALE, new Locale(LANGUAGE, COUNTRY));


        var jasperPrint = JasperFillManager.fillReport(
                inputStream,
                parameters,
                new JRBeanCollectionDataSource(
                        List.of(order)
                )
        );

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
