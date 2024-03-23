package com.example.demo.endpoints.basket;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "basket")
public class Basket {
  @Id
  @SequenceGenerator(name = "basket_sequence", sequenceName = "basket_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "basket_sequence")
  @Schema(example = "1", description = "Basket ID")
  Long id;

  public Basket() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "Basket{" +
            "id=" + id +
            '}';
  }


//   @ForeignKey(() => User)
//   @Column({ type: DataType.INTEGER })
//   userId: number;
//
//   @BelongsTo(() => User)
//   user: User;
//
//   @HasMany(() => BasketDevice)
//   basketDevice: BasketDevice;







//  @Table({ tableName: 'basket_device', createdAt: false, updatedAt: false })
//  export class BasketDevice extends Model<BasketDevice> {
//    @Column({
//            type: DataType.INTEGER,
//            unique: true,
//            autoIncrement: true,
//            primaryKey: true,
//            })
//    id: number;
//
//    @ForeignKey(() => Device)
//    @Column({ type: DataType.INTEGER })
//    device_id: number;
//
//    @ForeignKey(() => Basket)
//    @Column({ type: DataType.INTEGER })
//    basket_id: number;
//
//    @BelongsTo(() => Basket)
//    basket: Basket;
//
//    @BelongsTo(() => Device)
//    device: Device;
//  }
}
