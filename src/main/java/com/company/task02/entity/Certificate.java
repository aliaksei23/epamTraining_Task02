package com.company.task02.entity;

import java.time.YearMonth;
import java.util.StringJoiner;

public class Certificate {

    private int number;
    private YearMonth issueDate;
    private YearMonth expirationDate;
    private String registeringOrganization;

    public Certificate(int number, YearMonth issueDate, YearMonth expirationDate, String registeringOrganization) {
        this.number = number;
        this.issueDate = issueDate;
        this.expirationDate = expirationDate;
        this.registeringOrganization = registeringOrganization;
    }

    public Certificate() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public YearMonth getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(YearMonth issueDate) {
        this.issueDate = issueDate;
    }

    public YearMonth getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(YearMonth expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getRegisteringOrganization() {
        return registeringOrganization;
    }

    public void setRegisteringOrganization(String registeringOrganization) {
        this.registeringOrganization = registeringOrganization;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Certificate.class.getSimpleName() + "[", "]")
                .add("number=" + number)
                .add("issueDate=" + issueDate)
                .add("expirationDate=" + expirationDate)
                .add("registeringOrganization='" + registeringOrganization + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Certificate)) return false;

        Certificate that = (Certificate) o;

        if (getNumber() != that.getNumber()) return false;
        if (!getIssueDate().equals(that.getIssueDate())) return false;
        if (!getExpirationDate().equals(that.getExpirationDate())) return false;
        return getRegisteringOrganization().equals(that.getRegisteringOrganization());
    }

    @Override
    public int hashCode() {
        int result = getNumber();
        result = 31 * result + getIssueDate().hashCode();
        result = 31 * result + getExpirationDate().hashCode();
        result = 31 * result + getRegisteringOrganization().hashCode();
        return result;
    }
}
