package com.company.task02.entity;

import java.time.YearMonth;
import java.util.List;
import java.util.StringJoiner;

public class ImportedMedicine extends Medicine {

    private String countryOfProduction;

    public ImportedMedicine() {
    }

    public String getCountryOfProduction() {
        return countryOfProduction;
    }

    public void setCountryOfProduction(String countryOfProduction) {
        this.countryOfProduction = countryOfProduction;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ImportedMedicine.class.getSimpleName() + "[", "]")
                .add(super.toString())
                .add("countryOfProduction='" + countryOfProduction + "'")
                .toString();
    }
}
