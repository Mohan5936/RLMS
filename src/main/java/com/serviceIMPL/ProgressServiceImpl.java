package com.serviceIMPL;

import java.util.List;

import com.dto.ProgressDto;

public interface ProgressServiceImpl {

    ProgressDto updateProgress(ProgressDto progressDto);
    
    List<ProgressDto> getUserProgress(Long userId);
}