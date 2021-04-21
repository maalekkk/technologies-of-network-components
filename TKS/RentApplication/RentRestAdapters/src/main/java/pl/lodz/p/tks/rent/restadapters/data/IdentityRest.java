package pl.lodz.p.tks.rent.restadapters.data;

import java.io.Serializable;

public interface IdentityRest<ID extends Serializable> extends Serializable {
    ID getId();

    void setId(ID id);
}