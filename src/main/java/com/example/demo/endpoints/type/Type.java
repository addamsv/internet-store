package com.example.demo.endpoints.type;

import com.example.demo.endpoints.brand.Brand;
import com.example.demo.endpoints.device.Device;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;;import java.util.List;


@Entity
@Table(name = "type")
public class Type {
  @Id
  @SequenceGenerator(name = "type_sequence", sequenceName = "type_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "type_sequence")
  @Schema(example = "3", description = "Type ID")
  private Long id;


  @Schema(example = "Laptop", description = "Type of the Device")
  @Column(name = "name", nullable = false)
  private String name;


  @OneToMany(mappedBy = "type")
  private List<Device> devices;
  //  @HasMany(() => Device)
  //  device: Device[];


  /* Owner */
  @ManyToMany
  @JoinTable(
      name = "type_brand",
      joinColumns = @JoinColumn(name = "type_id"),
      inverseJoinColumns = @JoinColumn(name = "brand_id")
  )
  private List<Brand> brands;
  //  @BelongsToMany(() => Brand, () => TypeBrand)
  //  brands: Brand[];

  //  @Table({ tableName: 'type_brand', createdAt: false, updatedAt: false })
  //  export class TypeBrand extends Model<TypeBrand> {
  //    @Column({type: DataType.INTEGER,unique: true, autoIncrement: true,primaryKey: true })
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


  public Type() {}

  public Type(String name) {
    this.name = name;
  }

  public Type(Long id, String name) {
    this.id = id;
    this.name = name;
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

  public List<Device> getDevices() {
    return devices;
  }

  public void setDevices(List<Device> devices) {
    this.devices = devices;
  }

  public List<Brand> getBrands() {
    return brands;
  }

  public void setBrands(List<Brand> brands) {
    this.brands = brands;
  }

  @Override
  public String toString() {
    return "Type{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", devices=" + devices +
            ", brands=" + brands +
            '}';
  }
}
