package pl.lodz.p.tks.rent.repositoriesadapters.data.machine;


import pl.lodz.p.tks.rent.repositoriesadapters.data.EntityEnt;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.beans.Transient;

public abstract class MachineEnt extends EntityEnt {
    @Size(min = 3, max = 20)
    private String name;

    @Min(1)
    @NotNull
    private Integer cores;

    @Min(1024)
    @Max(65536)
    @NotNull
    private Integer ramSize;

    @Min(128)
    @NotNull
    private Integer hddSize;

    public MachineEnt() {
    }

    public MachineEnt(String name, Integer cores, Integer ramSize, Integer hddSize) {
        this.name = name;
        this.cores = cores;
        this.ramSize = ramSize;
        this.hddSize = hddSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCores() {
        return cores;
    }

    public void setCores(Integer cores) {
        this.cores = cores;
    }

    public Integer getRamSize() {
        return ramSize;
    }

    public void setRamSize(Integer ramSize) {
        this.ramSize = ramSize;
    }

    public Integer getHddSize() {
        return hddSize;
    }

    public void setHddSize(Integer hddSize) {
        this.hddSize = hddSize;
    }

    @Transient
    public String getType() {
        return this.getClass().getSimpleName();
    }
}