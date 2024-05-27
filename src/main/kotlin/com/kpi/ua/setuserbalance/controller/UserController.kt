package com.kpi.ua.setuserbalance.controller

import com.kpi.ua.setuserbalance.service.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class UserController(private val userService: UserService) {

    @PostMapping("/set-users-balance")
    fun setUsersBalance(@RequestBody userBalances: Map<Int, Int>): String {
        val count = userService.updateBalances(userBalances)

        return "%s balances have been updated".format(count)
    }
}