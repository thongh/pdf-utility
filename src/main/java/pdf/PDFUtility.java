package pdf;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import model.CommissionREST;
import model.ShopCommission;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;

import static org.thymeleaf.templatemode.TemplateMode.HTML;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
/**
 * This is class to provide function to generate a PDF
 */
public class PDFUtility {

    private static final String UTF_8 = "UTF-8";
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            generatePDF("", "C:\\temp\\test.pdf");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public static void generatePDF(String jsonData, String filename) throws Exception {
        // Setup thymeleaf engine
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(HTML);
        templateResolver.setCharacterEncoding(UTF_8);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        // Data
        CommissionREST data = createDataFromJson(jsonData);
        data.setName("Thong");
        Context context = new Context();
        context.setVariable("data", data);
        // Thymeleaf
        String renderedHtmlContent = templateEngine.process("template", context);
        String xHtml = convertToXhtml(renderedHtmlContent);
        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont("Code39.ttf", IDENTITY_H, EMBEDDED);
        // Working dir
        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "main", "resources")
                .toUri()
                .toURL()
                .toString();
        renderer.setDocumentFromString(xHtml, baseUrl);
        renderer.layout();
        // PDF
        OutputStream outputStream = new FileOutputStream(filename);
        renderer.createPDF(outputStream);
        outputStream.close();
        
    }
    
    private static CommissionREST createDataFromJson(String jsonData) {
        CommissionREST data = new CommissionREST();
        
        if (jsonData.equals("")) {
            // Call REST and map json to this data
        } else {
            // Map provided json to data 
        }
        
        return data;
    }
    
    private static String convertToXhtml(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setInputEncoding(UTF_8);
        tidy.setOutputEncoding(UTF_8);
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes(UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString(UTF_8);
    }

}
