package com.bend.his.constant;

/**
 * 检验检查类型
 * 30-15 接口返回的 BILL_TYPE
 */
public enum ReportItem {


    INSPECTION("检验", "9"),
    EXAMINE("检查", "8");

    private String name;
    private String value;

    ReportItem(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public static String getName(String value) {
        for (ReportItem pt : ReportItem.values()) {
            if (pt.getValue().equals(value)) {
                return pt.getName();
            }
        }
        return null;
    }

}
