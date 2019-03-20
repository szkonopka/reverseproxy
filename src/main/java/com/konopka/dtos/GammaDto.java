package com.konopka.dtos;

import java.io.Serializable;

public class GammaDto implements Serializable {
    private int id;
    private String name;
    private String method;

    public GammaDto(int id, String name, String method)
    {
        this.id = id;
        this.name = name;
        this.method = method;
    }
}