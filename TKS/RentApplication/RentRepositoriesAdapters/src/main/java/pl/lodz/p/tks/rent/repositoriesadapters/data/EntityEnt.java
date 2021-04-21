package pl.lodz.p.tks.rent.repositoriesadapters.data;

import java.util.Objects;
import java.util.UUID;

public abstract class EntityEnt implements IdentityEnt<UUID> {
    private UUID id;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntityEnt entity = (EntityEnt) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}