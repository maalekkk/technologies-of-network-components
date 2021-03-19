package pl.lodz.p.tks.repositoriesadapters.repository;

import pl.lodz.p.tks.repositoriesadapters.data.user.UserEnt;
import pl.lodz.p.tks.repositoriesadapters.repository.generator.UuidGenerator;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import java.util.UUID;

@ApplicationScoped
public class UserRepository extends InMemoryRepository<@Valid UserEnt, UUID> {
    public UserRepository() {
        super(new UuidGenerator());
    }

    @PostConstruct
    private void init() {
        UserEnt u1 = new UserEnt("Nawrok", "Sebastian Nawrocki", true);
        UserEnt u2 = new UserEnt("Blazz", "Maciej Błażewicz", true);
        UserEnt u3 = new UserEnt("Malek", "Bartłomiej Małkowski", true);
        save(u1);
        save(u2);
        save(u3);
    }
}