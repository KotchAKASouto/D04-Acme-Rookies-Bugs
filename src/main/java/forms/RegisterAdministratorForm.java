
package forms;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;

import domain.CreditCard;

public class RegisterAdministratorForm {

	private String		name;
	private String		surnames;
	private Integer		vat;
	private String		photo;
	private String		email;
	private CreditCard	creditCard;
	private String		phone;
	private String		address;

	private String		username;
	private String		password;
	private String		confirmPassword;

	private Boolean		checkbox;


	@SafeHtml
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@SafeHtml
	public String getSurnames() {
		return this.surnames;
	}

	public void setSurnames(final String surnames) {
		this.surnames = surnames;
	}

	@Column(unique = true)
	public Integer getVat() {
		return this.vat;
	}

	public void setVat(final Integer vat) {
		this.vat = vat;
	}

	@SafeHtml
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	@SafeHtml
	public String getEmail() {
		return this.email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@SafeHtml
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	@SafeHtml
	public String getAddress() {
		return this.address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	@Column(unique = true)
	@SafeHtml
	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	@SafeHtml
	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@SafeHtml
	public String getConfirmPassword() {
		return this.confirmPassword;
	}

	public void setConfirmPassword(final String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@NotNull
	public Boolean getCheckbox() {
		return this.checkbox;
	}

	public void setCheckbox(final Boolean checkbox) {
		this.checkbox = checkbox;
	}

	//Business metohds--------------------------------------------
	public Boolean checkPassword() {
		Boolean res;

		if (this.password.equals(this.confirmPassword))
			res = true;
		else
			res = false;

		return res;
	}

}
