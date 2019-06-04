package com.mry.enums;

/**
 * @author: cddufu@cn.ibm.com
 * @date:
 * @desc:
 */
public enum UserCardTypes {
    memCardType("1", "会员卡"), extCardType("2", "拓客卡"), actCardType("3", "活动卡"),
    consType1("1", "卡扣"), consType2("2", "抵扣赠送项目");
    private String type;
    private String info;
    private UserCardTypes(String type, String info) {
        this.type = type;
        this.info = info;
    }
    public String type() {
        return this.type;
    }
    public String info() {
        return this.info;
    }
    public static boolean equals(String type, UserCardTypes userCardTypes) {
        return type.equals(userCardTypes.type()) || type.equals(userCardTypes.info());
    }
}
