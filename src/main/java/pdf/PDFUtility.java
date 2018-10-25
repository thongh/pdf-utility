package pdf;

import org.thymeleaf.TemplateEngine;


import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import model.CommissionREST;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.fasterxml.jackson.databind.ObjectMapper;

import static com.itextpdf.text.pdf.BaseFont.EMBEDDED;
import static com.itextpdf.text.pdf.BaseFont.IDENTITY_H;

import static org.thymeleaf.templatemode.TemplateMode.HTML;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Base64;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
/**
 * This is class to provide function to generate a PDF
 */
public class PDFUtility {

    private static final String UTF_8 = "UTF-8";
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try {
            generateCommissionReport("MS1", "01-09-2018", "30-09-2018", "C:\\temp\\test.pdf");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public static void generateCommissionReport(String branchCode, String fromDate, 
            String toDate, String reportName) throws Exception {
        // Setup thymeleaf engine
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setPrefix("/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(HTML);
        templateResolver.setCharacterEncoding(UTF_8);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        // Get data
        String restEndpoint = "https://192.168.100.64:8082/pos-api/";
        String restResource = "reports/commissions?branchCode=MS1&fromDate=01-09-2018&toDate=30-09-2018";
        String jsonData = callREST(restEndpoint, restResource, "GET", "", "");
        CommissionREST data = createDataFromJson(jsonData);
        Context context = new Context();
        context.setVariable("data", data);
        context.setVariable("fromDate", fromDate);
        context.setVariable("toDate", toDate);
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
        OutputStream outputStream = new FileOutputStream(reportName);
        renderer.createPDF(outputStream);
        outputStream.close();
        
    }
    
    private static CommissionREST createDataFromJson(String jsonData) {
        CommissionREST data = new CommissionREST();
        ObjectMapper mapper = new ObjectMapper();
        try {
            data = mapper.readValue(jsonData, CommissionREST.class);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
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
    
    public static String callREST(String restEndpoint, String restResource, String method,
            String username, String password) throws IOException {
        disableSslVerification();
        StringBuffer stringBuffer = new StringBuffer();
        URL url = null;
        try {
            String restUrl = "";
            if (restEndpoint == "") {
                restEndpoint = "http://192.168.101.225:8082/pos-api/";
            }
            
            if (restResource == "") {
                restResource = "inventories/warehouses";
            }

            if (method == "") {
                method = "GET";
            }
            
            restUrl = restEndpoint + restResource;
                
            url = new URL(restUrl);
            
            // Open connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // Set method       
            conn.setDoOutput(true);
            conn.setRequestMethod(method);
            // Request header
            conn.setRequestProperty("Source-Type", "WEB");
            conn.setRequestProperty("Source-Name", "BPM");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Accept-Language", "en");
            
            // Auth
            if (username == "") {
                username = "bpmapiuser";
            }
            if (password == "") {
                password = "APiU53rBpM";
            }
            String encoded = Base64.getEncoder().encodeToString((username+":"+password).getBytes(StandardCharsets.UTF_8));  //Java 8
            conn.setRequestProperty("Authorization", "Basic "+encoded);
                    
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            
            String output;
            while ((output = br.readLine()) != null) {
                stringBuffer.append(output);
            }                       
            conn.disconnect();          
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }               
        return stringBuffer.toString();
    }
    
    private static void disableSslVerification() {
        try
        {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };
            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

}
