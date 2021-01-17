package com.example.survey.service

import com.example.survey.exceptions.AssessmentNotFoundException
import com.example.survey.repository.AssessmentRepository
import com.example.survey.repository.UserRepository
import com.example.survey.views.AssessmentView
import org.json.JSONObject
import org.springframework.stereotype.Service

@Service
class AssessmentService(
    private val assessmentRepository: AssessmentRepository,
    private val userRepository: UserRepository
) {

    fun get(id: Long): AssessmentView {
        return assessmentRepository.findById(id)
            .map { AssessmentView(it) }
            .orElseThrow { AssessmentNotFoundException() }
    }

    fun updateAnswers(id: Long, answers: JSONObject) {
        assessmentRepository.findById(id).ifPresentOrElse(
            {
                it.answer = answers
                assessmentRepository.save(it)
            },
            {
                throw AssessmentNotFoundException()
            }
        )
    }

}