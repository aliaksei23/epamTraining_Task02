package com.company.task02.entity;

import java.time.YearMonth;
import java.util.List;
import java.util.StringJoiner;

public class LocalMedicine extends Medicine {
    private Medicine medicine;
    private String regionOfProduction;

    public LocalMedicine() {
    }

    public LocalMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public String getRegionOfProduction() {
        return regionOfProduction;
    }

    public void setRegionOfProduction(String regionOfProduction) {
        this.regionOfProduction = regionOfProduction;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LocalMedicine.class.getSimpleName() + "[", "]")
                .add("regionOfProduction='" + regionOfProduction + "'")
                .toString();
    }
}
