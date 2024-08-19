package usecases.store
//
//import data.repository.{ItemRepository, UserRepository}
//
//class AddToBasket(userRepository: UserRepository, itemRepository: ItemRepository) {
//  def execute(userId: Int, itemId: Int): Boolean = {
//    for {
//      user <- userRepository.getById(userId)
//      item <- itemRepository.getById(itemId)
//    } yield {
//      userRepository.update(user.copy(basket = user.basket :+ itemId))
//      true
//    }
//  }.getOrElse(false)
//}