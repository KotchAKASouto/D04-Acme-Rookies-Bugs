package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Audit extends DomainEntity{

	private Date moment;
	private String text;
	private Double score;
	private Boolean finalMode;
	//relationships
	private Auditor auditor;
	private Position position;
	
	@NotNull
	@Past
	public Date getMoment() {
		return moment;
	}
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	@NotBlank
	@SafeHtml
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	@Min(0)
	@Max(10)
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	
	@NotNull
	public Boolean getFinalMode() {
		return finalMode;
	}
	public void setFinalMode(Boolean finalMode) {
		this.finalMode = finalMode;
	}
	
	@Valid
	@ManyToOne(optional=false)
	public Auditor getAuditor() {
		return auditor;
	}
	public void setAuditor(Auditor auditor) {
		this.auditor = auditor;
	}
	
	@Valid
	@OneToOne(optional=false)
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	
	
	
}
