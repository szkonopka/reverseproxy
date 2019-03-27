package com.konopka.dtos;

import java.io.Serializable;

public class MicroserviceDto implements Serializable {
    protected int id;
    protected String name;
    protected String method;

    public MicroserviceDto() { }

    public MicroserviceDto(int id, String name, String method)
    {
        this.id = id;
        this.name = name;
        this.method = method;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getMethod() { return method; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setMethod(String method) { this.method = method; }
}