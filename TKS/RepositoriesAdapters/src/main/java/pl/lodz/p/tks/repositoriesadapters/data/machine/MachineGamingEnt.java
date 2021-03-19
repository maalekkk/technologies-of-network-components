package pl.lodz.p.tks.repositoriesadapters.data.machine;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MachineGamingEnt extends MachineEnt {
    @Min(1)
    @NotNull
    private Integer gpuPower;

    @Min(1)
    @NotNull
    private Integer gpuVram;

    public MachineGamingEnt() {
    }

    public MachineGamingEnt(String name, Integer cores, Integer ramSize, Integer hddSize, Integer gpuPower, Integer gpuVram) {
        super(name, cores, ramSize, hddSize);
        this.gpuPower = gpuPower;
        this.gpuVram = gpuVram;
    }

    public Integer getGpuPower() {
        return gpuPower;
    }

    public void setGpuPower(Integer gpuPower) {
        this.gpuPower = gpuPower;
    }

    public Integer getGpuVram() {
        return gpuVram;
    }

    public void setGpuVram(Integer gpuVram) {
        this.gpuVram = gpuVram;
    }
}