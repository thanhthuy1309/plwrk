package constants
object DaoConstant {
  val DEFAULT_PERSISTENCE_UNIT: String = "defaultPersistenceUnit"
  
  val ROLE_DAO_FIND_ROLE_ALL: String = "RoleDao.findRoleAll"

  val DEPARMENT_DAO_FIND_DEPARMENT_ALL: String = "DeparmentDao.findDepartmentAll"

  val USER_DAO_FIND_USER_ALL: String = "UserDao.findUserAll"
  
  val USER_DAO_FIND_EMAIL: String = "UserDao.findUserByEmail"
  
  val USER_DAO_FIND_EMAIL_PASSWORD: String = "UserDao.findUserByEmailPassword"

  val USER_DAO_FIND_SUBTRACT_EMAIL: String = "UserDao.findUserSubtractEmail"

  val EMPLOYEE_DAO_FIND_STATUS: String = "EmployeeApplyDao.findEmployeeByStatus"

  val EMPLOYEE_DAO_FIND_JOBAPPLITATIONBYEMAILSTATUS: String = "EmployeeApplyDao.findJobApplitationByEmailStatus"

  val REASON_DAO_FIND_REASON_ALL: String = "ReasonDao.findReasonAll"

  val STATUS_DAO_FIND_STATUS_ALL: String = "StatusDao.findStatusAll"

  val EMPLOYEE_DAO_FIND_ALL: String = "EmployeeApplyDao.findJobApplitationAllList"
  
  val EMPLOYEE_DAO_FIND_EMAIL: String = "EmployeeApplyDao.findEmployeeByEmail"
}