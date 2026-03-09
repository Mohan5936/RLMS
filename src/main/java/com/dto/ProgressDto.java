package com.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProgressDto {

	private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotNull(message = "Lesson/Content ID is required")
    private Long lessonId;

    private boolean isCompleted;
    
    private LocalDateTime completionDate;
}
