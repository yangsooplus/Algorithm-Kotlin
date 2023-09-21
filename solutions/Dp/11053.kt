fun main() {
    val n = readln().toInt()
    val list = readln().split(' ').map { it.toInt() }
    val dp = IntArray(n) { 1 }
    var answer = 1

    for (i in 1 until n) {
       for (j in i - 1 downTo 0) {
           if (list[i] > list[j] && dp[j] >= dp[i]) {
               dp[i] = dp[j] + 1
           }
       }
        answer = maxOf(answer, dp[i])
    }

    println(answer)
}
