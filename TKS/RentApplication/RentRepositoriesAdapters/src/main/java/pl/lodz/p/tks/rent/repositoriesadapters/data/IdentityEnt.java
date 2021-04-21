package pl.lodz.p.tks.rent.repositoriesadapters.data;

import java.io.Serializable;

public interface IdentityEnt<ID extends Serializable> extends Serializable {
    ID getId();

    void setId(ID id);
}