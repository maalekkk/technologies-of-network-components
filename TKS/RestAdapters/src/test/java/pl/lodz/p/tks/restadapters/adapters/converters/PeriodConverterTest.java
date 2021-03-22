package pl.lodz.p.tks.restadapters.adapters.converters;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.tks.restadapters.data.rent.PeriodRest;
import pl.lodz.p.tks.view.domainmodel.model.rent.Period;

import java.time.LocalDateTime;

import static pl.lodz.p.tks.restadapters.adapters.converters.PeriodConverter.fromDomainModel;
import static pl.lodz.p.tks.restadapters.adapters.converters.TestUtils.comparePeriods;

public class PeriodConverterTest {

    private Period period;
    private PeriodRest periodRest;

    @Before
    public void init() {
        period = new Period();
        period.setStartDate(LocalDateTime.now());
        period.setEndDate(LocalDateTime.MAX);

        periodRest = new PeriodRest();
        periodRest.setStartDate(period.getStartDate());
        periodRest.setEndDate(period.getEndDate());
    }

    @Test
    public void domainToRestConversion() {
        PeriodRest periodRest = fromDomainModel(period);
        Assert.assertTrue(comparePeriods(periodRest, this.periodRest));
    }

    @Test
    public void entToDomainConversion() {
        Period period = PeriodConverter.toDomainModel(periodRest);
        Assert.assertTrue(comparePeriods(period, this.period));
    }
}
