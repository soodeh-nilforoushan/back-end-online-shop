package entry.rest.finatra.adapter.user

import contract.service.user.{SignInService, SignOutService, SignUpService}
import entry.rest.finatra.adapter.user.api.{SignInRequestDTO, SignOutRequestDTO, SignUpRequestDTO}
import entry.rest.finatra.{RequestWrapper, UnauthenticatedRequestWrapper}

object UserFactory {


  def signInRequest: (UnauthenticatedRequestWrapper, SignInRequestDTO) => SignInService.Request = (_, dto) =>
    SignInService.Request(dto.username, dto.password)

  def signOuRequest: (RequestWrapper, SignOutRequestDTO) => SignOutService.Request = (rw, _) =>
    SignOutService.Request(rw.getKey)

  def signUpRequest: (UnauthenticatedRequestWrapper, SignUpRequestDTO) => SignUpService.Request = (_, dto) =>
    SignUpService.Request(dto.username, dto.password, dto.name, dto.eMail, dto.role)

}
