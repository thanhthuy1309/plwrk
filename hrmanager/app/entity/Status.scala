package entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "status")
class Status {

  @Id
  @Column(name = "statusId", nullable = false, length = 1)
  var statusId: Int = _

  @Column(name = "statusName", nullable = false, length = 255)
  var statusName: String = _
}