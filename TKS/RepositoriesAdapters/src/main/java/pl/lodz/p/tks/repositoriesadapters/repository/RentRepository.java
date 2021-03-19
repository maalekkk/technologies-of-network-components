package pl.lodz.p.tks.repositoriesadapters.repository;


import pl.lodz.p.tks.repositoriesadapters.data.rent.RentEnt;
import pl.lodz.p.tks.repositoriesadapters.repository.generator.UuidGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class RentRepository extends InMemoryRepository<@Valid RentEnt, UUID> {
    public RentRepository() {
        super(new UuidGenerator());
    }

    @Override
    public List<RentEnt> findAll() {
        return super.findAll().stream().sorted(Comparator.comparing((RentEnt rent) -> rent.getPeriod().getStartDate()).reversed()).collect(Collectors.toList());
    }
}