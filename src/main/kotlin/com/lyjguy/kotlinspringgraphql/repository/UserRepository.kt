package com.lyjguy.kotlinspringgraphql.repository

import com.lyjguy.kotlinspringgraphql.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
}