package pl.lodz.p.tks.user.soapadapters.data;

import java.util.Objects;
import java.util.UUID;

public abstract class EntitySoap implements IdentitySoap<UUID> {
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

        EntitySoap entity = (EntitySoap) o;
        return Objects.equals(id, entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}