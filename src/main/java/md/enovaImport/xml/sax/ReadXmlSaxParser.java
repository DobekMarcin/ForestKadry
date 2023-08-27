package md.enovaImport.xml.sax;

import md.enovaImport.xml.models.ListaPlac;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class ReadXmlSaxParser {

    private static final String FILENAME = "src/main/java/md/enovaImport/xml/Lista.xml";

    public static void main(String[] args) {

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            SAXParser saxParser = factory.newSAXParser();
            HandlerSAX handler = new HandlerSAX();
            saxParser.parse(FILENAME, handler);

            List<ListaPlac> lista = handler.getListaPlac();
            System.out.println(lista);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }


    public List<ListaPlac> getImportData(String fileToImport) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();

        SAXParser saxParser = factory.newSAXParser();
        HandlerSAX handler = new HandlerSAX();
        saxParser.parse(fileToImport, handler);

        List<ListaPlac> lista = handler.getListaPlac();
        return lista;
    }
}
