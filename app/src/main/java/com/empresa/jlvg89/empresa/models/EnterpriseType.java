package com.empresa.jlvg89.empresa.models;

import java.io.Serializable;

/**
 * Created by jlvg89 on 03/03/18.
 */

public class EnterpriseType implements Serializable {
    private int id;
    private String enterprise_type_name;

    public EnterpriseType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnterprise_type_name() {
        return enterprise_type_name;
    }

    public void setEnterprise_type_name(String enterprise_type_name) {
        this.enterprise_type_name = enterprise_type_name;
    }

    @Override
    public String toString() {
        return "EnterpriseType{" +
                "id=" + id +
                ", enterprise_type_name='" + enterprise_type_name + '\'' +
                '}';
    }
}
