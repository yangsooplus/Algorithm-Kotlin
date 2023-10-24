import kotlin.math.abs

fun main() {
    val ddr = readln().split(' ').map{ it.toInt() }
    val dp = Array(ddr.size) { Array(5) { IntArray(5) { -1 } } }
    dp[0][0][0] = 0

    for (i in 1 until ddr.size) {
        val note = ddr[i-1]

        for (l in 0..4) {
            for (r in 0..4) {
                if (dp[i-1][l][r] >= 0) {
                    // 왼발
                    if (r != note) { // 두발 같은 곳에 위치 불가
                        val power = calculatePower(l, note)
                        if (dp[i][note][r] < 0) {
                            dp[i][note][r] = dp[i-1][l][r] + power
                        } else {
                            dp[i][note][r] = minOf(dp[i][note][r], dp[i-1][l][r] + power)
                        }
                    }

                    // 오른발
                    if (l != note) {
                        val power = calculatePower(r, note)
                        if (dp[i][l][note] < 0) {
                            dp[i][l][note] = dp[i-1][l][r] + power
                        } else {
                            dp[i][l][note] = minOf(dp[i][l][note], dp[i-1][l][r] + power)

                        }
                    }
                }
            }
        }
    }

    var answer = Int.MAX_VALUE
    for (l in 0..4) {
        for (r in 0..4) {
            if (dp[ddr.size - 1][l][r] >= 0)
                answer = minOf(answer, dp[ddr.size - 1][l][r])
        }
    }
    println(answer)
}

fun calculatePower(before: Int, after: Int): Int {
    if (after == 0) return -1
    if (before == 0) return 2
    
    return when (abs(before - after)) {
        0 -> 1
        2 -> 4
        else -> 3
    }
}
