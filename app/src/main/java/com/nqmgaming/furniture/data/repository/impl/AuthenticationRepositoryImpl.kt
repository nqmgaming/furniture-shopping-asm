package com.nqmgaming.furniture.data.repository.impl

import com.nqmgaming.furniture.data.network.dto.CartDto
import com.nqmgaming.furniture.data.network.dto.UserDto
import com.nqmgaming.furniture.data.repository.AuthenticationRepository
import io.github.jan.supabase.gotrue.Auth
import javax.inject.Inject
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.util.UUID

class AuthenticationRepositoryImpl
@Inject constructor(
    private val auth: Auth,
    private val postgrest: Postgrest
) : AuthenticationRepository {
    override suspend fun signIn(email: String, password: String): Boolean {
        return try {
            auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getInformationByEmail(email: String): UserDto {
        return try {
            val result = postgrest.from("Users")
                .select {
                    filter {
                        eq("email", email)
                    }
                }.decodeSingle<UserDto>()
            result
        } catch (e: Exception) {
            UserDto(
                userId = UUID.randomUUID().variant(),
                name = "",
                email = ""
            )
        }
    }

    override suspend fun signUp(email: String, password: String, name: String): Boolean {
        return try {
            val signup = auth.signUpWith(Email) {
                this.email = email
                this.password = password
                data = buildJsonObject {
                    put("name", name)
                }
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun insertUser(email: String, name: String) {
        return try {
            val response = postgrest["Users"].insert(
                buildJsonObject {
                    put("email", email)
                    put("name", name)
                }
            )

        } catch (e: java.lang.Exception) {
            throw e
        }
    }
}