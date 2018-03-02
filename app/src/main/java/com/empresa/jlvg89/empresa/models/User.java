package com.empresa.jlvg89.empresa.models;

import java.io.Serializable;

/**
 * Created by jlvg89 on 02/03/18.
 */

public class User implements Serializable{
    private Investor investor;
    private Enterprise enterprise;
    private boolean success;

    public User() {

    }

    public Investor getInvestor() {
        return investor;
    }

    public void setInvestor(Investor investor) {
        this.investor = investor;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
