package com.company.task02.builder.creators;

import com.company.task02.entity.*;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class CreateMedicine {

    public static Medicine createMedicine (){

        List<String> analogs = new ArrayList<>();
        analogs.add("Xefocam");
        analogs.add("Ketonal");

        Certificate certificate = new Certificate();
        certificate.setNumber(123);
        certificate.setIssueDate(YearMonth.of(2020, 5));
        certificate.setExpirationDate(YearMonth.of(2024, 8));
        certificate.setRegisteringOrganization("Organization1");

        MedicinePackage medicinePackage = new MedicinePackage();
        medicinePackage.setTypeOfPackaging("Plastins");
        medicinePackage.setAmountInPackage(20);
        medicinePackage.setPrice(1.4);

        Dosage dosage = new Dosage();
        dosage.setDrugDosage(200);
        dosage.setFrequencyOfAdmission(3);

        Version version = new Version();
        version.setVersionOfProduction("Tablets");
        version.setCertificate(certificate);
        version.setMedicinePackage(medicinePackage);
        version.setDosage(dosage);

        List<Version> versions = new ArrayList<>();
        versions.add(version);

        Medicine medicine = new Medicine();
        medicine.setIdInPharmacy("m01");
        medicine.setGroup(Group.getGroup(Group.PAINKILLERS.name()));
        medicine.setName("Analgin");
        medicine.setProducer("BelMed");
        medicine.setExpirationDate(YearMonth.of(2050, 5));
        medicine.setVersions(versions);
        medicine.setAnalogs(analogs);

        return medicine;
    }

}
