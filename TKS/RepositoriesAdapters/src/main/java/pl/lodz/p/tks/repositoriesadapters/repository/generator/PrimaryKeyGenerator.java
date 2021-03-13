package pl.lodz.p.tks.repositoriesadapters.repository.generator;

import java.io.Serializable;

public interface PrimaryKeyGenerator<ID extends Serializable>
{
    ID getId();
}
