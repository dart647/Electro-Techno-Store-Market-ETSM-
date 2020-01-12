package com.etsm.ETSM.Services;

import com.etsm.ETSM.Models.ProductAttrValue;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AttributeWrapperImplTest {

    @Test
    public void getAttrValuesTest(){

        AttributeWrapperImpl attributeWrapper = new AttributeWrapperImpl();
        ProductAttrValue productAttrValue = new ProductAttrValue();
        productAttrValue.setId(1L);
        List<ProductAttrValue> productAttrValueList = List.of(productAttrValue);
        Assert.assertEquals(attributeWrapper.getAttrValues(),null);
    }
    @Test
    public void setAttrValuesTest(){
        AttributeWrapperImpl attributeWrapper = new AttributeWrapperImpl();
        ProductAttrValue productAttrValue = new ProductAttrValue();
        productAttrValue.setId(1L);
        List<ProductAttrValue> productAttrValueList = List.of(productAttrValue);
        attributeWrapper.setAttrValues(productAttrValueList);

    }

}