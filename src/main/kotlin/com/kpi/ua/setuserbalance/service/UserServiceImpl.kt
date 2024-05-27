package com.kpi.ua.setuserbalance.service

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Value
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val jdbcTemplate: JdbcTemplate,
                      @Value("\${balance.datasource.batch-size}")
                      private val batchSize: Int) : UserService {

    val updateQuery = "UPDATE user_balance SET balance = ? WHERE id = ? AND balance != ?"

    @Transactional
    override fun updateBalances(userBalances: Map<Int, Int>): Long {
        val batchArgs = userBalances.map { arrayOf(it.value, it.key, it.value) }

        return batchArgs.chunked(batchSize).sumOf { batch ->
            jdbcTemplate.batchUpdate(updateQuery, batch).sum()
        }.toLong()
    }
}