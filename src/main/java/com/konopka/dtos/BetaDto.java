package com.konopka.dtos;

import java.io.Serializable;

public class BetaDto implements Serializable {
    private int id;
    private String name;
    private String method;

    public BetaDto(int id, String name, String method)
    {
        this.id = id;
        this.name = name;
        this.method = method;
    }
}