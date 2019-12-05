package net.vivans.emsmw.emsmw.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationIntrospector;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import lombok.extern.slf4j.Slf4j;
import net.vivans.emsmw.emsmw.cmg.MeasCollecFile;
import net.vivans.emsmw.emsmw.cmm.CmmMeasInfoIdTable;
import org.springframework.boot.CommandLineRunner;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Slf4j
public class AttachCmmMeasInfoId {
    public static String inputDir;
    public static String outpurDir;
    public static String parameterTime;

    public void getCMMFiles(String[] args) throws IOException, TransformerException, ParserConfigurationException, SAXException, DatatypeConfigurationException {
        log.info("Read CMMFiles...");
        inputDir = args[1];
        outpurDir = args[2];
        parameterTime = args[3];

        Files.walk(Paths.get(inputDir)).forEach(filePath -> {
            if(Files.isRegularFile(filePath)){
                try {
                    String fileName = filePath.toFile().getName();
                    //TODO: file 관련 util로 빼는 것이 좋을 듯. 너무 중복 됨
                    String[] fileNames = fileName.split("\\.");
                    String data = fileNames[0];
                    String time = fileNames[1];

                    String[] times = time.split("-");
                    String beginTime = times[0];
                    String beginHour = beginTime.substring(0,2);
                    String beginMin = beginTime.substring(2,4);

                    String endTime = times[1];
                    String endMin = endTime.substring(2,4);

                    //00 == 00
                    boolean isHour = beginMin.equals(endMin) ? true : false;

                    if(beginHour.equals(parameterTime) && isHour){
                        this.readFile(filePath.toFile());
                    }
                } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
                    e.printStackTrace();
                }
            }
        });

        log.info("CmmFiles Outputs: {}", outpurDir);
    }

    public void readFile(File file) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        String fileName = file.getName();
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc = documentBuilder.parse(file);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("measInfo");
        for(int i=0; i < nList.getLength(); i++){
            Node node = nList.item(i);

            if(node.getNodeType() == Node.ELEMENT_NODE){

                NamedNodeMap nAttribute = node.getAttributes();
                Attr measInfoId = doc.createAttribute("measInfoId");
                String measInfoIdStr = CmmMeasInfoIdTable.getCmmMeasInfoId(i+1).getMeasInfoId();
                measInfoId.setValue(measInfoIdStr);
                nAttribute.setNamedItem(measInfoId);
            }

        }
        this.updateCmmFile(doc, fileName);

    }
    public void updateCmmFile(Document doc, String fileName) throws TransformerException, IOException, ParserConfigurationException, SAXException {
        String outputFile = outpurDir + "\\" + fileName + ".fin";
        String ouputFiles = outputFile.replace("\\",  File.separator);

        // XML 파일로 쓰기
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4"); //정렬 스페이스4칸
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //들여쓰기
        transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes"); //doc.setXmlStandalone(true); 했을때 붙어서 출력되는부분 개행

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileOutputStream(new File(ouputFiles)));

        transformer.transform(source, result);
    }



    private static String getTagValue(String tag, Element element){
        NodeList nlList = element.getElementsByTagName(tag).item(0).getChildNodes();

        Node value = (Node) nlList.item(0);
        if(value == null){
            return null;
        }
        return value.getNodeValue();
    }



}
