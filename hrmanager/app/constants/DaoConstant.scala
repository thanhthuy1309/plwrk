package constants
object DaoConstant {
  val DEFAULT_PERSISTENCE_UNIT: String = "defaultPersistenceUnit"
  var ROLE_DAO_FIND_ROLE_ALL: String = "RoleDao.findRoleAll"
  var ROLE_DAO_FIND_ROLE_ID: String = "RoleDao.findRoleById"
  
  var DEPARMENT_DAO_FIND_DEPARMENT_ALL: String = "DeparmentDao.findDepartmentAll"

  var USER_DAO_FIND_USER_ALL: String = "UserDao.findUserAll"
  var USER_DAO_FIND_EMAIL: String = "UserDao.findUserByEmail"
  var USER_DAO_FIND_EMAIL_PASSWORD: String = "UserDao.findUserByEmailPassword"
  
  var USER_DAO_FIND_SUBTRACT_EMAIL: String = "UserDao.findUserSubtractEmail"
  
  var EMPLOYEE_DAO_FIND_STATUS: String = "EmployeeApplyDao.findEmployeeByStatus"
  
  var EMPLOYEE_DAO_FIND_JOBAPPLITATIONBYEMAILSTATUS: String = "EmployeeApplyDao.findJobApplitationByEmailStatus"
  
  var REASON_DAO_FIND_REASON_ALL: String = "ReasonDao.findReasonAll"
  
  var STATUS_DAO_FIND_STATUS_ALL: String = "StatusDao.findStatusAll"
  
  var EMPLOYEE_DAO_FIND_EMAIL: String = "EmployeeApplyDao.findEmployeeByEmail"
}