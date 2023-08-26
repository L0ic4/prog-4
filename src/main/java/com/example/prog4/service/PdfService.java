package com.example.prog4.service;

import com.example.prog4.entity.Employee.CompanyConf;
import com.example.prog4.entity.Employee.EmployeeEntity;
import com.lowagie.text.DocumentException;
import java.io.ByteArrayOutputStream;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Service
@AllArgsConstructor
public class PdfService {
  private static final String EMPLOYEE_HTML_TEMPLATE = "employee-file";
  private final CompanyConfService companyConfService;

  public byte[] generatePdf(EmployeeEntity employee,
                            CompanyConf companyConf, String template) {
    ITextRenderer renderer = new ITextRenderer();
    loadStyle(renderer, employee, companyConf, template);
    renderer.layout();

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      renderer.createPDF(outputStream);
    } catch (DocumentException ignored) {
    }
    return outputStream.toByteArray();
  }

  private void loadStyle(ITextRenderer renderer, EmployeeEntity employee,
                         CompanyConf companyConf, String template) {
    renderer.setDocumentFromString(parseCardTemplateToString(employee, companyConf, template));

  }

  private String parseCardTemplateToString(
      EmployeeEntity employee, CompanyConf companyConf, String template) {
    TemplateEngine templateEngine = configureTemplate();
    Context context = configureContext(employee, companyConf);
    return templateEngine.process(template, context);
  }

  private Context configureContext(EmployeeEntity employee, CompanyConf companyConf) {
    Context context = new Context();
    context.setVariable("employee", employee);
    context.setVariable("companyConf", companyConf);
    return context;
  }

  private TemplateEngine configureTemplate() {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix("templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setCharacterEncoding("UTF-8");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    templateResolver.setOrder(1);

    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);
    return templateEngine;
  }

  public byte[] getPdfCard(EmployeeEntity employee) {
    CompanyConf companyConf = companyConfService.getCompanyConf();
    return generatePdf(employee, companyConf, EMPLOYEE_HTML_TEMPLATE);
  }
}