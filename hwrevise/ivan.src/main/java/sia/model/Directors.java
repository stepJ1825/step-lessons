package sia.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Directors {
    private String firstName;
    private String lastName;
    private String fullName;
    private int birthYear;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
