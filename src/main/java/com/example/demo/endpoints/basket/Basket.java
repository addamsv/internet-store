package com.example.demo.endpoints.basket;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "basket")
public class Basket {
  @Id
  Long id;










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
