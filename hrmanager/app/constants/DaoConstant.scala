package constants
object DaoConstant {
  val DEFAULT_PERSISTENCE_UNIT: String = "defaultPersistenceUnit"
  var ROLE_DAO_FIND_ROLE_ALL = "RoleDao.findRoleAll"
  var ROLE_DAO_FIND_ROLE_ID = "RoleDao.findRoleById"

  var USER_DAO_FIND_USER_ALL = "UserDao.findUserAll"
  var USER_DAO_FIND_EMAIL = "UserDao.findUserByEmail"
}