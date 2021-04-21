package pl.lodz.p.tks.rent.repositoriesadapters.repository.generator;

import java.io.Serializable;

public interface PrimaryKeyGenerator<ID extends Serializable>
{
    ID getId();
}
