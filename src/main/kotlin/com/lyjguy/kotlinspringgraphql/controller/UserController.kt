package com.lyjguy.kotlinspringgraphql.controller

import com.lyjguy.kotlinspringgraphql.model.entity.User
import com.lyjguy.kotlinspringgraphql.service.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller


@Controller
class UserController(
    private val userService: UserService,
) {

    @QueryMapping
    fun getUser(@Argument id: Long): User? {
        return userService.findById(id)
    }
}