package com.kpi.ua.setuserbalance.repository

import com.kpi.ua.setuserbalance.model.UserBalance
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<UserBalance, Int> {

    @Modifying
    @Query("update UserBalance u set u.balance = ?1 where u.id = ?2 and u.balance != ?1")
    fun updateUserBalance(balance: Int, id: Int): Int
}
