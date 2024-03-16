package com.example.demo.endpoints.type;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;;

//import { ApiProperty } from '@nestjs/swagger';
//import {
//  Column,
//  DataType,
//  Model,
//  Table,
//  HasMany,
//  BelongsToMany,
//} from 'sequelize-typescript';
//import { Brand } from 'src/brand/brand.model';
//import { Device } from 'src/device/device.model';
//import { TypeBrand } from './type-brand.model';
@Entity
@Table(name = "type")
public class Type {// extends Model<Type>
  @Id
  @SequenceGenerator(
          name = "type_sequence",
          sequenceName = "type_sequence",
          allocationSize = 1
  )
  @GeneratedValue(
          strategy = GenerationType.SEQUENCE,
          generator = "type_sequence"
  )
  @Schema(example = "1", description = "Type ID")
  private Long id;


  @Schema(example = "Refrigerators", description = "Type of the Device")
  @Column(name = "name", nullable = false)
  private String name;

  public Type(String name) {
    this.name = name;
  }

  public Type() {
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

  @Override
  public String toString() {
    return "Type{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }

//  @Table({ tableName: 'type_brand', createdAt: false, updatedAt: false })
//  export class TypeBrand extends Model<TypeBrand> {
//    @Column({
//            type: DataType.INTEGER,
//            unique: true,
//            autoIncrement: true,
//            primaryKey: true,
//            })
//    id: number;
//
//    @ForeignKey(() => Type)
//    @Column({ type: DataType.INTEGER })
//    typeId: number;
//
//    @ForeignKey(() => Brand)
//    @Column({ type: DataType.INTEGER })
//    brandId: number;
//  }
//  @HasMany(() => Device)
//  device: Device[];
//
//  @BelongsToMany(() => Brand, () => TypeBrand)
//  brands: Brand[];
}
