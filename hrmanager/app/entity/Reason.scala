package entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "reason")
class Reason {

  @Id
  @Column(name = "reasonId", nullable = false, length = 1)
  var reasonId: Int = _

  @Column(name = "reasonName", nullable = false, length = 255)
  var reasonName: String = _
}