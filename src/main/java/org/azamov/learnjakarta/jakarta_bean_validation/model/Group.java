package org.azamov.learnjakarta.jakarta_bean_validation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "Groups")
@Table(name = "groups", schema = "jdbc_example")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotBlank
    String name;

    @Column(name = "created_at")
    String createdAt;

    @Transient
    @PositiveOrZero
    int studentCount;

    @Column(name = "created_by")
    @Positive
    int createdBy;
}
