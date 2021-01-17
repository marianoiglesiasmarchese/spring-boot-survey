package com.example.survey.service

import com.example.survey.exceptions.UserNotFoundException
import com.example.survey.model.Assessment
import com.example.survey.model.User
import com.example.survey.repository.AssessmentRepository
import com.example.survey.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val assessmentRepository: AssessmentRepository
) {

    fun login(email: String): User {
        return userRepository
            .findByEmail(email)
            .orElseThrow {  UserNotFoundException() }
    }

    fun addAssessment(id: Long){
        userRepository.findById(id).ifPresentOrElse(
            {
                it.assessments.add(Assessment())
                userRepository.save(it)
            },
            {
                throw UserNotFoundException()
            }
        )
    }
}
