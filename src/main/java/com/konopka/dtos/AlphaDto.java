package com.konopka.dtos;

import java.io.Serializable;

public class AlphaDto extends MicroserviceDto {
    private Double uniqueDouble;

    public AlphaDto() { }

    public AlphaDto(int id, String name, String method, Double uniqueDouble)
    {
        super(id, name, method);
        this.uniqueDouble = uniqueDouble;
    }

    public Double getUniqueDouble() { return uniqueDouble; }
    public void setUniqueDouble(Double uniqueDouble) { this.uniqueDouble = uniqueDouble; }
}