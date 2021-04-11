package pl.lodz.p.tks.repositoriesadapters.repository;

import pl.lodz.p.tks.repositoriesadapters.data.user.RoleEnt;
import pl.lodz.p.tks.repositoriesadapters.data.user.UserEnt;
import pl.lodz.p.tks.repositoriesadapters.repository.generator.UuidGenerator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import java.util.Collections;
import java.util.UUID;

@ApplicationScoped
public class UserRepository extends InMemoryRepository<@Valid UserEnt, UUID> {
    public UserRepository() {
        super(new UuidGenerator());
    }

    @PostConstruct
    private void init() {
        UserEnt u1 = new UserEnt("Nawrok", "trudnehaslo", "Sebastian Nawrocki", true, Collections.singleton(RoleEnt.Owner));
        UserEnt u2 = new UserEnt("Blazz", "trudnehaslo", "Maciej Błażewicz", true, Collections.singleton(RoleEnt.Admin));
        UserEnt u3 = new UserEnt("Malek", "trudnehaslo", "Bartłomiej Małkowski", true, Collections.singleton(RoleEnt.Client));
        save(u1);
        save(u2);
        save(u3);
    }
}