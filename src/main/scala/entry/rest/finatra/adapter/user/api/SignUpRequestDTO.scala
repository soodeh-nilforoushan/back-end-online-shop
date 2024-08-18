package entry.rest.finatra.adapter.user.api

case class SignUpRequestDTO(username: String, password: String, name: String, eMail: String, role: String)
