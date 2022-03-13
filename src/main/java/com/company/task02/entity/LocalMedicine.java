package com.company.task02.entity;

import java.time.YearMonth;
import java.util.List;

public class LocalMedicine extends Medicine {

    private String regionOfProduction;

    public LocalMedicine(String name, String producer, Group group, YearMonth expirationDate, List<Version> version, String regionOfProduction) {
        super(name, producer, group, expirationDate, version);
        this.regionOfProduction = regionOfProduction;
    }

    public String getRegionOfProduction() {
        return regionOfProduction;
    }

    public void setRegionOfProduction(String regionOfProduction) {
        this.regionOfProduction = regionOfProduction;
    }
}
