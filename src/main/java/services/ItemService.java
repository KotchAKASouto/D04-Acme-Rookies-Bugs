package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ItemRepository;

@Service
@Transactional
public class ItemService {

	// Managed Repository ------------------------
			@Autowired
			private ItemRepository	itemRepository;
}
