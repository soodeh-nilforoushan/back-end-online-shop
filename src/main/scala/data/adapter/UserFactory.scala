package data.adapter

import domain.user.User
import scalikejdbc.WrappedResultSet

object UserFactory {

  def user(dto: WrappedResultSet): User =
    User(
      dto int "id", dto string "userName", dto string "password",dto string "name" , dto string "email", dto string  "User Role")


}
