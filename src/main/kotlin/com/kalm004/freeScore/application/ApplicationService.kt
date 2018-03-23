package com.kalm004.freeScore.application

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ApplicationService(@Autowired var applicationRepository: ApplicationRepository) {
    fun getAllApplications() = applicationRepository.getAll()
}