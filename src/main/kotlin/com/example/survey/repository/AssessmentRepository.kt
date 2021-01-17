package com.example.survey.repository

import com.example.survey.model.Assessment
import org.springframework.data.jpa.repository.JpaRepository

interface AssessmentRepository: JpaRepository<Assessment, Long> {
}