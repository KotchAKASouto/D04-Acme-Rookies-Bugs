
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import security.UserAccount;
import utilities.AbstractTest;
import domain.CreditCard;
import domain.Provider;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ProviderServiceTest extends AbstractTest {

	//The SUT----------------------------------------------------
	@Autowired
	private ProviderService	providerService;


	/*
	 * ----CALCULATE COVERAGE----
	 * The previous delivery, we calculate it manually. In this one instead we are using the plugin called EclEmma,
	 * with which we can automatically calculate the percentage.
	 * 
	 * Each of the test have their result just before them, and the coverage of the complete test is shown at the end of the document.
	 */

	/*
	 * ACME.ROOKIES
	 * a)(Level B) Requirement 9.1: An actor who is not authenticated must be able to: Browse the list of providers
	 * 
	 * 
	 * b) Negative cases:
	 * 2. The number of providers is incorrect
	 * 
	 * c) Sentence coverage
	 * -findAll()=100%
	 * 
	 * d) Data coverage
	 * -Provider: 0%
	 */
	@Test
	public void driverListAllProviders() {
		final Object testingData[][] = {
			{
				6, null
			},//1. All fine
			{
				0, IllegalArgumentException.class
			},//2. The number of providers is incorrect

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListAllProviders((Integer) testingData[i][0], (Class<?>) testingData[i][1]);
	}
	protected void templateListAllProviders(final Integer number, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.startTransaction();

			final Collection<Provider> providers = this.providerService.findAll();

			Assert.isTrue(providers.size() == number);
		} catch (final Throwable oops) {
			caught = oops.getClass();

		}
		this.rollbackTransaction();
		super.checkExceptions(expected, caught);

	}

	/*
	 * ACME.ROOKIES
	 * a)(Level B) Requirement 9.3: An actor who is not authenticated must be able to: Register to the system as a provider
	 * 
	 * 
	 * b) Negative cases:
	 * 2. The email format isn't correct
	 * 
	 * c) Sentence coverage
	 * -Create() = 100%
	 * -save() =43.9%
	 * 
	 * d) Data coverage
	 * -Provider: 6.6667%
	 */

	@Test
	public void driverRegisterprovider() {
		final Object testingData[][] = {
			{
				"C/ Iñigo Montoya", "inhigo@gmail.com", "Iñigo", "123456789", "https://blogs.grupojoly.com/elcinehamuerto/files/2011/11/princesbride2.jpg", "12398761243", false, "Montoya", 123453452, true, "matasteamipadre", "mellamoiñigomontoya", 123,
				12, 2020, "Iñigo Montoya", "VISA", "1111222233334444", null
			},//1. All fine
			{
				"C/ Iñigo Montoya", "inhigo.com", "Iñigo", "123456789", "https://blogs.grupojoly.com/elcinehamuerto/files/2011/11/princesbride2.jpg", "12398761243", false, "Montoya", 123453452, true, "matasteamipadre", "mellamoiñigomontoya", 123, 20,
				2020, "Iñigo Montoya", "VISA", "1111222233334444", IllegalArgumentException.class
			},//2. The email format isn't correct

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateRegisterprovider((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Boolean) testingData[i][6],
				(String) testingData[i][7], (Integer) testingData[i][8], (Boolean) testingData[i][9], (String) testingData[i][10], (String) testingData[i][11], (Integer) testingData[i][12], (Integer) testingData[i][13], (Integer) testingData[i][14],
				(String) testingData[i][15], (String) testingData[i][16], (String) testingData[i][17], (Class<?>) testingData[i][18]);
	}

	protected void templateRegisterprovider(final String address, final String email, final String name, final String phone, final String photo, final String providerMake, final Boolean spammer, final String surnames, final Integer vat,
		final Boolean isBanned, final String password, final String username, final Integer cvv, final Integer expMonth, final Integer expYear, final String holderName, final String make, final String number, final Class<?> expected) {
		Class<?> caught;

		caught = null;
		try {
			this.startTransaction();

			final Provider provider = this.providerService.create();

			provider.setAddress(address);
			provider.setEmail(email);
			provider.setName(name);
			provider.setPhone(phone);
			provider.setPhoto(photo);
			provider.setProviderMake(providerMake);
			provider.setSpammer(spammer);
			provider.setSurnames(surnames);
			provider.setVat(vat);

			final UserAccount userAccount = provider.getUserAccount();
			userAccount.setIsNotBanned(isBanned);
			userAccount.setPassword(password);
			userAccount.setUsername(username);
			provider.setUserAccount(userAccount);

			final CreditCard creditCard = new CreditCard();
			creditCard.setCvv(cvv);
			creditCard.setExpMonth(expMonth);
			creditCard.setExpYear(expYear);
			creditCard.setHolderName(holderName);
			creditCard.setMake(make);
			creditCard.setNumber(number);
			provider.setCreditCard(creditCard);

			this.providerService.save(provider);

		} catch (final Throwable oops) {
			caught = oops.getClass();

		}
		this.rollbackTransaction();
		super.checkExceptions(expected, caught);

	}

	/*
	 * -------Coverage PositionService
	 * ----TOTAL SENTENCE COVERAGE:
	 * ProviderService = 33.2%
	 * 
	 * ----TOTAL DATA COVERAGE:
	 * Provider = 6.6667%
	 */

}
