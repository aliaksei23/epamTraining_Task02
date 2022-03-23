package com.company.task02.entity;

import java.time.YearMonth;
import java.util.List;
import java.util.StringJoiner;

public class LocalMedicine extends Medicine {
    private String regionOfProduction;

    public LocalMedicine() {
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
                .add(super.toString())
                .add("regionOfProduction='" + regionOfProduction + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocalMedicine)) return false;
        if (!super.equals(o)) return false;

        LocalMedicine that = (LocalMedicine) o;

        return getRegionOfProduction().equals(that.getRegionOfProduction());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getRegionOfProduction().hashCode();
        return result;
    }
}
