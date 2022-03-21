package com.company.task02.builder;

import com.company.task02.entity.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

import java.time.YearMonth;
import java.util.*;

import static com.company.task02.builder.MedicineXMLTag.*;

public class MedicineHandler extends DefaultHandler {

    private Set<Medicine> medicineSet;
    private Medicine currentMed;
    private Version currentVersion;
    private Certificate currentCertificate;
    private MedicinePackage currentMedicinePackage;
    private Dosage currentDosage;
    private MedicineXMLTag currentTextXmlTag;
    private MedicineXMLTag currentListXmlTag;
    private MedicineXMLTag currentObjectXmlTag;
    private EnumSet<MedicineXMLTag> withText;
    private EnumSet<MedicineXMLTag> withObjectStructure;
    private EnumSet<MedicineXMLTag> withListStructure;
    private EnumSet<MedicineXMLTag> allMedicines;

    public MedicineHandler() {
        medicineSet = new HashSet<>();
        withText = EnumSet.range(NAME, FREQUENCYOFADMISSION);
        withObjectStructure = EnumSet.range(VERSION, DOSAGE);
        withListStructure = EnumSet.of(VERSIONS);
        allMedicines = EnumSet.range(MEDICINE, IMPORTEDMEDICINE);
    }

    public Set<Medicine> getMedicineSet() {
        return medicineSet;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        MedicineXMLTag xmlTag = valueOf(qName.toUpperCase());
        if (allMedicines.contains(xmlTag)) {
            if (xmlTag == MEDICINE) {
                this.currentMed = new Medicine();
                currentMed.setIdInPharmacy(attrs.getValue(attrs.getIndex(IDINPHARMACY.getValue())));
            } else if (xmlTag == IMPORTEDMEDICINE) {
                this.currentMed = new ImportedMedicine();
                currentMed.setIdInPharmacy(attrs.getValue(attrs.getIndex(IDINPHARMACY.getValue())));
            } else if (xmlTag == LOCALMEDICINE) {
                this.currentMed = new LocalMedicine();
                currentMed.setIdInPharmacy(attrs.getValue(attrs.getIndex(IDINPHARMACY.getValue())));
            }
            this.currentMed.setGroup(Group.getGroup(attrs.getValue(0)));
        } else {
            if (withListStructure.contains(xmlTag)) {
                currentListXmlTag = xmlTag;
            }
            if (withObjectStructure.contains(xmlTag)) {
                currentObjectXmlTag = xmlTag;
            }
            if (withText.contains(xmlTag)) {
                currentTextXmlTag = xmlTag;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (allMedicines.contains(valueOf(qName.toUpperCase()))) {
            medicineSet.add(currentMed);
        } else if (VERSION.getValue().equalsIgnoreCase(qName)) {
            currentMed.getVersions().add(currentVersion);
        } else if (CERTIFICATE.getValue().equalsIgnoreCase(qName)) {
            currentVersion.setCertificate(currentCertificate);
        } else if (MEDICINEPACKAGE.getValue().equalsIgnoreCase(qName)) {
            currentVersion.setMedicinePackage(currentMedicinePackage);
        } else if (DOSAGE.getValue().equalsIgnoreCase(qName)) {
            currentVersion.setDosage(currentDosage);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String data = new String(ch, start, length).strip();

        if (currentListXmlTag != null) {
            currentMed.setVersions(new ArrayList<>());
            currentListXmlTag = null;
        } else if (currentObjectXmlTag != null) {
            switch (currentObjectXmlTag) {
                case VERSION -> currentVersion = new Version();
                case CERTIFICATE -> currentCertificate = new Certificate();
                case MEDICINEPACKAGE -> currentMedicinePackage = new MedicinePackage();
                case DOSAGE -> currentDosage = new Dosage();
            }
            currentObjectXmlTag = null;
        } else if (currentTextXmlTag != null) {
            switch (currentTextXmlTag) {
                case GROUP -> currentMed.setGroup(Group.ANTIBIOTICS);
                case IDINPHARMACY -> currentMed.setIdInPharmacy(data);
                case NAME -> currentMed.setName(data);
                case ANALOGSLIST -> currentMed.setAnalogs(Arrays.stream(data.split(" ")).toList());
                case PRODUCER -> currentMed.setProducer(data);
                case EXPIRATIONDATE -> currentMed.setExpirationDate(YearMonth.parse(data));

                case VERSIONOFPRODUCTION -> currentVersion.setVersionOfProduction(data);
                case NUMBER -> currentCertificate.setNumber(Integer.parseInt(data));
                case ISSUEDATE -> currentCertificate.setIssueDate(YearMonth.parse(data));
                case CERTIFICATEEXPIRATIONDATE -> currentCertificate.setExpirationDate(YearMonth.parse(data));
                case REGISTERINGORGANIZATION -> currentCertificate.setRegisteringOrganization(data);
                case TYPEOFPACKAGING -> currentMedicinePackage.setTypeOfPackaging(data);
                case AMOUNTINPACKAGE -> currentMedicinePackage.setAmountInPackage(Integer.parseInt(data));
                case PRICE -> currentMedicinePackage.setPrice(Double.parseDouble(data));
                case DRUGDOSAGE -> currentDosage.setDrugDosage(Integer.parseInt(data));
                case FREQUENCYOFADMISSION -> currentDosage.setFrequencyOfAdmission(Integer.parseInt(data));

                case REGIONOFPRODUCTION -> ((LocalMedicine) currentMed).setRegionOfProduction(data);
                case COUNTRYOFPRODUCTION -> ((ImportedMedicine) currentMed).setCountryOfProduction(data);

            }
            currentTextXmlTag = null;
        }
    }
}
