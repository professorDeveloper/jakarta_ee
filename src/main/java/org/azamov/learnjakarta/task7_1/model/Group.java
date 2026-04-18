package org.azamov.learnjakarta.task7_1.model;

import jakarta.persistence.*;
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

    String name;

    @Column(name = "created_at")
    String createdAt;

    @Transient
    int studentCount;

    @Column(name = "created_by")
    int createdBy;
}
