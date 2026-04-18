package org.azamov.learnjakarta.task2.java.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private int id;
    private String name;
    private String username;
    private String password;

}
