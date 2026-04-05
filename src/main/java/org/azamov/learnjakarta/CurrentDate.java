package org.azamov.learnjakarta;

public class CurrentDate {
    public  String getDate() {
        return java.time.LocalDate.now().toString();
    }
}
