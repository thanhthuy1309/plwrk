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
  @JoinColumn(name = "emailEmployee", referencedColumnName = "email", nullable = false)
  var emailEmployee: User = _
  
  @ManyToOne
  @JoinColumn(name = "emailManager", referencedColumnName = "email", nullable = false)
  var emailManager: User = _
  
  @ManyToOne
  @JoinColumn(name = "deparmentid", referencedColumnName = "deparmentid", nullable = false)
  var deparment: Deparment = _
  
  @Column(name = "fromDate", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  var fromDate: Date = _
  
  @Column(name = "toDate", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  var toDate: Date = _
  
  @Column(name = "submitDate")
  @Temporal(TemporalType.TIMESTAMP)
  var submitDate: Date = _
  
  @ManyToOne
  @JoinColumn(name = "reasonId", referencedColumnName = "reasonId", nullable = false)
  var reason: Reason = _
  
  @ManyToOne
  @JoinColumn(name = "statusId", referencedColumnName = "statusId", nullable = false)
  var status: Status = _
}