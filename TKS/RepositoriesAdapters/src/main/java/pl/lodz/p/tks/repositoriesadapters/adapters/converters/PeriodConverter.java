package pl.lodz.p.tks.repositoriesadapters.adapters.converters;

import pl.lodz.p.tks.applicationcore.domainmodel.model.rent.Period;
import pl.lodz.p.tks.repositoriesadapters.data.rent.PeriodEnt;

public class PeriodConverter {

    public static Period convertPeriodEnt(PeriodEnt periodEnt) {
        Period period = new Period();
        period.setStartDate(periodEnt.getStartDate());
        period.setEndDate(periodEnt.getEndDate());
        return period;
    }
    public static PeriodEnt convertPeriod(Period period) {
        PeriodEnt periodEnt = new PeriodEnt();
        periodEnt.setStartDate(period.getStartDate());
        periodEnt.setEndDate(period.getEndDate());
        return periodEnt;
    }

}
