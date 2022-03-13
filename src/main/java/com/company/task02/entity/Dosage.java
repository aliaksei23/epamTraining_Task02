package com.company.task02.entity;

import java.util.StringJoiner;

public class Dosage {

    private double drugDosage;
    private int frequencyOfAdmission;

    public Dosage(double drugDosage, int frequencyOfAdmission) {
        this.drugDosage = drugDosage;
        this.frequencyOfAdmission = frequencyOfAdmission;
    }

    public double getDrugDosage() {
        return drugDosage;
    }

    public void setDrugDosage(double drugDosage) {
        this.drugDosage = drugDosage;
    }

    public int getFrequencyOfAdmission() {
        return frequencyOfAdmission;
    }

    public void setFrequencyOfAdmission(int frequencyOfAdmission) {
        this.frequencyOfAdmission = frequencyOfAdmission;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Dosage.class.getSimpleName() + "[", "]")
                .add("drugDosage=" + drugDosage)
                .add("frequencyOfAdmission=" + frequencyOfAdmission)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dosage)) return false;

        Dosage dosage = (Dosage) o;

        if (Double.compare(dosage.getDrugDosage(), getDrugDosage()) != 0) return false;
        return getFrequencyOfAdmission() == dosage.getFrequencyOfAdmission();
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(getDrugDosage());
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + getFrequencyOfAdmission();
        return result;
    }
}
