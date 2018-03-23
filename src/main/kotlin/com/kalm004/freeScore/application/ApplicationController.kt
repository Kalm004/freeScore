package com.kalm004.freeScore.application

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApplicationController(@Autowired var applicationService : ApplicationService) {

    @GetMapping("/applications")
    fun getAll() = applicationService.getAllApplications()

}