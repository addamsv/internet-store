package com.example.demo.endpoints.brand;

import com.example.demo.endpoints.device.Device;
import com.example.demo.endpoints.type.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "brand")
public class Brand {
    @Id
    @SequenceGenerator(
        name = "brand_sequence",
        sequenceName = "brand_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "brand_sequence"
    )
    @Schema(example = "5", description = "Brand ID")
    private Long id;

    @Schema(example = "Mi", description = "Devices Brand")
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "brand")
    private List<Device> devices;
//    @HasMany(() => Device)
//    device: Device[];

    @ManyToMany(mappedBy = "brands")
    private List<Type> types;
//    @BelongsToMany(() => Type, () => TypeBrand)
//    types: Type[];




    public Brand(String name) {
        this.name = name;
    }
    public Brand() {}

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

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", devices=" + devices +
                ", types=" + types +
                '}';
    }
}
