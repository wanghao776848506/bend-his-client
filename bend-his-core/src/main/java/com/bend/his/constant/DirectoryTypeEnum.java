package com.bend.his.constant;

/**
 * 目录类型[0科室、1医生、2病区、3床位]
 */
public enum DirectoryTypeEnum {
    SECTION("0"),DOCTOR("1"),WARD("2"),BED("3");
    private String value;

    DirectoryTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
