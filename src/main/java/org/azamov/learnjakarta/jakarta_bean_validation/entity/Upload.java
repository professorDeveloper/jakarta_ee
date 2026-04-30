package org.azamov.learnjakarta.jakarta_bean_validation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
public class Upload extends  Base {
    @Column(nullable = false)
    private String generatedName;
    @Column(nullable = false)
    private String originalName;
    @Column(nullable = false)
    private String mimeType;
    @Column(nullable = false)
    private long size;
    @Column(nullable = false)
    private String extension;

    @Builder(builderMethodName = "childMethodBuilder")
    public Upload(String generatedName, String originalName, String mimeType, long size, String extension) {
        this.generatedName = generatedName;
        this.originalName = originalName;
        this.mimeType = mimeType;
        this.size = size;
        this.extension = extension;
    }
}
