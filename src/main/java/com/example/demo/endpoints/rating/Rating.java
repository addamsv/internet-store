package com.example.demo.endpoints.rating;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {
  @Id
  @SequenceGenerator(
          name = "rating_sequence",
          sequenceName = "rating_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "rating_sequence"
  )
  @Schema(example = "3", description = "Rating ID")
  private Long id;

  @Schema(example = "10", description = "Rating of the Device")
  @Column(name = "rate", nullable = false)
  private Integer rate = 10;

  public Rating() {
  }

  public Rating(Integer rate) {
    this.rate = rate;
  }

  public Rating(Long id, Integer rate) {
    this.id = id;
    this.rate = rate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getRate() {
    return rate;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }

  @Override
  public String toString() {
    return "Rating{" +
            "id=" + id +
            ", rate=" + rate +
            '}';
  }

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
