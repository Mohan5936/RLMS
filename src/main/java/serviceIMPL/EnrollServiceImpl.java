package serviceIMPL;

import java.util.List;

import com.Entity.Enrollment;
import com.dto.EnrollmentDto;

public interface EnrollServiceImpl {

	Enrollment enrollUser(EnrollmentDto dto);
    List<Enrollment> getEnrollmentsByUserId(Long userId);
    List<Enrollment> getEnrollmentsByCourseId(Long courseId);
}
