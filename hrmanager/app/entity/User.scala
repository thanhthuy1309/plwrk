package entity

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.GenerationType
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Column
import java.util.Date
import javax.persistence.Temporal
import javax.persistence.TemporalType

@Entity
@Table(name = "user")
class User extends Serializable {

  @Id
  @Column(name = "email", nullable = false, length = 255)
  var email: String = _

  @Column(name = "name", nullable = false, length = 500)
  var name: String = _

  @Column(name = "dateborn")
  @Temporal(TemporalType.DATE)
  var dateBorn: Date = _

  @Column(name = "role", length = 1)
  var role: String = _
  
  @Column(name = "password")
  var passWord: String = _
}