fun main() {
    val n = readln().toInt()

    if (n <= 6) {
        println(n)
        return
    }

    val dp = LongArray(n + 1)

    repeat(7) {
        dp[it] = it.toLong()
    }

    for (i in 7..n) {
        var maxi = dp[i - 1] + 1
        var j = 3
        while (i - j >= 0) {
            maxi = maxOf(maxi, dp[i - j] * (j - 1))
            j++
        }
        dp[i] = maxi
    }
    println(dp[n])
}

/*
* 정수형 overflow 
*/
