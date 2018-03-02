package com.empresa.jlvg89.empresa.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jlvg89 on 02/03/18.
 */

public class Portfolio implements Serializable {
    private int enterprisesNumber;
    private List<Enterprise> enterprises;

    public Portfolio() {
    }

    public int getEnterprisesNumber() {
        return enterprisesNumber;
    }

    public void setEnterprisesNumber(int enterprisesNumber) {
        this.enterprisesNumber = enterprisesNumber;
    }

    public List<Enterprise> getEnterprises() {
        return enterprises;
    }

    public void setEnterprises(List<Enterprise> enterprises) {
        this.enterprises = enterprises;
    }
}
