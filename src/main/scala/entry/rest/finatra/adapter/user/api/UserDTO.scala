package entry.rest.finatra.adapter.user.api

import domain.user.{User, UserPermission}

case class UserDTO (id: Long,
                    username: String,
                    password: String,
                    name: String,
                    email: String,
                    role: String)
