package entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence._
import java.util.Date

@Entity
@Table(name = "employee_apply")
class EmployeeApply {
  
  @Id
  @Column(name = "id", nullable = false)
  var id: Int = _
  
  @ManyToOne
  @JoinColumn(name = "emailEmployee", referencedColumnName = "email")
  var emailEmployee: User = _
  
  @ManyToOne
  @JoinColumn(name = "emailManager", referencedColumnName = "email")
  var emailManager: User = _
  
  @ManyToOne
  @JoinColumn(name = "deparmentid", referencedColumnName = "deparmentid")
  var deparment: Deparment = _
  
  @Column(name = "fromDate")
  @Temporal(TemporalType.DATE)
  var fromDate: Date = _
  
  @Column(name = "toDate")
  @Temporal(TemporalType.DATE)
  var toDate: Date = _
  
  @Column(name = "reason", length = 255)
  var reason: String = _
  
  @Column(name = "status", nullable = false)
  var status: Int = _
}