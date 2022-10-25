package org.melvin.first.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "territory")
public class Territory extends PanacheEntityBase {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "territory_description")
    private String territoryDescription;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = false)
    private Region regionID;

    public static Long countTerritoryByRegion(Integer id) {
        return Territory.count("RegionID", id);
    }

}
