package pl.lodz.pl.tks.repositoriesadapters.adapters.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.tks.repositoriesadapters.adapters.converters.PeriodConverter;
import pl.lodz.p.tks.repositoriesadapters.data.rent.PeriodEnt;
import pl.lodz.p.tks.view.domainmodel.model.rent.Period;

import java.time.LocalDateTime;

import static pl.lodz.pl.tks.repositoriesadapters.adapters.converters.TestUtils.comparePeriods;

public class PeriodConverterTest {

    private Period period;
    private PeriodEnt periodEnt;

    @Before
    public void init() {
        period = new Period();
        period.setStartDate(LocalDateTime.now());
        period.setEndDate(LocalDateTime.MAX);

        periodEnt = new PeriodEnt();
        periodEnt.setStartDate(period.getStartDate());
        periodEnt.setEndDate(period.getEndDate());
    }

    @Test
    public void domainToEntConversion() {
        PeriodEnt periodEnt = PeriodConverter.fromDomainModel(period);
        Assert.assertTrue(comparePeriods(periodEnt, this.periodEnt));
    }

    @Test
    public void entToDomainConversion() {
        Period period = PeriodConverter.toDomainModel(periodEnt);
        Assert.assertTrue(comparePeriods(period, this.period));
    }
}
