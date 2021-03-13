package pl.lodz.p.tks.applicationcore.domainmodel.model.machine;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MachineWorkstation extends Machine
{
    @Min(1)
    @NotNull
    private Integer ssdSize;

    @Min(0)
    @Max(10)
    @NotNull
    private Integer netCards;

    private boolean raidSupport;

    public MachineWorkstation()
    {
    }

    public MachineWorkstation(String name, Integer cores, Integer ramSize, Integer hddSize, Integer ssdSize, Integer netCards, boolean raidSupport)
    {
        super(name, cores, ramSize, hddSize);
        this.ssdSize = ssdSize;
        this.netCards = netCards;
        this.raidSupport = raidSupport;
    }

    public Integer getSsdSize()
    {
        return ssdSize;
    }

    public void setSsdSize(Integer ssdSize)
    {
        this.ssdSize = ssdSize;
    }

    public Integer getNetCards()
    {
        return netCards;
    }

    public void setNetCards(Integer netCards)
    {
        this.netCards = netCards;
    }

    public boolean getRaidSupport()
    {
        return raidSupport;
    }

    public void setRaidSupport(boolean raidSupport)
    {
        this.raidSupport = raidSupport;
    }
}
