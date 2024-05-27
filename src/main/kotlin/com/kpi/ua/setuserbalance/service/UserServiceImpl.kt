package com.kpi.ua.setuserbalance.service

import com.kpi.ua.setuserbalance.repository.UserRepository
import jakarta.transaction.Transactional
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    @Transactional
    override fun updateBalances(userBalances: Map<Int, Int>): Long {

        return runBlocking {
            userBalances.keys.map { id ->
                async { userBalances[id]?.let { userRepository.updateUserBalance(it, id) } ?: 0L }
            }.awaitAll().sumOf { it.toLong() }
        }
    }
}