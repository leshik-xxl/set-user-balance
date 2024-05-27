package com.kpi.ua.setuserbalance.service

interface UserService {
    fun updateBalances(userBalances: Map<Int, Int>) : Long
}