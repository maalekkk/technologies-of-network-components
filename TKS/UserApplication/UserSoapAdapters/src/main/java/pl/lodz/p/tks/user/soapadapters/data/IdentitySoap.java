package pl.lodz.p.tks.user.soapadapters.data;

import java.io.Serializable;

public interface IdentitySoap<ID extends Serializable> extends Serializable {
    ID getId();

    void setId(ID id);
}