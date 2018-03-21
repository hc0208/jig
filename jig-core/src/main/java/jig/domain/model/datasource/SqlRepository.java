package jig.domain.model.datasource;

import jig.domain.model.identifier.MethodIdentifier;

import java.util.Optional;

public interface SqlRepository {

    Optional<Sql> find(MethodIdentifier identifier);

    void register(Sql sql);

    Sql get(SqlIdentifier identifier);
}
