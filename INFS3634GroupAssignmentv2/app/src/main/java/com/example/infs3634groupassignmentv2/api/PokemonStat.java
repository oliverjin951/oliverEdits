package com.example.infs3634groupassignmentv2.api;

import java.io.Serializable;

public class PokemonStat implements Serializable {

    private int base_stat;
    private Stat stat;

    public int getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(int base_stat) {
        this.base_stat = base_stat;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }
}
