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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImportedMedicine)) return false;
        if (!super.equals(o)) return false;

        ImportedMedicine that = (ImportedMedicine) o;

        return getCountryOfProduction().equals(that.getCountryOfProduction());
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + getCountryOfProduction().hashCode();
        return result;
    }
}
