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
  private final EmployeeService employeeService;

  public byte[] generatePdfFromHtml(String html)
      throws DocumentException {
    ITextRenderer renderer = new ITextRenderer();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    renderer.setDocumentFromString(html);
    renderer.layout();
    renderer.createPDF(outputStream);
    return outputStream.toByteArray();

  }

  private String parseThymeleafTemplate(EmployeeEntity employeeEntity, CompanyConf companyConf) {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix("templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setCharacterEncoding("UTF-8");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    templateResolver.setOrder(1);

    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);

    Context context = new Context();
    context.setVariable("employee", employeeEntity);
    context.setVariable("companyConf", companyConf);

    return templateEngine.process(PdfService.EMPLOYEE_HTML_TEMPLATE, context);
  }


  public byte[] getPdf(int id) throws DocumentException {
    CompanyConf companyConf = companyConfService.getCompanyConf();
    EmployeeEntity employee = employeeService.findById(id);
    return generatePdfFromHtml(parseThymeleafTemplate(employee, companyConf));
  }
}