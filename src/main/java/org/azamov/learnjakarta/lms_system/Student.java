package org.azamov.learnjakarta.lms_system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Student {
    private String id;
    private String createdAt;
    private String fullName;
    private int age;
    private String groupId;
}
