package com.company.task02.builder.creators;

import com.company.task02.entity.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class CreateImportedMedicine {

    public static ImportedMedicine createImportedMedicine() {

        List<String> analogs = new ArrayList<>();
        analogs.add("Biorhythm");
        analogs.add("Makrovit");

        Certificate certificate = new Certificate();
        certificate.setNumber(879);
        certificate.setIssueDate(YearMonth.of(2021, 8));
        certificate.setExpirationDate(YearMonth.of(2028,5));
        certificate.setRegisteringOrganization("Organization4");

        MedicinePackage medicinePackage = new MedicinePackage();
        medicinePackage.setTypeOfPackaging("vial");
        medicinePackage.setAmountInPackage(50);
        medicinePackage.setPrice(12.8);

        Dosage dosage = new Dosage();
        dosage.setDrugDosage(150);
        dosage.setFrequencyOfAdmission(1);

        Version version = new Version();
        version.setVersionOfProduction("Pills");
        version.setCertificate(certificate);
        version.setMedicinePackage(medicinePackage);
        version.setDosage(dosage);

        List<Version> versions = new ArrayList<>();
        versions.add(version);

        ImportedMedicine importedMedicine = new ImportedMedicine();
        importedMedicine.setIdInPharmacy("im03");
        importedMedicine.setGroup(Group.getGroup(Group.VITAMINS.name()));
        importedMedicine.setName("AlfaVit Energy");
        importedMedicine.setProducer("ForeignerOrganization");
        importedMedicine.setExpirationDate(YearMonth.of(2027, 8));
        importedMedicine.setVersions(versions);
        importedMedicine.setAnalogs(analogs);
        importedMedicine.setCountryOfProduction("ForeignerCountry");

        return importedMedicine;
    }

}
