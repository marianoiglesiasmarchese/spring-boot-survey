package com.example.survey.model

import com.example.survey.utils.JSONObjectConverter
import com.example.survey.views.AssessmentProgress
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener
import java.time.Instant
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Lob

@Entity
class Assessment(
    @field: [Id GeneratedValue(strategy = GenerationType.IDENTITY)]
    var id: Long? = null,

    @field: Enumerated(EnumType.STRING)
    val assessmentStatus: AssessmentStatus = AssessmentStatus.Not_sent,

    @field: JsonFormat(shape = JsonFormat.Shape.NUMBER)
    val lastSent: Instant = Instant.EPOCH,

    @field: [JsonIgnore Lob Convert(converter = JSONObjectConverter::class)]
    var answer: JSONObject? = null
){

    @JsonIgnore
    fun getSecurityProgress(): AssessmentProgress {
        val total = getTotalSecurityQuestions()
        val answeredQuestions = getAnsweredQuestionsForGroup("G2")
        return AssessmentProgress(total = total, answered = answeredQuestions)
    }

    @JsonIgnore
    fun getBusinessProgress(): AssessmentProgress {
        val total = getTotalBusinessQuestions()
        val answeredQuestions = getAnsweredQuestionsForGroup("G1")
        return AssessmentProgress(total = total, answered = answeredQuestions)
    }

    private fun getTotalSecurityQuestions(): Int {
        val assessmentTemplate = getAssessmentTemplate()
        return getNumberOfQuestionForGroup(assessmentTemplate, "Security")
    }

    private fun getTotalBusinessQuestions(): Int {
        val assessmentTemplate = getAssessmentTemplate()
        return getNumberOfQuestionForGroup(assessmentTemplate, "Business")
    }

    private fun getAssessmentTemplate(): JSONObject {
        val schemaStream = javaClass.getResourceAsStream("/templates/assessment-template.json")
        return JSONObject(JSONTokener(schemaStream))
    }

    private fun getNumberOfQuestionForGroup(assessmentTemplate: JSONObject, targetGroup: String): Int {
        var total = 0
        val groups = assessmentTemplate["groups"] as JSONArray
        val groupsIterator: Iterator<Any> = groups.iterator()
        while (groupsIterator.hasNext()) {
            val group = groupsIterator.next() as JSONObject
            if (targetGroup == group["nav"]) {
                val sections = group["sections"] as JSONArray
                val sectionsIterator: Iterator<Any> = sections.iterator()
                while (sectionsIterator.hasNext()) {
                    val section = sectionsIterator.next() as JSONObject
                    val questions = section["questions"] as JSONArray
                    total += questions.length()
                }
            }
        }
        return total
    }

    private fun getAnsweredQuestionsForGroup(targetGroupId: String): Int {
        var answeredQuestions = 0
        if (answer != null) {
            val answers = answer!!.get("answers") as JSONArray
            val answersIterator: Iterator<Any> = answers.iterator()
            while (answersIterator.hasNext()) {
                val answer = answersIterator.next() as JSONObject
                if (targetGroupId == answer["groupId"]) {
                    answeredQuestions += 1
                }
            }
        }
        return answeredQuestions
    }

}

enum class AssessmentStatus {
    Sent, Not_sent
}
