package com.company.task02.validator;

import com.company.task02.validator.error.MedicinsErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidator {

    public static void xmlValidator() {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        String fileName = "E:\\alex\\epamTraining\\epamTraining_Task02\\src\\main\\resources\\medicines.xml";
        String schemaName = "E:\\alex\\epamTraining\\epamTraining_Task02\\src\\main\\resources\\medicines.xsd";
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaLocation = new File(schemaName);
        try {
            // schema creation
            Schema schema = factory.newSchema(schemaLocation);
            // creating a schema-based validator
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileName);
            // document check
            validator.setErrorHandler(new MedicinsErrorHandler());
            validator.validate(source);
        } catch (SAXException | IOException e) {
            System.err.println(fileName + " is not correct or valid");
        }
    }

}
