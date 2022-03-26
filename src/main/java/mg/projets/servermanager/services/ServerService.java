package mg.projets.servermanager.services;

import java.util.Collection;

import mg.projets.servermanager.models.Server;

public interface ServerService {
    Server create(Server server);

    Collection<Server> list(int limit);

    Server get(Long id);

    Server update(Server server);

    Boolean delete(Long id);
}
