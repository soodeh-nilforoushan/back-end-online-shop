package modules

import com.twitter.inject.TwitterModule
import data.repository.SessionRepository

object CallbackModule extends TwitterModule {

  protected override def configure(): Unit = {

    // Auth
    bind(classOf[contract.callback.user.SessionCallback]) to classOf[SessionRepository]
    bind(classOf[contract.callback.user.UserCallback]) to classOf[data.repository.UserRepository]
    // store
    bind(classOf[contract.callback.store.ItemCallback]) to classOf[data.repository.ItemRepository]


  }

}
