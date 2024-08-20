package domain.user


case class UserPermission( userID: Long,  permission: UserPermission.Permission)

object UserPermission {
  type Permission = Long

  object Permission {

    final val ADD_ITEM: Permission = 1
    final val ADD_To_BASKET: Permission = 2
    final val GET_ALL_BASKET: Permission = 4
    final val GET_ITEM: Permission = 5
    final val REMOVE_FROM_BASKET: Permission = 6
    final val UPDATE_BASKET: Permission = 7
    final val UPDATE_ITEM: Permission = 8
    final val REMOVE_ITEM: Permission = 9
  }

}



