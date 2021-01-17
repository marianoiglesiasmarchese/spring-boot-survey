package com.example.survey.controller

import com.example.survey.config.Logging
import com.example.survey.config.logger
import com.example.survey.model.User
import com.example.survey.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) : Logging{

    @PostMapping
    fun login(@RequestBody user: UserRequest): ResponseEntity<User> {
        return ResponseEntity.ok(userService.login(user.email!!))
    }

    @PostMapping("/assessment")
    fun addAssessment(@RequestBody user: UserRequest): ResponseEntity<Any> {
        logger().info("Create assessment for an user")
        userService.addAssessment(user.id!!)
        return ResponseEntity.accepted().build()
    }
}

class UserRequest(
    val id: Long? = null,
    val email: String? = null
)