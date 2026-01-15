package com.portal.job_service.repository;

import com.portal.job_service.entity.Job;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.Set;

@Repository
public interface JobRespository extends ReactiveCrudRepository<Job,String> {

    Flux<Job> findBySkillsIn(Set<String> skills);
}
