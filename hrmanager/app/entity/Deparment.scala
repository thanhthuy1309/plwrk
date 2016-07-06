package entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "deparment")
class Deparment {

  @Id
  @Column(name = "deparmentid", nullable = false)
  var deparmentId: Int = _

  @Column(name = "deparmentname", nullable = false, length = 255)
  var deparmentName: String = _
}