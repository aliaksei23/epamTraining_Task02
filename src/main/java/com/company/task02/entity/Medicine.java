package com.company.task02.entity;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Medicine {

    private String idInPharmacy;
    private String name;
    private String producer;
    private Group group;
    private YearMonth expirationDate;
    private List<String> analogs;
    private List<Version> versions;

    public Medicine() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    public List<String> getAnalogs() {
        return analogs;
    }

    public void setAnalogs(List<String> analogs) {
        this.analogs = analogs;
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(YearMonth expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getIdInPharmacy() {
        return idInPharmacy;
    }

    public void setIdInPharmacy(String idInPharmacy) {
        this.idInPharmacy = idInPharmacy;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Medicine.class.getSimpleName() + "[", "]")
                .add("idInPharmacy='" + idInPharmacy + "'")
                .add("name='" + name + "'")
                .add("producer='" + producer + "'")
                .add("group=" + group)
                .add("expirationDate=" + expirationDate)
                .add("analogs=" + analogs)
                .add("versions=" + versions)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicine)) return false;

        Medicine medicine = (Medicine) o;

        if (!getIdInPharmacy().equals(medicine.getIdInPharmacy())) return false;
        if (!getName().equals(medicine.getName())) return false;
        if (!getProducer().equals(medicine.getProducer())) return false;
        if (getGroup() != medicine.getGroup()) return false;
        if (!getExpirationDate().equals(medicine.getExpirationDate())) return false;
        if (!getAnalogs().equals(medicine.getAnalogs())) return false;
        return getVersions().equals(medicine.getVersions());
    }

    @Override
    public int hashCode() {
        int result = getIdInPharmacy().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getProducer().hashCode();
        result = 31 * result + getGroup().hashCode();
        result = 31 * result + getExpirationDate().hashCode();
        result = 31 * result + getAnalogs().hashCode();
        result = 31 * result + getVersions().hashCode();
        return result;
    }
}
