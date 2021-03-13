package pl.lodz.p.tks.applicationcore.domainmodel.model;

import java.io.Serializable;

public interface Identity<ID extends Serializable> extends Serializable
{
    ID getId();

    void setId(ID id);
}
