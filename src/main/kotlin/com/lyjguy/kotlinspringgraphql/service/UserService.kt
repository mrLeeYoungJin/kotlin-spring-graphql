package com.lyjguy.kotlinspringgraphql.service

import com.lyjguy.kotlinspringgraphql.model.entity.User
import com.lyjguy.kotlinspringgraphql.model.enum.UserType
import com.lyjguy.kotlinspringgraphql.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun findById(id: Long): User? {
        return userRepository.findById(id).orElse(null)
    }

    @Transactional
    fun save() {
        val user = User(
            name = "test",
            email = "test@test.com",
            password = "1234",
            userType = UserType.USER,
        )

        userRepository.save(user)
    }
}