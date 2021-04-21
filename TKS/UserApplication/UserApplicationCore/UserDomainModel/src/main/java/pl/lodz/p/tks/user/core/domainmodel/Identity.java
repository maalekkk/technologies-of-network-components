package pl.lodz.p.tks.user.core.domainmodel;

import java.io.Serializable;

public interface Identity<ID extends Serializable> extends Serializable {
    ID getId();

    void setId(ID id);
}