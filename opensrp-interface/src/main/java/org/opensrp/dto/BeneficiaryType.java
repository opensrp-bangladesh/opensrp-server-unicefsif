package org.opensrp.dto;

public enum BeneficiaryType {
    members("members"),
    household("household");
    
    private String value;

    BeneficiaryType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static BeneficiaryType from(String beneficiaryType) {
        return valueOf(beneficiaryType);
    }
}
