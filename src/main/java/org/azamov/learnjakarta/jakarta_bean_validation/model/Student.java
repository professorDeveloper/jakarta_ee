package org.azamov.learnjakarta.jakarta_bean_validation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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

    @NotBlank
    @Column(name = "full_name")
    String fullName;

    @Min(value = 18)
    int age;

    @Column(name = "group_id")
    @Positive
    int groupId;

    @Column(name = "created_by")
    @Positive
    int createdBy;
}
