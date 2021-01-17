package com.example.survey.utils

import com.example.survey.config.Logging
import com.example.survey.config.logger
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject
import java.io.IOException
import javax.persistence.AttributeConverter

class JSONObjectConverter : AttributeConverter<JSONObject?, String?>, Logging {

    override fun convertToDatabaseColumn(attribute: JSONObject?): String? {
        val objectMapper = ObjectMapper()
        try {
            if (attribute != null) return objectMapper.writeValueAsString(attribute.toMap())
        } catch (e: JsonProcessingException) {
            logger().error("JSON writing error", e)
        }
        return null
    }

    override fun convertToEntityAttribute(attributeJSON: String?): JSONObject? {
        val objectMapper = ObjectMapper()
        try {
            if (attributeJSON != null) return JSONObject(
                objectMapper.readValue(
                    attributeJSON,
                    MutableMap::class.java
                )
            )
        } catch (e: IOException) {
            logger().error("JSON reading error", e)
        }
        return null
    }
}