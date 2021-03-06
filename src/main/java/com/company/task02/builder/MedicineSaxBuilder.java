package com.company.task02.builder;

import com.company.task02.builder.handler.MedicineHandler;
import com.company.task02.entity.Medicine;
import com.company.task02.validator.XmlValidator;
import com.company.task02.validator.error.MedicinsErrorHandler;
import org.xml.sax.XMLReader;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class MedicineSaxBuilder {

    private Set<Medicine> medicineSet;
    private MedicineHandler medicineHandler = new MedicineHandler();
    private XMLReader reader;

    public MedicineSaxBuilder() {
        if (XmlValidator.xmlValidator()) {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            try {
                SAXParser saxParser = factory.newSAXParser();
                reader = saxParser.getXMLReader();
            } catch (ParserConfigurationException | SAXException e) {
                e.printStackTrace();
            }
            reader.setErrorHandler(new MedicinsErrorHandler());
            reader.setContentHandler(medicineHandler);
        }
    }

    public Set<Medicine> getMedicine() {
        return medicineSet;
    }

    public void buildSetMedicines(String filename) {
        try {
            reader.parse(filename);
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
        medicineSet = medicineHandler.getMedicineSet();
    }

}
