package com.bussin.SpringDirections.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlannedRoute implements Serializable {
    @Id
    @Type(type = "org.hibernate.type.UUIDCharType")
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @NotNull(message = "Please set a starting point")
    private String plannedFrom;

    @NotNull(message = "Please set a destination.")
    private String plannedTo;

    @NotNull(message = "Date and time should not be empty")
    private LocalDateTime dateTime;

//    Store longitude and latitude as BigDecimals of precision 6 and scale 9,
//    8 respectively
//    https://stackoverflow.com/questions/1196415/what-datatype-to-use-when-storing-latitude-and-longitude-data-in-sql-databases
   @Column(scale = 9, precision = 6)
   private BigDecimal originLongitude;

   @Column(scale = 8, precision = 6)
   private BigDecimal originLatitude;

   @Column(scale = 9, precision = 6)
   private BigDecimal destLongitude;

   @Column(scale = 8, precision = 6)
   private BigDecimal destLatitude;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        PlannedRoute that = (PlannedRoute) o;
        return plannedFrom != null && Objects.equals(plannedFrom, that.plannedFrom);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
