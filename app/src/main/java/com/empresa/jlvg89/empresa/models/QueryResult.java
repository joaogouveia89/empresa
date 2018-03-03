package com.empresa.jlvg89.empresa.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jlvg89 on 03/03/18.
 */

public class QueryResult implements Serializable {
    private List<Enterprise> enterprises;


    public QueryResult() {
    }

    public List<Enterprise> getEnterprises() {
        return enterprises;
    }

    public void setEnterprises(List<Enterprise> enterprises) {
        this.enterprises = enterprises;
    }
}
