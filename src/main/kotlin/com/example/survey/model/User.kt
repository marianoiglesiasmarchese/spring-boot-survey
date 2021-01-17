package com.example.survey.model

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.Email

@Entity
class User(
    @field: [Id GeneratedValue(strategy = GenerationType.IDENTITY)]
    var id: Long,
    val name: String,
    val lastName: String,
    @field: Enumerated(EnumType.STRING)
    val userType: UserType,
    @field: [Email Column(unique = true)]
    val email: String,
    val pass: String,
    @field: OneToMany(cascade = [CascadeType.ALL])
    val assessments: MutableSet<Assessment> = mutableSetOf()
)

enum class UserType {
    BROKER, BUYER
}
