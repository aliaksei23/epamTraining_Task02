package com.company.task02.entity;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Medicine {

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

    @Override
    public String toString() {
        return new StringJoiner(", ", Medicine.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("producer='" + producer + "'")
                .add("group=" + group)
                .add("expirationDate=" + expirationDate)
                .add("analogs=" + analogs)
                .add("versions=" + versions)
                .toString();
    }
}
