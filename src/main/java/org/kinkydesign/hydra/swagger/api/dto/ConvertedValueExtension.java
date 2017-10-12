package org.kinkydesign.hydra.swagger.api.dto;

import org.kinkydesign.hydra.swagger.api.annotations.JsonldProperty;
import org.kinkydesign.hydra.swagger.api.annotations.JsonldType;

/**
 * This dto is used to test extended classes and the way they get documented
 * by SwaggerModifier as well as the way they are communicated with JsonldProvider
 */

@JsonldType("http://schema.org/object")
public class ConvertedValueExtension  extends  ConvertedValue{

    @JsonldProperty("http://schema.org/someIntValue")
    private Integer someIntValue;

    public ConvertedValueExtension(){}

    public ConvertedValueExtension(Integer someIntValue) {
        this.someIntValue = someIntValue;
    }

    public Integer getSomeIntValue() {
        return someIntValue;
    }

    public void setSomeIntValue(Integer someIntValue) {
        this.someIntValue = someIntValue;
    }
}
