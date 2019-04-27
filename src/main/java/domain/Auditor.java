package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
@Access(AccessType.PROPERTY)
public class Auditor extends Actor{
	
	//relationships
	private Position	position;

	
	@OneToOne(optional=true)
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	

}
