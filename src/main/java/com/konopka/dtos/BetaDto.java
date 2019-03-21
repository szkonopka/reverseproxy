package com.konopka.dtos;

import java.io.Serializable;

public class BetaDto extends MicroserviceDto {
    private Boolean uniqueBool;

    public BetaDto() { }

    public BetaDto(int id, String name, String method, Boolean uniqueBool)
    {
        super(id, name, method);
        this.uniqueBool = uniqueBool;
    }

    public Boolean getUniqueBool() { return uniqueBool; }
    public void setUniqueBool() { this.uniqueBool = uniqueBool; }
}