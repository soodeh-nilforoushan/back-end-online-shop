
package domain.user


case class User(id: Long,
                username: String,
                password: String,
                name: String,
                email: String ,
                role: User.Role
                 )

object User{
  type Role= String
  object Role{
    val SELLER :Role="SELLER"
    val CUSTOMER: Role="CUSTOMER"

  }
}