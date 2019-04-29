
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AuditRepository;
import security.Authority;
import domain.Actor;
import domain.Audit;
import domain.Auditor;

@Service
@Transactional
public class AuditService {

	// Managed Repository ------------------------
	@Autowired
	private AuditRepository	auditRepository;

	// Suporting services ------------------------
	@Autowired
	private AuditorService	auditorService;

	@Autowired
	private ActorService	actorService;

	@Autowired
	private Validator		validator;


	// Simple CRUD methods -----------------------

	public Audit create() {
		final Actor actor = this.actorService.findByPrincipal();
		Assert.notNull(actor);
		final Authority authority = new Authority();
		authority.setAuthority(Authority.AUDITOR);
		Assert.isTrue((actor.getUserAccount().getAuthorities().contains(authority)));

		final Audit result = new Audit();

		result.setFinalMode(false);
		result.setAuditor(this.auditorService.findByPrincipal());
		result.setPosition(this.auditorService.findByPrincipal().getPosition());

		final Date currentMoment = new Date(System.currentTimeMillis() - 1000);
		result.setMoment(currentMoment);

		return result;
	}

	public Collection<Audit> findAll() {
		Collection<Audit> result;
		result = this.auditRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Audit findOne(final int auditId) {
		Audit audit;
		audit = this.auditRepository.findOne(auditId);
		return audit;
	}

	public Audit save(final Audit audit) {

		Assert.notNull(audit);

		final Actor actor = this.actorService.findByPrincipal();
		Assert.notNull(actor);
		final Authority authority = new Authority();
		authority.setAuthority(Authority.AUDITOR);
		Assert.isTrue((actor.getUserAccount().getAuthorities().contains(authority)));
		Assert.isTrue(actor.getId() == audit.getAuditor().getId());

		Assert.isTrue(audit.getAuditor().getPosition() == audit.getPosition());

		Audit result;

		if (audit.getId() != 0) {
			final Audit auditBBDD = this.findOne(audit.getId());
			Assert.isTrue(auditBBDD.getFinalMode() == false);
		}

		result = this.auditRepository.save(audit);

		return result;
	}

	public void delete(final Audit audit) {
		Assert.notNull(audit);
		final Actor actor = this.actorService.findByPrincipal();
		Assert.notNull(actor);

		final Authority comp = new Authority();
		comp.setAuthority(Authority.AUDITOR);
		Assert.isTrue(actor.getUserAccount().getAuthorities().contains(comp));
		Assert.isTrue(actor.getId() == audit.getAuditor().getId());
		Assert.isTrue(audit.getAuditor().getPosition() == audit.getPosition());

		Assert.isTrue(audit.getFinalMode() == false);
		this.auditRepository.delete(audit);

	}

	//Other bussiness methods

	public Audit reconstruct(final Audit audit, final BindingResult binding) {

		Audit result = audit;
		final Audit auditNew = this.create();

		if (audit.getId() == 0 || audit == null) {

			audit.setPosition(auditNew.getPosition());
			audit.setMoment(auditNew.getMoment());
			audit.setAuditor(auditNew.getAuditor());

			this.validator.validate(audit, binding);

			result = audit;
		} else {

			final Audit auditBBDD = this.findOne(audit.getId());

			audit.setPosition(auditBBDD.getPosition());
			audit.setMoment(auditBBDD.getMoment());
			audit.setAuditor(auditBBDD.getAuditor());

			this.validator.validate(audit, binding);

		}

		return result;

	}

	public Collection<Audit> findAuditsByAuditorId(final int auditorId) {
		final Collection<Audit> audits = this.auditRepository.findAuditsByAuditorId(auditorId);

		return audits;
	}

	public Collection<Audit> findAuditsByPositionId(final int positionId) {
		final Collection<Audit> audits = this.auditRepository.findAuditsByPositionId(positionId);

		return audits;
	}

	public Boolean auditAuditorSecurity(final int auditId) {
		Boolean res = false;
		final Audit audit = this.findOne(auditId);

		final Auditor owner = audit.getAuditor();

		final Auditor login = this.auditorService.findByPrincipal();

		if (login.equals(owner) && audit.getPosition() == login.getPosition())
			res = true;

		return res;
	}

	public void flush() {
		this.auditRepository.flush();
	}

}
