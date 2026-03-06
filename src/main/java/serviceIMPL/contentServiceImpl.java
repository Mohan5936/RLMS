package serviceIMPL;

import java.util.List;

import com.Entity.Content;
import com.dto.ContentDto;

public interface contentServiceImpl {

	public Content saveContent(ContentDto dto);
	
	public Content getById(long id);
;
	
//	List<ContentDto> getContentByCourseId(Long courseId);
//	public Course getByCourseId(long courseId);
}
