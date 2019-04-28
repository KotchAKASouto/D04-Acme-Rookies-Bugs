
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.AuditRepository;
import domain.Audit;

@Service
@Transactional
public class AuditService {

	// Managed Repository ------------------------
	@Autowired
	AuditRepository	auditRepository;


	public Collection<Audit> findByPositionId(final int positionId) {

		final Collection<Audit> res = this.auditRepository.findByPositionId(positionId);

		return res;
	}
}
