package mg.projets.servermanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mg.projets.servermanager.models.Server;

public interface ServerRepository extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);
}
