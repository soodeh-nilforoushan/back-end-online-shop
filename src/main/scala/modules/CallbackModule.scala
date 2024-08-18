package modules

import com.twitter.inject.TwitterModule

object CallbackModule extends TwitterModule {

  protected override def configure(): Unit = {

    // Auth
    bind(classOf[contract.callback.user.SessionCallback]) to classOf[repository.user.SessionRepository]
    bind(classOf[contract.callback.user.UserCallback]) to classOf[repository.user.UserRepository]
    // store
    bind(classOf[contract.callback.store.ItemCallback]) to classOf[data.repository.ItemRepository]
    bind(classOf[contract.callback.store.]) to classOf[inmem.blog.CommentRepository]
    bind(classOf[callback.blog.PostCallback]) to classOf[inmem.blog.PostRepository]

  }

}
