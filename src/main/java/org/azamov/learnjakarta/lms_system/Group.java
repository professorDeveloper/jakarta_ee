package org.azamov.learnjakarta.lms_system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Group {
    private String id;
    private String name;
    private String createdAt;
    private int studentCount;
}
