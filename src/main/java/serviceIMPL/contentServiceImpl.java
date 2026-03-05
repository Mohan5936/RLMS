package serviceIMPL;

import com.Entity.Content;
import com.dto.ContentDto;

public interface contentServiceImpl {

	public Content saveContent(ContentDto dto);
	public Content getById(long id);
	
}
