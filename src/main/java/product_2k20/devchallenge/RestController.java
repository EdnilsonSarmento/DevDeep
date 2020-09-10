package product_2k20.devchallenge;

import com.sun.xml.internal.bind.v2.runtime.XMLSerializer;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import com.github.underscore.lodash.$;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Author: Ednilson Luis ALfredo Sarmento
 * Program: Rest Client
 * **/

@org.springframework.web.bind.annotation.RestController

public class RestController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Size of a byte buffer to read/write file
     */
    private static final int BUFFER_SIZE = 4096;


    @GetMapping("/countries")
    public List<Object> getCountries() throws SAXException, TransformerException, ParserConfigurationException, IOException {

        /*Writing The Resource URL on a String*/
        String resourceURL = "https://restcountries.eu/rest/v2/all?fields=name;capital;region;" +
                "subregion;population;area;timezones;nativename;flag";

        /* Get the Resources and assign to Objects N.B: Internet Needed */
        Object[] objects = restTemplate.getForObject(resourceURL, Object[].class);


        /*Converting Objects to a XML Format*/
        String xml = $.toXml(Arrays.asList(objects));

        try {
            File file=new File("src/main/java/product_2k20/devchallenge/arquivosExportados/fromJSON.xml");

            if(file.createNewFile()){
                // creates a FileWriter Object
                FileWriter writer = new FileWriter(file);
                // Writes the content to the file
                writer.write(xml);
                writer.flush();
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        xml2CSV();

        return Arrays.asList(objects);
    }


    /**
     * Method to Convert XML file to CSV File
     */
    public void xml2CSV() throws IOException, SAXException, ParserConfigurationException, TransformerException {

        File stylesheet = new File("src/main/java/product_2k20/devchallenge/stylesheet.xsl");
        File xmlSource = new File("src/main/java/product_2k20/devchallenge/arquivosExportados/fromJSON.xml");

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlSource);

        StreamSource stylesource = new StreamSource(stylesheet);
        Transformer transformer = TransformerFactory.newInstance()
                .newTransformer(stylesource);
        Source source = new DOMSource(document);
        Result outputTarget = new StreamResult(new File("src/main/java/product_2k20/devchallenge/arquivosExportados/fromJSON.csv"));
        transformer.transform(source, outputTarget);
    }

    /**
     * Method for handling XML file download request from client
     */
    @GetMapping("/download.do")
    public void doDownload(HttpServletRequest request,
                           HttpServletResponse response) throws IOException, TransformerException, SAXException, ParserConfigurationException {

        /**
         * Path of the file to be downloaded, relative to application's directory
         */
        String filePath = "src/main/java/product_2k20/devchallenge/arquivosExportados/fromJSON.xml";

        /**
         * Verification
         * **/
        File file = new File(filePath);
        if(!file.exists())
            getCountries();

        // get absolute path of the application
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("/");
        System.out.println("appPath = " + appPath);

        // construct the complete absolute path of the file
        String fullPath = appPath + filePath;
        File downloadFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();

    }

    /**
     * Method for handling CSV file download request from client
     */
    @GetMapping("/downloadCSVFile.do")
    public void doDownloadCSVFile(HttpServletRequest request,
                           HttpServletResponse response) throws IOException, TransformerException, SAXException, ParserConfigurationException {

        /**
         * Path of the file to be downloaded, relative to application's directory
         */
        String filePath = "src/main/java/product_2k20/devchallenge/arquivosExportados/fromJSON.csv";

        /**
         * Verification
         * **/
        File file = new File(filePath);
        if(!file.exists())
            getCountries();

        // get absolute path of the application
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("/");
        System.out.println("appPath = " + appPath);

        // construct the complete absolute path of the file
        String fullPath = appPath + filePath;
        File downloadFile = new File(filePath);
        FileInputStream inputStream = new FileInputStream(downloadFile);

        // get MIME type of the file
        String mimeType = context.getMimeType(fullPath);
        if (mimeType == null) {
            // set to binary type if MIME mapping not found
            mimeType = "application/octet-stream";
        }
        System.out.println("MIME type: " + mimeType);

        // set content attributes for the response
        response.setContentType(mimeType);
        response.setContentLength((int) downloadFile.length());

        // set headers for the response
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"",
                downloadFile.getName());
        response.setHeader(headerKey, headerValue);

        // get output stream of the response
        OutputStream outStream = response.getOutputStream();

        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead = -1;

        // write bytes read from the input stream into the output stream
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outStream.close();

    }

}
