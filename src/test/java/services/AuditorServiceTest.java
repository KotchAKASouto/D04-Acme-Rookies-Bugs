
package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import utilities.AbstractTest;
import domain.Auditor;
import domain.CreditCard;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class AuditorServiceTest extends AbstractTest {

	//The SUT----------------------------------------------------
	@Autowired
	private AuditorService	auditorService;


	/*
	 * ----CALCULATE COVERAGE----
	 * The previous delivery, we calculate it manually. In this one instead we are using the plugin called EclEmma,
	 * with which we can automatically calculate the percentage.
	 * 
	 * Each of the test have their result just before them, and the coverage of the complete test is shown at the end of the document.
	 */

	/*
	 * ACME.ROOKIES
	 * a)(Level C) Requirement 4.2: An actor who is authenticated as an administrator must be able to: Create user accounts for new auditors.
	 * 
	 * b) Negative cases:
	 * 2. Name = null
	 * 3. Name = blank
	 * 
	 * c) Sentence coverage
	 * -save(): 52%
	 * -create(): 100%
	 * 
	 * d) Data coverage
	 * -Auditor: 9,09091%
	 */

	@Test
	public void driverRegisterAuditor() {
		final Object testingData[][] = {
			{
				"name1", "surnames", 1205310889, "https://google.com", "email1@gmail.com", "672195205", "address1", "auditor100", "auditor100", "functionalTest", "VISA", "380004663288126", "12", "2020", "123", null
			},//1. All fine
			{
				null, "surnames", 1205310889, "https://google.com", "email1@gmail.com", "672195205", "address1", "auditor100", "auditor100", "functionalTest", "VISA", "380004663288126", "12", "2020", "123", ConstraintViolationException.class
			},//2. Name = null
			{
				"		", "surnames", 1205310889, "https://google.com", "email1@gmail.com", "672195205", "address1", "auditor100", "auditor100", "functionalTest", "VISA", "380004663288126", "12", "2020", "123", ConstraintViolationException.class
			},//3. Name = blank

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateRegisterAuditor((String) testingData[i][0], (String) testingData[i][1], (Integer) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (String) testingData[i][8], (String) testingData[i][9], (String) testingData[i][10], (String) testingData[i][11], (String) testingData[i][12], (String) testingData[i][13], (String) testingData[i][14],
				(Class<?>) testingData[i][15]);
	}

	protected void templateRegisterAuditor(final String name, final String surnames, final Integer vat, final String photo, final String email, final String phone, final String address, final String username, final String password,
		final String holderName, final String make, final String number, final String expMonth, final String expYear, final String cvv, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {

			this.authenticate("admin");

			final Auditor auditor = this.auditorService.create();

			auditor.setName(name);
			auditor.setSurnames(surnames);
			auditor.setVat(vat);

			final CreditCard creditCard = new CreditCard();
			creditCard.setHolderName(holderName);
			creditCard.setCvv(new Integer(cvv));
			creditCard.setExpMonth(new Integer(expMonth));
			creditCard.setExpYear(new Integer(expYear));
			creditCard.setMake(make);
			creditCard.setNumber(number);

			auditor.setCreditCard(creditCard);
			auditor.setPhoto(photo);
			auditor.setEmail(email);
			auditor.setPhone(phone);
			auditor.setAddress(address);

			auditor.getUserAccount().setUsername(username);
			auditor.getUserAccount().setPassword(password);

			this.startTransaction();

			this.auditorService.save(auditor);
			this.auditorService.flush();

			this.unauthenticate();

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.rollbackTransaction();
		super.checkExceptions(expected, caught);

	}

	/*
	 * -------Coverage AuditorService-------
	 * 
	 * ----TOTAL SENTENCE COVERAGE:
	 * AuditorService: 36%
	 * 
	 * ----TOTAL DATA COVERAGE:
	 * Auditor = 9,09091%
	 */
}
