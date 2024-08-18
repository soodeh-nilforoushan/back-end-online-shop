package modules


import com.twitter.inject.TwitterModule

object ServiceModule extends TwitterModule {

  protected override def configure(): Unit = {

    // Auth
    bind(classOf[contract.service.user.AuthorizeService]) to classOf[usecases.user.AuthorizeUseCase]
    bind(classOf[contract.service.user.SignInService]) to classOf[usecases.user.SignInUseCase]
    bind(classOf[contract.service.user.SignOutService]) to classOf[usecases.user.SignOutUseCase]
    bind(classOf[contract.service.user.SignUpService]) to classOf[usecases.user.SignUpUseCase]
    // store
    bind(classOf[contract.service.store.AddItemService]) to classOf[usecases.store.AddItemUseCase]
    bind(classOf[contract.service.store.UpdateItemService]) to classOf[usecases.store.UpdateItemUseCase]
    bind(classOf[contract.service.store.RemoveItemService]) to classOf[usecases.store.RemoveItemUseCase]


  }

}
