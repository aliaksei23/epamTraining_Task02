package com.company.task02.builder;

import java.io.File;
import java.io.IOException;
import java.time.YearMonth;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.company.task02.entity.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MedicineDomBuilder {

    private Set<Medicine> medicineSet;
    private DocumentBuilder documentBuilder;

    public MedicineDomBuilder() {
        medicineSet = new HashSet<>();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Set<Medicine> getMedicine() {
        return medicineSet;
    }

    public void buildSetMedicine(String filename) {
        Document document;
        try {
            document = documentBuilder.parse(new File(filename));
            document.getDocumentElement().normalize();
            Element root = document.getDocumentElement();
            NodeList medList = root.getElementsByTagName(MedicineXMLTag.MEDICINE.getValue());
            NodeList localMedList = root.getElementsByTagName(MedicineXMLTag.LOCALMEDICINE.getValue());
            NodeList importedMedList = root.getElementsByTagName(MedicineXMLTag.IMPORTEDMEDICINE.getValue());
            for (int i = 0; i < medList.getLength(); i++) {
                Element medElement = (Element) medList.item(i);
                Medicine medicine = buildMedicine(medElement);
                medicineSet.add(medicine);
            }
            for (int i = 0; i < localMedList.getLength(); i++) {
                Element medElement = (Element) localMedList.item(i);
                LocalMedicine medicine = buildLocalMedicine(medElement);
                medicineSet.add(medicine);
            }
            for (int i = 0; i < importedMedList.getLength(); i++) {
                Element medElement = (Element) importedMedList.item(i);
                ImportedMedicine medicine = buildImportedMedicine(medElement);
                medicineSet.add(medicine);
            }
        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    private Medicine buildMedicine( Element medicineElement) {
        Medicine medicine = new Medicine();
        medicine.setGroup(Group.valueOf(medicineElement.getAttribute(MedicineXMLTag.GROUP.getValue()).toUpperCase(Locale.ROOT)));
        medicine.setIdInPharmacy(medicineElement.getAttribute(MedicineXMLTag.IDINPHARMACY.getValue()));
        medicine.setName(getElementTextContent(medicineElement, MedicineXMLTag.NAME.getValue()));
        medicine.setProducer(getElementTextContent(medicineElement, MedicineXMLTag.PRODUCER.getValue()));
        medicine.setExpirationDate(getElementYearMonthContent(medicineElement, MedicineXMLTag.EXPIRATIONDATE.getValue()));
        medicine.setAnalogs(getAnalogsContext(medicineElement));
        medicine.setVersions(buildVersionsList(medicineElement));
        return medicine;
    }

    private LocalMedicine buildLocalMedicine (Element medicineElement){
        LocalMedicine localMedicine = new LocalMedicine();
        localMedicine.setGroup(Group.valueOf(medicineElement.getAttribute(MedicineXMLTag.GROUP.getValue()).toUpperCase(Locale.ROOT)));
        localMedicine.setIdInPharmacy(medicineElement.getAttribute(MedicineXMLTag.IDINPHARMACY.getValue()));
        localMedicine.setName(getElementTextContent(medicineElement, MedicineXMLTag.NAME.getValue()));
        localMedicine.setProducer(getElementTextContent(medicineElement, MedicineXMLTag.PRODUCER.getValue()));
        localMedicine.setExpirationDate(getElementYearMonthContent(medicineElement, MedicineXMLTag.EXPIRATIONDATE.getValue()));
        localMedicine.setAnalogs(getAnalogsContext(medicineElement));
        localMedicine.setVersions(buildVersionsList(medicineElement));
        localMedicine.setRegionOfProduction(getElementTextContent(medicineElement, MedicineXMLTag.REGIONOFPRODUCTION.getValue()));
        return localMedicine;
    }

    private ImportedMedicine buildImportedMedicine (Element medicineElement){
        ImportedMedicine importedMedicine = new ImportedMedicine();
        importedMedicine.setGroup(Group.valueOf(medicineElement.getAttribute(MedicineXMLTag.GROUP.getValue()).toUpperCase(Locale.ROOT)));
        importedMedicine.setIdInPharmacy(medicineElement.getAttribute(MedicineXMLTag.IDINPHARMACY.getValue()));
        importedMedicine.setName(getElementTextContent(medicineElement, MedicineXMLTag.NAME.getValue()));
        importedMedicine.setProducer(getElementTextContent(medicineElement, MedicineXMLTag.PRODUCER.getValue()));
        importedMedicine.setExpirationDate(getElementYearMonthContent(medicineElement, MedicineXMLTag.EXPIRATIONDATE.getValue()));
        importedMedicine.setAnalogs(getAnalogsContext(medicineElement));
        importedMedicine.setVersions(buildVersionsList(medicineElement));
        importedMedicine.setCountryOfProduction(getElementTextContent(medicineElement, MedicineXMLTag.COUNTRYOFPRODUCTION.getValue()));
        return importedMedicine;
    }

    private List<Version> buildVersionsList(Element element) {
        NodeList nodeList = element.getElementsByTagName(MedicineXMLTag.VERSIONS.getValue());
        Element versionsNode = (Element) nodeList.item(0);
        NodeList versionsNodeList = versionsNode.getElementsByTagName(MedicineXMLTag.VERSION.getValue());
        List<Version> versions = new ArrayList<>();
        for (int i = 0; i < versionsNodeList.getLength(); i++) {
            versions.add(buildVersion(versionsNodeList.item(i)));
        }
        return versions;
    }

    private Version buildVersion(Node node) {
        Version version = new Version();
        Element versionElement = (Element) node;
        version.setVersionOfProduction(getElementTextContent(versionElement, MedicineXMLTag.VERSIONOFPRODUCTION.getValue()));
        version.setCertificate(buildCertificate(versionElement));
        version.setMedicinePackage(buildMedicinePackage(versionElement));
        version.setDosage(buildDosage(versionElement));
        return version;
    }

    private Certificate buildCertificate(Element element) {
        NodeList certificateList = element.getElementsByTagName(MedicineXMLTag.CERTIFICATE.getValue());
        Element certificateElement = (Element) certificateList.item(0);
        Certificate certificate = new Certificate();
        certificate.setNumber(getElementIntContent(certificateElement, MedicineXMLTag.NUMBER.getValue()));
        certificate.setIssueDate(getElementYearMonthContent(certificateElement, MedicineXMLTag.ISSUEDATE.getValue()));
        certificate.setIssueDate(getElementYearMonthContent(certificateElement, MedicineXMLTag.CERTIFICATEEXPIRATIONDATE.getValue()));
        certificate.setRegisteringOrganization(getElementTextContent(certificateElement, MedicineXMLTag.REGISTERINGORGANIZATION.getValue()));
        return certificate;
    }

    private MedicinePackage buildMedicinePackage(Element element) {
        NodeList medicinePackageList = element.getElementsByTagName(MedicineXMLTag.MEDICINEPACKAGE.getValue());
        Element medicinePackageElement = (Element) medicinePackageList.item(0);
        MedicinePackage medicinePackage = new MedicinePackage();
        medicinePackage.setTypeOfPackaging(getElementTextContent(medicinePackageElement, MedicineXMLTag.TYPEOFPACKAGING.getValue()));
        medicinePackage.setAmountInPackage(getElementIntContent(medicinePackageElement, MedicineXMLTag.AMOUNTINPACKAGE.getValue()));
        medicinePackage.setPrice(getElementDoubleContent(medicinePackageElement, MedicineXMLTag.PRICE.getValue()));
        return medicinePackage;
    }

    private Dosage buildDosage(Element element) {
        NodeList dosageList = element.getElementsByTagName(MedicineXMLTag.DOSAGE.getValue());
        Element dosageElement = (Element) dosageList.item(0);
        Dosage dosage = new Dosage();
        dosage.setDrugDosage(getElementIntContent(dosageElement, MedicineXMLTag.DRUGDOSAGE.getValue()));
        dosage.setFrequencyOfAdmission(getElementIntContent(dosageElement, MedicineXMLTag.FREQUENCYOFADMISSION.getValue()));
        return dosage;
    }

    private String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }

    private int getElementIntContent(Element element, String elementName) {
        String stringInt = getElementTextContent(element, elementName);
        return Integer.parseInt(stringInt);
    }

    private double getElementDoubleContent(Element element, String elementName) {
        String stringInt = getElementTextContent(element, elementName);
        return Double.parseDouble(stringInt);
    }

    private YearMonth getElementYearMonthContent(Element element, String tagName) {
        String yearMonthString = getElementTextContent(element, tagName);
        return YearMonth.parse(yearMonthString);
    }

    private List<String> getAnalogsContext(Element element) {
        NodeList nodeList = element.getElementsByTagName(MedicineXMLTag.ANALOGSLIST.getValue());
        Element analogsNode = (Element) nodeList.item(0);
        List<String> analogsList = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            analogsList.add(analogsNode.getTextContent());
        }
        return analogsList;
    }
}
