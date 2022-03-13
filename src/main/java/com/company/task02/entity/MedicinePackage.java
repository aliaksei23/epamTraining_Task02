package com.company.task02.entity;

import java.util.StringJoiner;

public class MedicinePackage {

    private String typeOfPackaging;
    private int amountInPackage;
    private double price;

    public MedicinePackage(String typeOfPackaging, int amountInPackage, double price) {
        this.typeOfPackaging = typeOfPackaging;
        this.amountInPackage = amountInPackage;
        this.price = price;
    }

    public String getTypeOfPackaging() {
        return typeOfPackaging;
    }

    public void setTypeOfPackaging(String typeOfPackaging) {
        this.typeOfPackaging = typeOfPackaging;
    }

    public int getAmountInPackage() {
        return amountInPackage;
    }

    public void setAmountInPackage(int amountInPackage) {
        this.amountInPackage = amountInPackage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MedicinePackage.class.getSimpleName() + "[", "]")
                .add("typeOfPackaging='" + typeOfPackaging + "'")
                .add("amountInPackage=" + amountInPackage)
                .add("price=" + price)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MedicinePackage)) return false;

        MedicinePackage that = (MedicinePackage) o;

        if (getAmountInPackage() != that.getAmountInPackage()) return false;
        if (Double.compare(that.getPrice(), getPrice()) != 0) return false;
        return getTypeOfPackaging().equals(that.getTypeOfPackaging());
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getTypeOfPackaging().hashCode();
        result = 31 * result + getAmountInPackage();
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
