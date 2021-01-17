package com.example.survey.controller

import com.example.survey.config.Logging
import com.example.survey.config.logger
import com.example.survey.service.AssessmentService
import com.example.survey.views.AssessmentView
import org.json.JSONObject
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/assessments")
class AssessmentController(
    private val assessmentService: AssessmentService
): Logging {

    @GetMapping("/{id}")
    @ResponseBody
    fun getAssessment(@PathVariable id: Long): ResponseEntity<AssessmentView> {
        logger().debug("Retrieving assessment + answers + general info of a risk account")
        return ResponseEntity.ok(assessmentService.get(id))
    }

    @PutMapping
    fun updateAssessmentAnswers(@RequestBody answers: String): ResponseEntity<Any> {
        logger().info("Update assessment answers of the risk account")
        // TODO check out the request input (ask to lucho or may) / check file that creates the json template!!
        val jsonAnswers = JSONObject(answers)
        val assessmentId = jsonAnswers["assessment_id"] as Long
        assessmentService.updateAnswers(assessmentId, jsonAnswers)
        return ResponseEntity.accepted().build()
    }

}