package com.portal.job_service.controller;

import com.portal.job_service.dto.JobDto;
import com.portal.job_service.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@RestController
@RequestMapping("job")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping("all")
    public Flux<JobDto> all(){
        return this.jobService.allJobs();
    }

    @GetMapping("search")
    public Flux<JobDto> search(@RequestParam Set<String> skills){
        return this.jobService.jobBySkillsIn(skills);
    }

    @PostMapping
    public Mono<JobDto> save(@RequestBody Mono<JobDto> mono){
        return this.jobService.save(mono);
    }
}
