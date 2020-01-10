package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.ProductAttrValue;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AttributeWrapper{
    List<ProductAttrValue> getAttrValues();

    void setAttrValues(List<ProductAttrValue> attrValues);
}

@Service
class AttributeWrapperImpl implements AttributeWrapper{
    private List<ProductAttrValue> attrValues;

    public List<ProductAttrValue> getAttrValues() {
        return attrValues;
    }

    public void setAttrValues(List<ProductAttrValue> attrValues) {
        this.attrValues = attrValues;
    }
}
