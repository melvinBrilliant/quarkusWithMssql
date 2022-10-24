package org.melvin.first.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class Region extends PanacheEntity {

    @Column(name = "RegionDescription")
    private String regionDescription;

    @OneToMany(mappedBy = "regionID")
    private Set<Territory> territories = new LinkedHashSet<>();

}
