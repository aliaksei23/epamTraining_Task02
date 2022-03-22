package com.company.task02.builder.tegs;

public enum MedicineXMLTag {
    MEDICINES("medicines"),
    MEDICINE("medicine"),
    LOCALMEDICINE("localMedicine"),
    IMPORTEDMEDICINE("importedMedicine"),

    NAME("name"),
    REGIONOFPRODUCTION("regionOfProduction"),
    COUNTRYOFPRODUCTION("countryOfProduction"),
    GROUP("group"),
    IDINPHARMACY("idInPharmacy"),
    ANALOGSLIST("analogsList"),
    PRODUCER("producer"),
    EXPIRATIONDATE("expirationDate"),
    CERTIFICATEEXPIRATIONDATE("certificateExpirationDate"),
    VERSIONOFPRODUCTION("versionOfProduction"),
    NUMBER("number"),
    ISSUEDATE("issueDate"),
    REGISTERINGORGANIZATION("registeringOrganization"),
    TYPEOFPACKAGING("typeOfPackaging"),
    AMOUNTINPACKAGE("amountInPackage"),
    PRICE("price"),
    DRUGDOSAGE("drugDosage"),
    FREQUENCYOFADMISSION("frequencyOfAdmission"),
    //withComplexStructure;
    VERSION("version"),// object
    CERTIFICATE("certificate"),// object
    MEDICINEPACKAGE("medicinePackage"),//object
    DOSAGE("dosage"),//object
    //withListStructure
    VERSIONS("versions");// no text contains

    private String value;

    MedicineXMLTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static MedicineXMLTag getMedicineXMLTag(String name)  {
        for (MedicineXMLTag tag : MedicineXMLTag.values()) {
            if (name.equals(tag.getValue())) {
                return tag;
            }
        }
        throw new IllegalArgumentException("Unknown tag <" + name + ">");
    }
}
