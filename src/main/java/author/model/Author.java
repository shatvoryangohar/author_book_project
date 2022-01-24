package author.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author implements Serializable {

    private String name;
    private String surname;
    private String email;
    private int age;
    private Gender gender;
    private Date dateOfBirth;

}

