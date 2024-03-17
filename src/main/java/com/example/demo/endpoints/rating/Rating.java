package com.example.demo.endpoints.rating;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rating")
public class Rating {

  @Id
  private Long id;

//   @ApiProperty({ example: "10", description: "Rating of the Device" })
//   @Column({ type: DataType.INTEGER, allowNull: false })
  private Integer rate;

//   @ForeignKey(() => User)
//   @Column({ type: DataType.INTEGER })
//   private Integer user_id;

//   @ForeignKey(() => Device)
//   @Column({ type: DataType.INTEGER })
//   private Integer device_id;

//   @BelongsTo(() => User)
//   user: User;
//
//   @BelongsTo(() => Device)
//   device: Device;
}
