
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
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
	 * -------Coverage PositionService
	 * ----TOTAL SENTENCE COVERAGE:
	 * ProviderService =
	 * 
	 * ----TOTAL DATA COVERAGE:
	 * Provider = 0%
	 */

}
