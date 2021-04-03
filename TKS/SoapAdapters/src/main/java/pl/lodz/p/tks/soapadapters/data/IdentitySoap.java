package pl.lodz.p.tks.soapadapters.data;

import java.io.Serializable;

public interface IdentitySoap<ID extends Serializable> extends Serializable {
    ID getId();

    void setId(ID id);
}