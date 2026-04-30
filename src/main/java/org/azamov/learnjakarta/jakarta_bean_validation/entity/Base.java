package org.azamov.learnjakarta.jakarta_bean_validation.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@MappedSuperclass
@Getter
@Setter

public class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
