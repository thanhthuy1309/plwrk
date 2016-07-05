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
@Table(name = "role")
class Role extends Serializable {

  @Id
  @Column(name = "roleid", nullable = false, length = 1)
  var roleId: String = _

  @Column(name = "rolename", nullable = false, length = 255)
  var roleName: String = _
}