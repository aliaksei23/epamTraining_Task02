package com.company.task02.entity;

import java.util.StringJoiner;

public class Version {

    private String version;
    private Certificate certificate;
    private MedicinePackage medicinePackage;
    private Dosage dosage;

    public Version(String version, Certificate certificate, MedicinePackage MedicinePackage, Dosage dosage) {
        this.version = version;
        this.certificate = certificate;
        this.medicinePackage = MedicinePackage;
        this.dosage = dosage;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
                .add("version='" + version + "'")
                .add("certificate=" + certificate)
                .add("aMedicinePackage=" + medicinePackage)
                .add("dosage=" + dosage)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Version)) return false;

        Version version1 = (Version) o;

        if (!getVersion().equals(version1.getVersion())) return false;
        if (!getCertificate().equals(version1.getCertificate())) return false;
        if (!getMedicinePackage().equals(version1.getMedicinePackage())) return false;
        return getDosage().equals(version1.getDosage());
    }

    @Override
    public int hashCode() {
        int result = getVersion().hashCode();
        result = 31 * result + getCertificate().hashCode();
        result = 31 * result + getMedicinePackage().hashCode();
        result = 31 * result + getDosage().hashCode();
        return result;
    }
}
