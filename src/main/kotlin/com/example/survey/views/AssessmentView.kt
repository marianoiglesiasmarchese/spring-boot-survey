package com.example.survey.views

import com.example.survey.model.Assessment
import com.fasterxml.jackson.annotation.JsonFormat
import org.json.JSONObject
import org.json.JSONTokener
import java.time.Instant

data class AssessmentProgress(val answered: Int, val total: Int)

data class AssessmentGeneralInfoView(
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    val lastSent: Instant,
    val securityQuestionsProgress: AssessmentProgress,
    val businessQuestionsProgress: AssessmentProgress,
    val brokerBusinessAnswersLastChange: Long,
    val buyerBusinessAnswersLastChange: Long,
    val brokerSecurityAnswersLastChange: Long,
    val buyerSecurityAnswersLastChange: Long
)

data class AssessmentView(val assessment: Assessment) {
    fun getTemplate(): Map<String, Any> {
        val schemaStream = javaClass.getResourceAsStream("/templates/assessment-template.json")
        val rawAssessment = JSONObject(JSONTokener(schemaStream))
        return rawAssessment.toMap()
    }

    fun getAnswers(): Map<String, Any>{
        return if (assessment.answer != null) {
            assessment.answer!!.toMap()
        } else {
            val schemaStream = javaClass.getResourceAsStream("/templates/assessment-answers.json")
            val rawAnswer = JSONObject(JSONTokener(schemaStream))
            rawAnswer.toMap()
        }
    }

    fun getGeneralInfo(): AssessmentGeneralInfoView {
        var brokerBusinessAnswersLastChange = 0L
        var brokerSecurityAnswersLastChange = 0L
        var buyerBusinessAnswersLastChange = 0L
        var buyerSecurityAnswersLastChange = 0L
        if (this.assessment.answer != null) {
            brokerBusinessAnswersLastChange = this.assessment.answer!!.getLong("brokerBusinessAnswersLastChange")
            brokerSecurityAnswersLastChange = this.assessment.answer!!.getLong("brokerSecurityAnswersLastChange")
            buyerBusinessAnswersLastChange = this.assessment.answer!!.getLong("buyerBusinessAnswersLastChange")
            buyerSecurityAnswersLastChange = this.assessment.answer!!.getLong("buyerSecurityAnswersLastChange")
        }
        return AssessmentGeneralInfoView(
            lastSent = this.assessment.lastSent,
            securityQuestionsProgress = assessment.getSecurityProgress(),
            businessQuestionsProgress = assessment.getBusinessProgress(),
            brokerBusinessAnswersLastChange = brokerBusinessAnswersLastChange,
            brokerSecurityAnswersLastChange = brokerSecurityAnswersLastChange,
            buyerBusinessAnswersLastChange = buyerBusinessAnswersLastChange,
            buyerSecurityAnswersLastChange = buyerSecurityAnswersLastChange
        )
    }
}