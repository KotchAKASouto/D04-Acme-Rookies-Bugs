
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Integer> {

	@Query("select p from Audit p where p.position.id = ?1")
	Collection<Audit> findAuditsByPositionId(int positionId);

	@Query("select p from Audit p where p.auditor.id = ?1")
	Collection<Audit> findAuditsByAuditorId(int auditorId);

}
