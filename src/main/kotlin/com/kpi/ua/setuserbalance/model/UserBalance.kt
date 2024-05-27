package com.kpi.ua.setuserbalance.model

import jakarta.persistence.*

@Entity
@Table(name = "user_balance")
data class UserBalance(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        val id: Int,
        val name: String,
        var balance: Int
)