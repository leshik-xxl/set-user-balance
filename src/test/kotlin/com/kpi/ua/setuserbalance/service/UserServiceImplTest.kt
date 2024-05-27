package com.kpi.ua.setuserbalance.service

import com.kpi.ua.setuserbalance.model.UserBalance
import com.kpi.ua.setuserbalance.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceImplTest {

    private val userRepository: UserRepository = mockk()
    private val userService: UserService = UserServiceImpl(userRepository)

    @Test
    fun shouldUpdateAllUsers() {
        val userBalances = mapOf(1 to 100, 2 to 200, 3 to 300)
        val users = getUsersList()

        every { userRepository.findAllById(userBalances.keys) } returns users
        every { userRepository.saveAll(any<List<UserBalance>>()) } returns users

        val updatedCount = userService.updateBalances(userBalances)

        assertEquals(3, updatedCount)
        assertEquals(100, users[0].balance)
        assertEquals(200, users[1].balance)
        assertEquals(300, users[2].balance)

        verify(exactly = 1) { userRepository.findAllById(userBalances.keys) }
        verify(exactly = 1) { userRepository.saveAll(users) }
    }

    @Test
    fun shouldNotUpdateUsersWhenNotExistsImMap() {
        val userBalances = mapOf(1 to 100, 2 to 200)

        val users = getUsersList()

        every { userRepository.findAllById(userBalances.keys) } returns users
        every { userRepository.saveAll(any<List<UserBalance>>()) } returns users

        val updatedCount = userService.updateBalances(userBalances)

        assertEquals(2, updatedCount)
        assertEquals(100, users[0].balance)
        assertEquals(200, users[1].balance)
        assertEquals(0, users[2].balance)

        verify(exactly = 1) { userRepository.findAllById(userBalances.keys) }
        verify(exactly = 1) { userRepository.saveAll(users) }
    }

    @Test
    fun shouldReturnZeroWhenNoUsersToUpdate() {
        val userBalances = mapOf<Int, Int>()

        every { userRepository.findAllById(userBalances.keys) } returns emptyList()
        every { userRepository.saveAll(any<List<UserBalance>>()) } returns emptyList()

        val updatedCount = userService.updateBalances(userBalances)

        assertEquals(0, updatedCount)

        verify(exactly = 1) { userRepository.findAllById(userBalances.keys) }
        verify(exactly = 0) { userRepository.saveAll(any<List<UserBalance>>()) }
    }

    fun getUsersList(): List<UserBalance> {
        val user1 = UserBalance(1, "User1", 0)
        val user2 = UserBalance(2, "User2", 0)
        val user3 = UserBalance(3, "User3", 0)
        return listOf(user1, user2, user3)
    }
}