package personal.ws.learning.jdk.java6;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaxTest {
    public static void main(String[] args) throws XMLStreamException, FileNotFoundException {
        readXMLByStAX();//用XMLEventReader 解析xml 文档
        writeXMLByStAX();//用XMLStreamWriter 写xml文档
    }

    private static void readXMLByStAX() throws XMLStreamException, FileNotFoundException {
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        XMLEventReader xmler = xmlif.createXMLEventReader(StaxTest.class.getResourceAsStream("oneDay.xml"));
        XMLEvent event;
        StringBuffer parsingResult = new StringBuffer();
        while (xmler.hasNext()) {
            event = xmler.nextEvent();
            if ( event.isStartElement() ) { //如果解析的是起始标记
                StartElement se = event.asStartElement();
                parsingResult.append("<");
                parsingResult.append(se.getName());
                if ( se.getName().getLocalPart().equals("catalog") ) {
                    parsingResult.append(" id=\"");
                    parsingResult.append(se.getAttributeByName(new QName("id")).getValue());
                    parsingResult.append("\"");
                }
                parsingResult.append(">");
            } else if ( event.isCharacters() ) { //如果解析的是文本内容
                parsingResult.append(event.asCharacters().getData());
            } else if ( event.isEndElement() ) { //如果解析的是结束标记
                parsingResult.append("</");
                parsingResult.append(event.asEndElement().getName());
                parsingResult.append(">");
            }
        }
        System.out.println(parsingResult);
    }

    private static void writeXMLByStAX() throws XMLStreamException, FileNotFoundException {
        XMLOutputFactory xmlof = XMLOutputFactory.newInstance();
        XMLStreamWriter xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("output.xml"));
        // 写入默认的XML 声明到xml文档
        xmlw.writeStartDocument();
        xmlw.writeCharacters("\n");
        // 写入注释到xml 文档
        xmlw.writeComment("testing comment");
        xmlw.writeCharacters("\n");
        // 写入一个catalogs根元素
        xmlw.writeStartElement("catalogs");
        xmlw.writeNamespace("myNS", "http://blog.csdn.net/Lj");
        xmlw.writeAttribute("owner", "Lj");
        xmlw.writeCharacters("\n");
        // 写入子元素catalog
        xmlw.writeStartElement("http://blog.csdn.net/Lj", "catalog");
        xmlw.writeAttribute("id", "007");
        xmlw.writeCharacters("Apparel");
        // 写入catalog元素的结束标签
        xmlw.writeEndElement();
        // 写入catalogs元素的结束标签
        xmlw.writeEndElement();
        // 结束XML 文档
        xmlw.writeEndDocument();
        xmlw.close();
    }

}
