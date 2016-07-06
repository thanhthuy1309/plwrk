package constants
object DaoConstant {
  val DEFAULT_PERSISTENCE_UNIT: String = "defaultPersistenceUnit"
  var ROLE_DAO_FIND_ROLE_ALL: String = "RoleDao.findRoleAll"
  var ROLE_DAO_FIND_ROLE_ID: String = "RoleDao.findRoleById"

  var USER_DAO_FIND_USER_ALL: String = "UserDao.findUserAll"
  var USER_DAO_FIND_EMAIL: String = "UserDao.findUserByEmail"
}