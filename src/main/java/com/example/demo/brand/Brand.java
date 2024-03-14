package com.example.demo.brand;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

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
    @Schema(example = "1", description = "Brand ID")
    private Long id;


    @Schema(example = "Mi", description = "Devices Brand")
    @Column(name = "name", nullable = false)
    private String name;

//    @HasMany(() => Device)
//    device: Device[];
//
//    @BelongsToMany(() => Type, () => TypeBrand)
//    types: Type[];

    public Brand(Long id, String name) {
        this.id = id;
        this.name = name;
    }
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

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
