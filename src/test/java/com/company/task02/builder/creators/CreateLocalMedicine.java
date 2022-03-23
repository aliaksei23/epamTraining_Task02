package com.company.task02.builder.creators;

import com.company.task02.entity.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class CreateLocalMedicine {

    public static LocalMedicine createLocalMedicine (){
        List<String> analogs = new ArrayList<>();
        analogs.add("Arbidol");
        analogs.add("Tamiflu");

        Certificate certificate = new Certificate();
        certificate.setNumber(06);
        certificate.setIssueDate(YearMonth.of(2020, 11));
        certificate.setExpirationDate(YearMonth.of(2027,1));
        certificate.setRegisteringOrganization("OrganizationLocal2");

        MedicinePackage medicinePackage = new MedicinePackage();
        medicinePackage.setTypeOfPackaging("vial");
        medicinePackage.setAmountInPackage(40);
        medicinePackage.setPrice(25);

        Dosage dosage = new Dosage();
        dosage.setDrugDosage(200);
        dosage.setFrequencyOfAdmission(2);

        Version version = new Version();
        version.setVersionOfProduction("Capsules");
        version.setCertificate(certificate);
        version.setMedicinePackage(medicinePackage);
        version.setDosage(dosage);

        List<Version> versions = new ArrayList<>();
        versions.add(version);

       LocalMedicine localMedicine = new LocalMedicine();
        localMedicine.setIdInPharmacy("lm06");
        localMedicine.setGroup(Group.getGroup(Group.ANTIVIRAL.name()));
        localMedicine.setName("Arbidol Maximum");
        localMedicine.setProducer("LocalOrganization");
        localMedicine.setExpirationDate(YearMonth.of(2027, 8));
        localMedicine.setVersions(versions);
        localMedicine.setAnalogs(analogs);
        localMedicine.setRegionOfProduction("LocalRegion");

        return localMedicine;
    }

}
