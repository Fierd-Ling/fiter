package com.hand.pojo;

/**
 * @author: ZhongLingYun
 * @date: 2018/8/23 17:29
 * @description: 地址实体类 只用到了两个字段
 */
public class Address {
    private Integer addressId;

    private String addressName;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
}
