package com.konopka.dtos;

import java.io.Serializable;

public class GammaDto extends MicroserviceDto {
    private Byte uniqueChar;
    
    public GammaDto() { }

    public GammaDto(int id, String name, String method, Byte uniqueChar)
    {
        super(id, name, method);
        this.uniqueChar = uniqueChar;
    }

    public Byte getUniqueChar() { return uniqueChar; }
    public void setUniqueChar(Byte uniqueChar) { this.uniqueChar = uniqueChar; }
}