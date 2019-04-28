
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ItemRepository;
import domain.Item;

@Service
@Transactional
public class ItemService {

	// Managed Repository ------------------------
	@Autowired
	private ItemRepository	itemRepository;


	public Collection<Item> findAll() {

		final Collection<Item> items = this.itemRepository.findAll();

		Assert.notNull(items);

		return items;
	}

	public Item findOne(final int itemId) {

		final Item item = this.itemRepository.findOne(itemId);

		return item;

	}

	//Other business methods
	public Collection<Item> findItemsByProviderId(final int providerId) {

		final Collection<Item> res = this.itemRepository.findItemsByProviderId(providerId);

		return res;
	}

}
