
fun main() {
    val n = readln().toInt()
    val home = Array(n) { IntArray(n) }

    repeat(n) {
        home[it] = readln().split(' ').map { it.toInt() }.toIntArray()
    }

    if (n == 2) {
        val r = home[0][0] + minOf(home[1][1], home[1][2])
        val g = home[0][1] + minOf(home[1][0], home[1][2])
        val b = home[0][2] + minOf(home[1][0], home[1][1])

        println(minOf(r, g, b))
        return
    }

    val dp =  Array(3) { Array(3) { IntArray(n + 1) { 1001 } } }  // 시작RGB / RGB / 값

    dp[0][0][0] = home[0][0]
    dp[1][1][0] = home[0][1]
    dp[2][2][0] = home[0][2]


    for (c in 0..2) {
        for (i in 1 until n - 1) {
            dp[c][0][i] = minOf(dp[c][1][i - 1], dp[c][2][i - 1]) + home[i][0]
            dp[c][1][i] = minOf(dp[c][0][i - 1], dp[c][2][i - 1]) + home[i][1]
            dp[c][2][i] = minOf(dp[c][0][i - 1], dp[c][1][i - 1]) + home[i][2]
        }
    }


    val r = minOf(
        dp[0][0][n - 2] + minOf(home[n - 1][2], home[n - 1][1]),
        dp[0][1][n - 2] + home[n - 1][2],
        dp[0][2][n - 2] + home[n - 1][1],
    )

    val g = minOf(
        dp[1][0][n - 2] + home[n - 1][2],
        dp[1][1][n - 2] + minOf(home[n - 1][0] , home[n - 1][2]) ,
        dp[1][2][n - 2] + home[n - 1][0],
    )

    val b = minOf(
        dp[2][0][n - 2] + home[n - 1][1],
        dp[2][1][n - 2] + home[n - 1][0],
        dp[2][2][n - 2] +  minOf(home[n - 1][1] , home[n - 1][0]) ,
    )

    println(minOf(r, g, b))

}


/*
* RGB거리에서 차원을 1개 더 늘려서 해결.
* */
