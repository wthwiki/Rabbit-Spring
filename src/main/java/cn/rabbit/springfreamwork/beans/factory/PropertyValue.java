package cn.rabbit.springfreamwork.beans.factory;


import lombok.Data;

@Data
public class PropertyValue {
    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }
}
