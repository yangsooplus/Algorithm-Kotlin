fun main() {
    val n = readln().toInt()
    val data = Array(n) { Array(n) { Matrix() } }
    repeat(n) {
        val input = readln().split(' ').map { it.toInt() }
        data[it][it] = Matrix(input[0], input[1], 0)
    }

    for (len in 1 until n) {
        for (i in 0 until n - len) {
            for (k in 0 until len) {
                val result = data[i][i + k].sum + data[i + k + 1][i + len].sum +
                        data[i][i + k].a * data[i][i + k].b * data[i + k + 1][i + len].b
                if (data[i][i + len].sum > result) {
                    data[i][i + len] = Matrix(
                        data[i][i + k].a,
                        data[i + k + 1][i + len].b,
                        result
                    )
                }
            }
        }
    }
    
    println(data[0][n-1].sum)
}

data class Matrix(val a: Int = 0, val b: Int = 0, val sum: Int = Int.MAX_VALUE)
