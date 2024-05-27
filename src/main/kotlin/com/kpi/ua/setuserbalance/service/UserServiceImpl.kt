package com.kpi.ua.setuserbalance.service

import com.kpi.ua.setuserbalance.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    @Transactional
    override fun updateBalances(userBalances: Map<Int, Int>): Long {
        var updateCount = 0L;
        userBalances.keys.forEach { id ->
            userBalances[id]?.let {
                updateCount += userRepository.updateUserBalance(it, id)
            }
        }
        return updateCount;
    }
}