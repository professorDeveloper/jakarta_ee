package org.azamov.learnjakarta.jakarta_bean_validation.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@ToString
public class Book extends Base {
    private String title;
    private String author;
    private String description;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Upload cover;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Upload pdf;

    @Builder(builderMethodName = "childMethodBuilder")
    public Book(String title, String author, String description, Upload cover, Upload pdf) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.cover = cover;
        this.pdf = pdf;
    }
}
