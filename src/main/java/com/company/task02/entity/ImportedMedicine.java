package com.company.task02.entity;

import java.time.YearMonth;
import java.util.List;

public class ImportedMedicine extends Medicine {

    private String countryOfProduction;

    public ImportedMedicine(String name, String producer, Group group, YearMonth expirationDate, List<Version> version, String countryOfProduction) {
        super(name, producer, group, expirationDate, version);
        this.countryOfProduction = countryOfProduction;
    }

    public String getCountryOfProduction() {
        return countryOfProduction;
    }

    public void setCountryOfProduction(String countryOfProduction) {
        this.countryOfProduction = countryOfProduction;
    }
}
