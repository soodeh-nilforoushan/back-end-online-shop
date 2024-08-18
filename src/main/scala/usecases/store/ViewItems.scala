package usecases.store

import data.repository.ItemRepository
import domain.store.Item

class ViewItems(itemRepository: ItemRepository) {
  def execute(): List[Item] = {
    itemRepository.getAll
  }
}