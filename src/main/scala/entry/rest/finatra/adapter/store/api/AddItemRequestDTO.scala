package entry.rest.finatra.adapter.user.store.api

case class AddItemRequestDTO(userIdDTO: Long, nameDTO: String, descriptionDTO: String, priceDTO: Double, stockDTO:Long)
