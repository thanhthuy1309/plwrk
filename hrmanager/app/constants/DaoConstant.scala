package constants
object DaoConstant {
  val DEFAULT_PERSISTENCE_UNIT: String = "defaultPersistenceUnit"
  var ROLE_DAO_FIND_ROLE_ALL: String = "RoleDao.findRoleAll"
  var ROLE_DAO_FIND_ROLE_ID: String = "RoleDao.findRoleById"
  
  var DEPARMENT_DAO_FIND_DEPARMENT_ALL: String = "DeparmentDao.findDepartmentAll"
  var DEPARMENT_DAO_FIND_DEPARMENT_ID: String = "DeparmentDao.findDepartmentById"

  var USER_DAO_FIND_USER_ALL: String = "UserDao.findUserAll"
  var USER_DAO_FIND_EMAIL: String = "UserDao.findUserByEmail"
}