package org.melvin.first.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Territory extends PanacheEntityBase {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "TerritoryDescription")
    private String territoryDescription;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "RegionID", nullable = false)
    private Region regionID;

    public static Long countTerritoryByRegion(Integer id) {
        return Territory.count("RegionID", id);
    }

}
