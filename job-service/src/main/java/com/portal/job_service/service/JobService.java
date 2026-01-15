package com.portal.job_service.service;

import com.portal.job_service.dto.JobDto;
import com.portal.job_service.repository.JobRespository;
import com.portal.job_service.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Service
public class JobService {

    @Autowired
    private JobRespository jobRespository;

    public Flux<JobDto> allJobs(){
        return this.jobRespository.findAll().map(EntityDtoUtil::toDto);
    }

    public Flux<JobDto> jobBySkillsIn(Set<String> skills){
        return this.jobRespository.findBySkillsIn(skills).map(EntityDtoUtil::toDto);
    }

    public Mono<JobDto> save(Mono<JobDto> mono){
        return mono
                .map(EntityDtoUtil::toEntity)
                .flatMap(this.jobRespository::save)
                .map(EntityDtoUtil::toDto);
    }

}
