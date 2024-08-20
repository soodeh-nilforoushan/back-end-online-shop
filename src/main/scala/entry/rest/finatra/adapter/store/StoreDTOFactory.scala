package entry.rest.finatra.adapter.store

import domain.store.Item
import entry.rest.finatra.adapter.store.api.ItemDTO

object StoreDTOFactory {

def item: Item => ItemDTO = o =>
  ItemDTO(o.id, o.name, o.description, o.price, o.stock)
}
