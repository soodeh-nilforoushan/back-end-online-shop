package entry.rest.finatra.adapter.store

import domain.store.Item

object ItemDTOFactory {

def item: Item => ItemDTO = o =>
  ItemDTO(o.id,o.name,o.price,o.stock, o.description)
}
