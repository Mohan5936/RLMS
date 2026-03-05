package serviceIMPL;

import com.Entity.Progress;
import com.dto.ProgressDto;

public interface ProgressServiceImpl {
	Progress markAsComplete(ProgressDto dto);
}
