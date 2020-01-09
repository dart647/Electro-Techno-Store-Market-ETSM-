package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.ProductAttrValue;

import java.util.List;

public class AttributeWrapper{
    List<ProductAttrValue> attrValues;

    public AttributeWrapper(List<ProductAttrValue> attrValues) {
        this.attrValues = attrValues;
    }

    public List<ProductAttrValue> getAttrValues() {
        return attrValues;
    }

    public void setAttrValues(List<ProductAttrValue> attrValues) {
        this.attrValues = attrValues;
    }
}
