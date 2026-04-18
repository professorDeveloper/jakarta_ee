package org.azamov.learnjakarta.leetcode_task

class Medium322 {
    fun coinChange(coins: ArrayList<Int>, amount: Int): Int {
        val dp = IntArray(amount + 1) { Int.MAX_VALUE }
        dp[0] = 0

        for (i in 1..amount) {
            for (coin in coins) {
                if (coin <= i && dp[i - coin] != Int.MAX_VALUE) {
                    dp[i] = minOf(dp[i], dp[i - coin] + 1)
                }
            }
        }
        return if (dp[amount] == Int.MAX_VALUE) -1 else dp[amount]
    }
}

fun main(args: Array<String>) {
    val coins = arrayListOf(2,3,3,3,5,1)
    val amount = 110000
    val medium322 = Medium322()
    println(medium322.coinChange(coins, amount))
}