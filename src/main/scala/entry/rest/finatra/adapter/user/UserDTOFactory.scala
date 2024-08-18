package entry.rest.finatra.adapter.user

import domain.user.{Session, User}
import entry.rest.finatra.adapter.user.api.{SessionDTO, UserDTO}

object UserDTOFactory{


  def session: Session => SessionDTO = o =>
    SessionDTO(o.key, o.userID, o.username)


  def user: User =>UserDTO = o=>
    UserDTO(o.id, o.username, o.password, o.name, o.email,o.role)
}


