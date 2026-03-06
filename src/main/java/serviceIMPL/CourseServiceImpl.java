package serviceIMPL;

import com.Entity.Course;
import com.dto.CourseDto;

public interface CourseServiceImpl {

	public CourseDto saveCourse(CourseDto course);
	public CourseDto getCourseById(long id);
}
