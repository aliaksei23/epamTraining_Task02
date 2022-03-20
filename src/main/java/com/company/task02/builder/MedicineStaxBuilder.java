package com.company.task02.builder;

import com.company.task02.entity.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;

import static com.company.task02.builder.MedicineXMLTag.*;

public class MedicineStaxBuilder {

    private Set<Medicine> medicines;
    private XMLInputFactory inputFactory;

    public MedicineStaxBuilder() {
        inputFactory = XMLInputFactory.newInstance();
        medicines = new HashSet<>();
    }

    public Set<Medicine> getMedicines() {
        return medicines;
    }

    public void buildSetMedicines(String filename) {
        XMLStreamReader reader;
        String name1;
        try (FileInputStream inputStream = new FileInputStream(new File(filename))) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            // StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name1 = reader.getLocalName();
                    if (name1.equals(MEDICINE.getValue())) {
                        Medicine medicine = buildMedicine(reader);
                        medicines.add(medicine);
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    private Medicine buildMedicine(XMLStreamReader reader) throws XMLStreamException {
        Medicine medicine = new Medicine();
        medicine.setGroup(Group.valueOf(reader.getAttributeValue(null,GROUP.getValue())));

        String name1;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name1 = reader.getLocalName();
                    MedicineXMLTag tag = valueOf(name1.toUpperCase());
                    switch (tag) {
                        case NAME -> medicine.setName(getXMLText(reader));
                        case PRODUCER -> medicine.setProducer(getXMLText(reader));
                        case EXPIRATIONDATE -> medicine.setExpirationDate(YearMonth.parse(getXMLText(reader)));
                        case ANALOGSLIST -> medicine.setAnalogs(Arrays.stream(getXMLText(reader).split(" ")).toList());
                        case VERSIONS -> medicine.setVersions(getXMLVersions(reader));
                    }
                case XMLStreamConstants.END_ELEMENT:

                  name1 = reader.getLocalName();
//                    MedicineXMLTag tag1 = getMedicineXMLTag(name);
                    MedicineXMLTag endTag = valueOf(name1.toUpperCase());
                    if (MEDICINE == endTag) {
                        return medicine;
                    }
            }
        }
        throw new XMLStreamException("End tag of Medicine is not found");
    }

    private List<Version> getXMLVersions(XMLStreamReader reader) throws XMLStreamException {
        List<Version> versions = new ArrayList<>();
        int type;
        String name1;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name1 = reader.getLocalName();
                    MedicineXMLTag tag = valueOf(name1.toUpperCase());
                    if (VERSION == tag) {
                        Version version = getXMLVersion(reader);
                        versions.add(version);
                    }
                case XMLStreamConstants.END_ELEMENT:
                    name1 = reader.getLocalName();
                    MedicineXMLTag endTag = valueOf(name1.toUpperCase());
                    if (VERSIONS == endTag) {
                        return versions;
                    }
            }
        }
        throw new XMLStreamException("End tag of Versions is not found");
    }

    private Version getXMLVersion(XMLStreamReader reader) throws XMLStreamException {
        Version version = new Version();
        int type;
        String name1;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name1 = reader.getLocalName();
                    MedicineXMLTag tag = valueOf(name1.toUpperCase());
                    switch (tag) {
                        case VERSIONOFPRODUCTION -> version.setVersionOfProduction(getXMLText(reader));
                        case CERTIFICATE -> version.setCertificate(getXMLCertificate(reader));
                        case MEDICINEPACKAGE -> version.setMedicinePackage(getXMLMedicinePackage(reader));
                        case DOSAGE -> version.setDosage(getXMLDosage(reader));
                    }
                case XMLStreamConstants.END_ELEMENT:
                    name1 = reader.getLocalName();
                    MedicineXMLTag endTag = valueOf(name1.toUpperCase());
                    if (VERSION == endTag) {
                        return version;
                    }
            }
        }
        throw new XMLStreamException("End tag of Version is not found");
    }

    private Certificate getXMLCertificate(XMLStreamReader reader) throws XMLStreamException {
        Certificate certificate = new Certificate();
        int type;
        String name1;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name1 = reader.getLocalName();
                    MedicineXMLTag tag = valueOf(name1.toUpperCase());
                    switch (tag) {
                        case NUMBER -> certificate.setNumber(Integer.parseInt(getXMLText(reader)));
                        case ISSUEDATE -> certificate.setIssueDate(YearMonth.parse(getXMLText(reader)));
                        case CERTIFICATEEXPIRATIONDATE -> certificate.setExpirationDate(YearMonth.parse(getXMLText(reader)));
                        case REGISTERINGORGANIZATION -> certificate.setRegisteringOrganization(getXMLText(reader));
                    }
                case XMLStreamConstants.END_ELEMENT:
                    name1 = reader.getLocalName();
                    MedicineXMLTag endTag = valueOf(name1.toUpperCase());
                    if (CERTIFICATE == endTag) {
                        return certificate;
                    }
            }
        }
        throw new XMLStreamException("End tag of Certificate is not found");
    }

    private MedicinePackage getXMLMedicinePackage(XMLStreamReader reader) throws XMLStreamException {
        MedicinePackage medicinePackage = new MedicinePackage();
        int type;
        String name1;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name1 = reader.getLocalName();
                    MedicineXMLTag tag = valueOf(name1.toUpperCase());
                    switch (tag) {
                        case TYPEOFPACKAGING -> medicinePackage.setTypeOfPackaging(getXMLText(reader));
                        case AMOUNTINPACKAGE -> medicinePackage.setAmountInPackage(Integer.parseInt(getXMLText(reader)));
                        case PRICE -> medicinePackage.setPrice(Double.parseDouble(getXMLText(reader)));
                    }
                case XMLStreamConstants.END_ELEMENT:
                    name1 = reader.getLocalName();
                    MedicineXMLTag endTag = valueOf(name1.toUpperCase());
                    if (MEDICINEPACKAGE == endTag) {
                        return medicinePackage;
                    }
            }
        }
        throw new XMLStreamException("End tag of MedicinePackage is not found");
    }

    private Dosage getXMLDosage(XMLStreamReader reader) throws XMLStreamException {
        Dosage dosage = new Dosage();
        int type;
        String name1;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name1 = reader.getLocalName();
                    MedicineXMLTag tag = valueOf(name1.toUpperCase());
                    switch (tag) {
                        case DRUGDOSAGE -> dosage.setDrugDosage(Integer.parseInt(getXMLText(reader)));
                        case FREQUENCYOFADMISSION -> dosage.setFrequencyOfAdmission(Integer.parseInt(getXMLText(reader)));
                    }
                case XMLStreamConstants.END_ELEMENT:
                    name1 = reader.getLocalName();
                    MedicineXMLTag endTag = valueOf(name1.toUpperCase());
                    if (DOSAGE == endTag) {
                        return dosage;
                    }
            }
        }
        throw new XMLStreamException("End tag of Dosage is not found");
    }


    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}