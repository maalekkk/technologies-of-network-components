package pl.lodz.p.tks.repositoriesadapters.repository.generator;

import java.util.UUID;

public class UuidGenerator implements PrimaryKeyGenerator<UUID>
{
    @Override
    public UUID getId()
    {
        return UUID.randomUUID();
    }
}
