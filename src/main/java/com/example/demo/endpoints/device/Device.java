package com.example.demo.endpoints.device;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Entity
@Table(name = "device")
public class Device {
  public Device() {}

  public Device(String name, Integer price, Integer rating, String img) {
    this.name = name;
    this.price = price;
    this.rating = rating;
    this.img = img;
  }

  public Device(Long id, String name, Integer price, Integer rating, String img) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.rating = rating;
    this.img = img;
  }

  @Id
  @SequenceGenerator(
          name = "device_sequence",
          sequenceName = "device_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "device_sequence"
  )
  @Schema(example = "1", description = "Device ID")
  private Long id;

  @Schema(example = "Mi phone", description = "Name of the Device")
  @Column(name = "name", nullable = false)
  private String name;

  @Schema(example = "1000", description = "Price of the Device")
  @Column(name = "price", nullable = false)
  private Integer price;

  @Schema(example = "10", description = "Rating of the Device")
  @Column(name = "rating", nullable = false)
  private Integer rating = 0;

  @Schema(example = "image.png", description = "Image File Name")
  @Column(name = "img", nullable = false)
  private String img;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Device{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", rating=" + rating +
            ", img='" + img + '\'' +
            '}';
  }

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

//  @ForeignKey(() => Type)
//  @Column( type: DataType.INTEGER )
//  typeId: number;
//
//  @ForeignKey(() => Brand)
//  @Column( type: DataType.INTEGER )
//  brandId: number;
//
//  @HasMany(() => DeviceInfo)
//  deviceInfo: DeviceInfo[];
//
//  @HasMany(() => Rating)
//  rate: Rating[];
//
//  @HasMany(() => BasketDevice)
//  basketDevice: BasketDevice[];
//
//  @BelongsTo(() => Type)
//  type: Type;
//
//  @BelongsTo(() => Brand)
//  brand: Brand;











  //@Table(name = "device_info")
//  @ApiProperty({ example: '1', description: 'Unique ID' })
//  @Column({
//    type: DataType.INTEGER,
//    unique: true,
//    autoIncrement: true,
//    primaryKey: true,
//  })
//  id: number;
//
//  @ApiProperty({
//    example: 'Value',
//    description: 'Property of a device',
//  })
//  @Column({ type: DataType.STRING, allowNull: false })
//  title: string;
//
//  @ApiProperty({
//    example: '10lt',
//    description: 'Description of some property',
//  })
//  @Column({ type: DataType.STRING, allowNull: false })
//  description: string;
//
//  @ForeignKey(() => Device)
//  @Column({ type: DataType.INTEGER })
//  deviceId: number;
//
//  @BelongsTo(() => Device)
//  device: Device;
}
