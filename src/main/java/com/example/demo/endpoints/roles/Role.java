package com.example.demo.endpoints.roles;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
  @Id
  @SequenceGenerator(
          name = "role_sequence",
          sequenceName = "role_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "role_sequence"
  )
  @Schema(example = "1", description = "Role ID")
  private Long id;

  @Schema(example = "ROLE_ADMIN", description = "unique roles name")
  @Column(name = "value", nullable = false)
  private String value;

  @Schema(example = "Administrator", description = "description of the role")
  @Column(name = "description", nullable = false)
  private String description;

  public Role() {
  }

  public Role(String value, String description) {
    this.value = value;
    this.description = description;
  }

  public Role(Long id, String value, String description) {
    this.id = id;
    this.value = value;
    this.description = description;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return "Role{" +
            "id=" + id +
            ", value='" + value + '\'' +
            ", description='" + description + '\'' +
            '}';
  }

//   @BelongsToMany(() => User, () => UserRoles)
//   users: User[];



//   @Table(name = "user_roles")
//
//   @Id
//   @Column({
//     type: DataType.INTEGER,
//     unique: true,
//     autoIncrement: true,
//     primaryKey: true,
//   })
//  Long id;
//
//  @ForeignKey(() => Role)
//  @Column({ type: DataType.INTEGER })
//  Integer roleId;
//
//  @ForeignKey(() => User)
//  @Column({ type: DataType.INTEGER })
//  Integer userId;
}
