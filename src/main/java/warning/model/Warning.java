package warning.model;

import lombok.Builder;
import lombok.Data;
import student.model.Student;

@Data
@Builder
public class Warning {
    private long warningId;
    private String warningText;
    private Student student;
}
