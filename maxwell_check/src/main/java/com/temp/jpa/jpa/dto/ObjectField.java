package com.temp.jpa.jpa.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;

/**
 * 对象字段
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,include = JsonTypeInfo.As.PROPERTY,visible = true,property="fieldType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ObjectField.class, name = "SYS_FIELD"),
        @JsonSubTypes.Type(value = ObjectField.class, name = "BIZ_FIELD")
})
@Getter
@Setter
public class ObjectField {


}
