package serviceIMPL;

import com.Entity.Content;
import com.Entity.Course;
import com.dto.ContentDto;

public interface contentServiceImpl {

	public Content saveContent(ContentDto dto);
	public Content getById(long id);
//	public Course getByCourseId(long courseId);
}
