package com.company.task02.entity;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class Medicine {

    private String name;
    private String producer;
    private Group group;
    private YearMonth expirationDate;
    private List<String> analogs;
    private List<Version> version;

    public Medicine() {
    }

    public Medicine(String name, String producer, Group group, YearMonth expirationDate, List<Version> version) {
        this.name = name;
        this.producer = producer;
        this.group = group;
        this.expirationDate = expirationDate;
        this.version = version;
        analogs = new ArrayList<>();

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

    public List<Version> getVersion() {
        return version;
    }

    public void setVersion(List<Version> version) {
        this.version = version;
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
}
