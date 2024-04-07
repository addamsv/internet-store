package com.example.demo.endpoints.device;

import com.example.demo.endpoints.brand.Brand;
import com.example.demo.endpoints.type.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
  @Column(name = "rating", nullable = true)
  private Integer rating = 0;

  @Schema(example = "image.png", description = "Image File Name")
  @Column(name = "img", nullable = false)
  private String img;

  @ManyToOne
  /* in order to get rid of jackson mistake */
  @JsonIgnore
  @JoinColumn(name = "type_id")
  private Type type;
//  @ForeignKey(() => Type)
//  @Column( type: DataType.INTEGER )
//  Long typeId;
//  @BelongsTo(() => Type)
//  Type type;

  @ManyToOne
  /* in order to get rid of jackson mistake */
  @JsonIgnore
  @JoinColumn(name = "brand_id")
  private Brand brand;
//  @ForeignKey(() => Brand)
//  @Column( type: DataType.INTEGER )
//  Long brandId;
//  @BelongsTo(() => Brand)
//  Brand brand;

//  @HasMany(() => Rating)
//  rate: Rating[];

  //  @HasMany(() => BasketDevice)
//  basketDevice: BasketDevice[];

  @Override
  public String toString() {
    return "Device{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", rating=" + rating +
            ", img='" + img + '\'' +
            ", type=" + type +
            ", brand=" + brand +
            '}';
  }


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

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Brand getBrand() {
    return brand;
  }

  public void setBrand(Brand brand) {
    this.brand = brand;
  }


//  @HasMany(() => DeviceInfo)
//  deviceInfo: DeviceInfo[];

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
