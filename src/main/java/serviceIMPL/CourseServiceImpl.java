package serviceIMPL;

import com.Entity.Course;
import com.dto.CourseDto;

public interface CourseServiceImpl {

	public Course saveCourse(CourseDto course);
	public Course getById(long id);
}
