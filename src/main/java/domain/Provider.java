package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Provider extends Actor{
	

	private String mak;

	@NotBlank
	@SafeHtml
	public String getMak() {
		return mak;
	}

	public void setMak(String mak) {
		this.mak = mak;
	}
	
	
	

}
