package com.company.task02.entity;

import java.util.StringJoiner;

public class Version {

    private String versionOfProduction;
    private Certificate certificate = new Certificate();
    private MedicinePackage medicinePackage = new MedicinePackage();
    private Dosage dosage = new Dosage();

    public Version(String versionOfProduction, Certificate certificate, MedicinePackage MedicinePackage, Dosage dosage) {
        this.versionOfProduction = versionOfProduction;
        this.certificate = certificate;
        this.medicinePackage = MedicinePackage;
        this.dosage = dosage;
    }

    public Version() {
    }

    public String getVersionOfProduction() {
        return versionOfProduction;
    }

    public void setVersionOfProduction(String versionOfProduction) {
        this.versionOfProduction = versionOfProduction;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public MedicinePackage getMedicinePackage() {
        return medicinePackage;
    }

    public void setMedicinePackage(MedicinePackage medicinePackage) {
        this.medicinePackage = medicinePackage;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Version.class.getSimpleName() + "[", "]")
                .add("version='" + versionOfProduction + "'")
                .add("certificate=" + certificate)
                .add("MedicinePackage=" + medicinePackage)
                .add("dosage=" + dosage)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Version)) return false;

        Version version1 = (Version) o;

        if (!getVersionOfProduction().equals(version1.getVersionOfProduction())) return false;
        if (!getCertificate().equals(version1.getCertificate())) return false;
        if (!getMedicinePackage().equals(version1.getMedicinePackage())) return false;
        return getDosage().equals(version1.getDosage());
    }

    @Override
    public int hashCode() {
        int result = getVersionOfProduction().hashCode();
        result = 31 * result + getCertificate().hashCode();
        result = 31 * result + getMedicinePackage().hashCode();
        result = 31 * result + getDosage().hashCode();
        return result;
    }
}
