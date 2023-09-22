lateinit var root: IntArray

fun main() {
    val (n, m) = readln().split(' ').map { it.toInt() }
    val edges = mutableListOf<Triple<Int, Int, Int>>()
    var answer = 0
    var cnt = 0

    if (n == 2) {
        println(0)
        return
    }
    
    root = IntArray(n) { it }

    repeat(m) {
        val (a, b, w) = readln().split(' ').map { it.toInt() }
        edges.add(Triple(a - 1, b - 1, w))
    }

    edges.sortBy { it.third }

    edges.forEach { edge ->
        if (findRoot(edge.first) != findRoot(edge.second)) {
            unionRoot(edge.first, edge.second)
            answer += edge.third
            cnt++
        }
        if (cnt == n - 2) {
            println(answer)
            return
        }
    }
}

fun findRoot(num: Int): Int {
    return if (num == root[num]) {
        num
    } else {
        val parent = findRoot(root[num])
        root[num] = parent
        parent
    }
}

fun unionRoot(a: Int, b: Int) {
    val rootA = findRoot(a)
    val rootB = findRoot(b)

    if (rootA != rootB) {
        root[rootB] = rootA
    }
}
