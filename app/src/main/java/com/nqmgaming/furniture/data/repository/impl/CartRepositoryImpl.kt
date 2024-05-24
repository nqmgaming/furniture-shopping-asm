package com.nqmgaming.furniture.data.repository.impl

import com.nqmgaming.furniture.data.network.dto.CartDto
import com.nqmgaming.furniture.data.repository.CartRepository
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Locale.filter
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
) : CartRepository {
    override suspend fun getCartsByUserId(userId: Int): List<String> {
        return try {
            val result = postgrest.from("Users")
                .select(
                    columns = Columns.list(
                        "cart_list"
                    )
                ) {
                    filter {
                        eq("user_id", userId)
                    }
                }.decodeList<CartList>().flatMap {
                    it.cartList
                }
            result
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getCartItem(cartId: String): CartDto {
        return try {
            val result = postgrest.from("Carts")
                .select {
                    filter {
                        eq("cart_id", cartId)
                    }
                }.decodeSingle<CartDto>()
            result
        } catch (e: Exception) {
            e.printStackTrace()
            CartDto(-1, -1, "", -1)
        }
    }

    override suspend fun removeCartItem(cartItem: CartDto, userId: Int, cartsId: List<String>) {
        // Update cart_list in Users then delete cart in Carts
        try {
            postgrest.from("Users")
                .update(
                    {
                        set("cart_list", cartsId)
                    }
                ) {
                    filter {
                        eq("user_id", userId)
                    }
                }
            postgrest.from("Carts")
                .delete {
                    filter {
                        eq("cart_id", cartItem.cartId)
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun decreaseCartItem(cartId: String, quantity: Int) {
        try {
            postgrest.from("Carts")
                .update(
                    {
                        set("quantity", quantity)
                    }
                ) {
                    filter {
                        eq("cart_id", cartId)
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun increaseCartItem(cartId: String, quantity: Int) {
        try {
            postgrest.from("Carts")
                .update(
                    {
                        set("quantity", quantity)
                    }
                ) {
                    filter {
                        eq("cart_id", cartId)
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun removeAllCartId(userId: Int) {
        try {
            postgrest.from("Users")
                .update(
                    {
                        set("cart_list", emptyList<String>())
                    }
                ) {
                    filter {
                        eq("user_id", userId)
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun removeAllFromCart(cartId: String) {
        try {
            postgrest.from("Carts")
                .delete {
                    filter {
                        eq("cart_id", cartId)
                    }
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun addToCart(
        userId: Int,
        productId: Int,
        quantity: Int,
        colorString: String
    ) {
        // Add to Carts then update cart_list in Users
        try {
            val result = postgrest.from("Carts")
                .insert(
                    CartDto(
                        cartId = -1,
                        quantity = quantity,
                        colorString = colorString,
                        productId = productId
                    )
                ).decodeSingle<CartDto>()

            val cartList = getCartsByUserId(userId).toMutableList()
            cartList.add(result.cartId.toString())

            postgrest.from("Users")
                .update(
                    {
                        set("cart_list", cartList)
                    }
                ) {
                    filter {
                        eq("user_id", userId)
                    }
                }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}

@Serializable
data class CartList(@SerialName("cart_list") val cartList: List<String>)