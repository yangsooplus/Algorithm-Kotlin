lateinit var visited: Array<BooleanArray>
lateinit var area: Array<IntArray>

fun main() {
    val (n, m) = readln().split(' ').map { it.toInt() }
    var answer = 0
    area = Array(n) { IntArray(m) }
    visited = Array(n) { BooleanArray(m) { false } }


    repeat(n) { r ->
        val row = readln()

        for (c in row.indices) {
            if (row[c] == 'W') {
                area[r][c] = -1
            }
        }
    }

    for (r in 0 until n) {
        for (c in 0 until m) {
            if (area[r][c] == 0 && !visited[r][c]) {
                val maxPos = bfs(copyArea(n, m), r, c)
                val (_, _, result) = bfs(copyArea(n, m), maxPos.first, maxPos.second)
                answer = maxOf(answer, result)
            }
        }
    }

    println(answer)
}

fun bfs(array: Array<IntArray>, startR: Int, startC: Int): Triple<Int, Int, Int> {
    array[startR][startC] = 1
    val queue = ArrayDeque<Pair<Int, Int>>()
    queue.addLast(Pair(startR, startC))
    var count = 0
    var position = Pair(startR, startC)

    while (queue.isNotEmpty()) {
        val v = queue.removeFirst()

        if (count < array[v.first][v.second]) {
            count = array[v.first][v.second]
            position = v
        }

        if (v.first > 0 && array[v.first - 1][v.second] == 0) {
            array[v.first - 1][v.second] = array[v.first][v.second] + 1
            queue.addLast(Pair(v.first - 1, v.second))
        }
        if (v.first < array.size - 1 && array[v.first + 1][v.second] == 0) {
            array[v.first + 1][v.second] = array[v.first][v.second] + 1
            queue.addLast(Pair(v.first + 1, v.second))
        }
        if (v.second > 0 && array[v.first][v.second - 1] == 0) {
            array[v.first][v.second - 1] = array[v.first][v.second] + 1
            queue.addLast(Pair(v.first, v.second - 1))
        }
        if (v.second < array[0].size - 1 && array[v.first][v.second + 1] == 0) {
            array[v.first][v.second + 1] = array[v.first][v.second] + 1
            queue.addLast(Pair(v.first, v.second + 1))
        }
    }

    return Triple(position.first, position.second, count - 1)
}

fun copyArea(n: Int, m: Int): Array<IntArray> {
    val copy = Array(n) { IntArray(m) }

    for (i in 0 until n) {
        for (j in 0 until m) {
            copy[i][j] = area[i][j]
        }
    }

    return copy
}
