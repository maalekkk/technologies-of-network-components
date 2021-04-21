package pl.lodz.p.tks.user.repositoriesadapters.repository.generator;

import java.io.Serializable;

public interface PrimaryKeyGenerator<ID extends Serializable>
{
    ID getId();
}
