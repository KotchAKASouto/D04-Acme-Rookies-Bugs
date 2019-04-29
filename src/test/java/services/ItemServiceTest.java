
package services;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ItemServiceTest extends AbstractTest {

	//The SUT----------------------------------------------------
	@Autowired
	private ItemService	itemService;


	/*
	 * ----CALCULATE COVERAGE----
	 * The previous delivery, we calculate it manually. In this one instead we are using the plugin called EclEmma,
	 * with which we can automatically calculate the percentage.
	 * 
	 * Each of the test have their result just before them, and the coverage of the complete test is shown at the end of the document.
	 */

	/*
	 * ACME.ROOKIES
	 * a)(Level B) Requirement 10.1: An actor who is authenticated as a provider must be able to: Manage his or her catalogue of items, which includes LISTING, showing, creating, updating, and deleting them.
	 * 
	 * b) Negative cases:
	 * 2. Item doesn't belong Provider
	 * 
	 * 
	 * c) Sentence coverage
	 * -findOne(): %
	 * d) Data coverage
	 */

	@Test
	public void driverListItems() {
		final Object testingData[][] = {

			{
				"item1", "provider1", null
			//1. All right
			}, {
				"item1", "provider2", IllegalArgumentException.class
			//2. Item doesn't belong Provider
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateListItems((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}
	protected void templateListItems(final String itemId, final String providerUsername, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {

			super.authenticate(providerUsername);
			final Integer itemIdInteger = super.getEntityId(itemId);
			final Integer providerIdInteger = super.getEntityId(providerUsername);

			final Item item = this.itemService.findOne(itemIdInteger);

			final Collection<Item> items = this.itemService.findItemsByProviderId(providerIdInteger);

			Assert.isTrue(items.contains(item));

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.unauthenticate();
		super.checkExceptions(expected, caught);

	}

	/*
	 * ACME.ROOKIES
	 * a)(Level B) Requirement 10.1: An actor who is authenticated as a provider must be able to: Manage his or her catalogue of items, which includes listing, showing, CREATING, updating, and deleting them.
	 * 
	 * b) Negative cases:
	 * 2. Somebody not authenticated tries to create a social profile
	 * 3. nick = blank
	 * 4. nick = not safe html
	 * 5. socialName = blank
	 * 6. socialName = not safe html
	 * 
	 * c) Sentence coverage
	 * -create(): %
	 * -save(): %
	 * -findAll(): %
	 * d) Data coverage
	 */

	@Test
	public void driverCreateItem() {
		final Object testingData[][] = {

			{
				"provider1", "name1", "description1", "https://www.youtube.com", null
			//1. All right
			}, {
				null, "name1", "description1", "https://www.youtube.com", IllegalArgumentException.class
			//2. Somebody not authenticated tries to create a social profile
			}, {
				"provider1", "", "description1", "https://www.youtube.com", ConstraintViolationException.class
			//3. name = blank
			}, {
				"provider1", "<script>alert('hola')</script>", "description1", "https://www.youtube.com", ConstraintViolationException.class
			//4. name = not safe html
			}, {
				"provider1", "name1", "", "https://www.youtube.com", ConstraintViolationException.class
			//5. description = blank
			}, {
				"provider1", "name1", "<script>alert('hola')</script>", "https://www.youtube.com", ConstraintViolationException.class
			//6. description = not safe html
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreateItem((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (Class<?>) testingData[i][4]);

	}
	protected void templateCreateItem(final String provider, final String name, final String description, final String link, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {
			if (provider != null)
				super.authenticate(provider);

			final Item item = this.itemService.create();
			item.setName(name);
			item.setLink(link);
			item.setDescription(description);

			final Item saved = this.itemService.save(item);
			this.itemService.flush();

			final Collection<Item> items = this.itemService.findAll();
			Assert.isTrue(items.contains(saved));

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		if (provider != null)
			super.unauthenticate();
		super.checkExceptions(expected, caught);

	}

	/*
	 * ACME.ROOKIES
	 * a)(Level B) Requirement 10.1: An actor who is authenticated as a provider must be able to: Manage his or her catalogue of items, which includes listing, showing, creating, UPDATING, and deleting them.
	 * 
	 * b) Negative cases:
	 * 2. Item doesn't belong Provider
	 * 
	 * c) Sentence coverage
	 * -save(): %
	 * -findAll(): %
	 * -findOne(): %
	 * d) Data coverage
	 */

	@Test
	public void driverUpdateItem() {
		final Object testingData[][] = {

			{
				"Provider1", "item1", "name1", null
			//1. All right
			}, {
				"Provider2", "item1", "name1", IllegalArgumentException.class
			//2. Item doesn't belong Provider
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateUpdateItem((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (Class<?>) testingData[i][3]);

	}
	protected void templateUpdateItem(final String provider, final String itemId, final String name, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {
			super.authenticate(provider);

			final Item item = this.itemService.findOne(super.getEntityId(itemId));
			item.setName(name);

			//this.startTransaction();
			final Item saved = this.itemService.save(item);
			this.itemService.flush();

			final Collection<Item> items = this.itemService.findAll();

			Assert.isTrue(items.contains(saved));

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.unauthenticate();
		super.checkExceptions(expected, caught);
		//this.rollbackTransaction();

	}

	/*
	 * ACME.HACKERRANK
	 * a)(Level A) Requirement 23.1: An actor who is authenticated must be able to: Manage his or her social profiles, which includes listing, showing, creating, updating, and DELETING them.
	 * 
	 * b) Negative cases:
	 * 2. Item doesn't belong Provider
	 * 
	 * c) Sentence coverage
	 * -delete(): %
	 * -findAll(): %
	 * -findOne(): %
	 * d) Data coverage
	 */

	@Test
	public void driverDeleteItem() {
		final Object testingData[][] = {

			{
				"Provider1", "item1", null
			//1. All right
			}, {
				"Provider2", "item1", IllegalArgumentException.class
			//2. Item doesn't belong Provider
			}

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateDeleteItem((String) testingData[i][0], (String) testingData[i][1], (Class<?>) testingData[i][2]);

	}
	protected void templateDeleteItem(final String provider, final String itemId, final Class<?> expected) {

		Class<?> caught;

		caught = null;
		try {
			super.authenticate(provider);

			final Item item = this.itemService.findOne(super.getEntityId(itemId));

			this.itemService.delete(item);
			this.itemService.flush();

			final Collection<Item> items = this.itemService.findAll();
			Assert.isTrue(!items.contains(item));

		} catch (final Throwable oops) {
			caught = oops.getClass();
		}
		super.unauthenticate();
		super.checkExceptions(expected, caught);

	}

	/*
	 * -------Coverage ItemService
	 * ----TOTAL SENTENCE COVERAGE:
	 * ItemService = %
	 * 
	 * ----TOTAL DATA COVERAGE:
	 * Item = 0%
	 */
}
