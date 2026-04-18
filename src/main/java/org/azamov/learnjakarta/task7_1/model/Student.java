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
@Entity
@Table(name = "students", schema = "jdbc_example")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "full_name")
    String fullName;

    int age;

    @Column(name = "group_id")
    int groupId;

    @Column(name = "created_by")
    int createdBy;
}
