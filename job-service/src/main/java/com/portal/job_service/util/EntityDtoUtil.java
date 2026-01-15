package com.portal.job_service.util;

import com.portal.job_service.dto.JobDto;
import com.portal.job_service.entity.Job;
import org.springframework.beans.BeanUtils;

public class EntityDtoUtil {

    public static JobDto toDto(Job job){
        JobDto jobDto = new JobDto();
        BeanUtils.copyProperties(job, jobDto);
        return jobDto;
    }

    public static Job toEntity(JobDto dto){
        Job job = new Job();
        BeanUtils.copyProperties(dto,job);
        return job;
    }
}
