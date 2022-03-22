package com.company.task02.builder;

import com.company.task02.builder.tegs.MedicineXMLTag;
import com.company.task02.entity.*;
import com.company.task02.validator.XmlValidator;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.YearMonth;
import java.util.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import static com.company.task02.builder.tegs.MedicineXMLTag.*;

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
        if (XmlValidator.xmlValidator()) {
            XMLStreamReader reader;
            String name;
            try (FileInputStream inputStream = new FileInputStream(filename)) {
                reader = inputFactory.createXMLStreamReader(inputStream);
                while (reader.hasNext()) {
                    int type = reader.next();
                    if (type == XMLStreamConstants.START_ELEMENT) {
                        name = reader.getLocalName();
                        if (name.equals(MEDICINE.getValue())) {
                            Medicine medicine = buildMedicine(new Medicine(), reader);
                            medicines.add(medicine);
                        }
                        if (name.equalsIgnoreCase(LOCALMEDICINE.getValue())) {
                            Medicine medicine = buildMedicine(new LocalMedicine(), reader);
                            medicines.add(medicine);
                        }
                        if (name.equalsIgnoreCase(IMPORTEDMEDICINE.getValue())) {
                            Medicine medicine = buildMedicine(new ImportedMedicine(), reader);
                            medicines.add(medicine);
                        }
                    }
                }
            } catch (XMLStreamException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Medicine buildMedicine(Medicine medicine, XMLStreamReader reader) throws XMLStreamException {
        medicine.setGroup(Group.getGroup(reader.getAttributeValue(null, GROUP.getValue())));
        medicine.setIdInPharmacy(reader.getAttributeValue(null, IDINPHARMACY.getValue()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    MedicineXMLTag tag = valueOf(name.toUpperCase());
                    switch (tag) {
                        case NAME -> medicine.setName(getXMLText(reader));
                        case PRODUCER -> medicine.setProducer(getXMLText(reader));
                        case EXPIRATIONDATE -> medicine.setExpirationDate(YearMonth.parse(getXMLText(reader)));
                        case ANALOGSLIST -> medicine.setAnalogs(Arrays.stream(getXMLText(reader).split(" ")).toList());
                        case VERSIONS -> medicine.setVersions(getXMLVersions(reader));
                        case REGIONOFPRODUCTION -> {
                            LocalMedicine localMedicine = (LocalMedicine) medicine;
                            localMedicine.setRegionOfProduction(getXMLText(reader));
                        }
                        case COUNTRYOFPRODUCTION -> {
                            ImportedMedicine importedMedicine = (ImportedMedicine) medicine;
                            importedMedicine.setCountryOfProduction(getXMLText(reader));
                        }
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {

                    name = reader.getLocalName();
                    MedicineXMLTag endTag = valueOf(name.toUpperCase());
                    if (MEDICINE == endTag || LOCALMEDICINE == endTag || IMPORTEDMEDICINE == endTag) {
                        return medicine;
                    }
                }
            }
        }
        throw new XMLStreamException("End tag of Medicine is not found");
    }

    private List<Version> getXMLVersions(XMLStreamReader reader) throws XMLStreamException {
        List<Version> versions = new ArrayList<>();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    MedicineXMLTag tag = valueOf(name.toUpperCase());
                    if (VERSION == tag) {
                        Version version = getXMLVersion(reader);
                        versions.add(version);
                    }
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    MedicineXMLTag endTag = valueOf(name.toUpperCase());
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
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    MedicineXMLTag tag = valueOf(name.toUpperCase());
                    switch (tag) {
                        case VERSIONOFPRODUCTION -> version.setVersionOfProduction(getXMLText(reader));
                        case CERTIFICATE -> version.setCertificate(getXMLCertificate(reader));
                        case MEDICINEPACKAGE -> version.setMedicinePackage(getXMLMedicinePackage(reader));
                        case DOSAGE -> version.setDosage(getXMLDosage(reader));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    MedicineXMLTag endTag = valueOf(name.toUpperCase());
                    if (VERSION == endTag) {
                        return version;
                    }
                }
            }
        }
        throw new XMLStreamException("End tag of Version is not found");
    }

    private Certificate getXMLCertificate(XMLStreamReader reader) throws XMLStreamException {
        Certificate certificate = new Certificate();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    MedicineXMLTag tag = valueOf(name.toUpperCase());
                    switch (tag) {
                        case NUMBER -> certificate.setNumber(Integer.parseInt(getXMLText(reader)));
                        case ISSUEDATE -> certificate.setIssueDate(YearMonth.parse(getXMLText(reader)));
                        case CERTIFICATEEXPIRATIONDATE -> certificate.setExpirationDate(YearMonth.parse(getXMLText(reader)));
                        case REGISTERINGORGANIZATION -> certificate.setRegisteringOrganization(getXMLText(reader));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    MedicineXMLTag endTag = valueOf(name.toUpperCase());
                    if (CERTIFICATE == endTag) {
                        return certificate;
                    }
                }
            }
        }
        throw new XMLStreamException("End tag of Certificate is not found");
    }

    private MedicinePackage getXMLMedicinePackage(XMLStreamReader reader) throws XMLStreamException {
        MedicinePackage medicinePackage = new MedicinePackage();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    MedicineXMLTag tag = valueOf(name.toUpperCase());
                    switch (tag) {
                        case TYPEOFPACKAGING -> medicinePackage.setTypeOfPackaging(getXMLText(reader));
                        case AMOUNTINPACKAGE -> medicinePackage.setAmountInPackage(Integer.parseInt(getXMLText(reader)));
                        case PRICE -> medicinePackage.setPrice(Double.parseDouble(getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    MedicineXMLTag endTag = valueOf(name.toUpperCase());
                    if (MEDICINEPACKAGE == endTag) {
                        return medicinePackage;
                    }
                }
            }
        }
        throw new XMLStreamException("End tag of MedicinePackage is not found");
    }

    private Dosage getXMLDosage(XMLStreamReader reader) throws XMLStreamException {
        Dosage dosage = new Dosage();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    name = reader.getLocalName();
                    MedicineXMLTag tag = valueOf(name.toUpperCase());
                    switch (tag) {
                        case DRUGDOSAGE -> dosage.setDrugDosage(Integer.parseInt(getXMLText(reader)));
                        case FREQUENCYOFADMISSION -> dosage.setFrequencyOfAdmission(Integer.parseInt(getXMLText(reader)));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    name = reader.getLocalName();
                    MedicineXMLTag endTag = valueOf(name.toUpperCase());
                    if (DOSAGE == endTag) {
                        return dosage;
                    }
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
