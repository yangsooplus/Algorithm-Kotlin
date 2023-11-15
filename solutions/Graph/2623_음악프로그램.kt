fun main() {
    val (n, m) = readln().split(' ').map { it.toInt() }
    val graph = Array(n+1) { BooleanArray(n+1) { false } }
    val inDegree = IntArray(n+1)

    repeat(m) {
        val input = readln().split(' ').map { it.toInt() }
        for (i in 1 until input.size - 1) {
            if (!graph[input[i]][input[i+1]]) {
                graph[input[i]][input[i+1]] = true
                inDegree[input[i+1]]++
            }

            if (graph[input[i+1]][input[i]] ) {
                println(0)
                return
            }
        }
    }

    val queue = ArrayDeque<Int>()
    val answer = mutableListOf<Int>()

    for (i in 1..n) {
        if (inDegree[i] == 0) {
            queue.addLast(i)
        }
    }

    while (queue.isNotEmpty()) {
        val u = queue.removeFirst()
        answer.add(u)

        for (v in 1..n) {
            if (graph[u][v]) {
                if (--inDegree[v] == 0) {
                    queue.addLast(v)
                }
            }
        }
    }

    if (inDegree.count { it > 0 } > 0) {
        println(0)
    } else {
        println(answer.joinToString("\n"))
    }

}
