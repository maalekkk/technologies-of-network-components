package pl.lodz.p.tks.rent.restadapters.adapters.converters;

import pl.lodz.p.tks.rent.restadapters.data.rent.PeriodRest;
import pl.lodz.p.tks.rent.core.domainmodel.rent.Period;

public class PeriodConverter {

    public static Period toDomainModel(PeriodRest periodRest) {
        Period period = new Period();
        period.setStartDate(periodRest.getStartDate());
        period.setEndDate(periodRest.getEndDate());
        return period;
    }

    public static PeriodRest fromDomainModel(Period period) {
        PeriodRest periodRest = new PeriodRest();
        periodRest.setStartDate(period.getStartDate());
        periodRest.setEndDate(period.getEndDate());
        return periodRest;
    }
}