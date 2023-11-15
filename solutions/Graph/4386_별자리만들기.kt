import kotlin.math.pow
import kotlin.math.sqrt

lateinit var root: IntArray


fun main() {
    val n = readln().toInt()
    root = IntArray(n) { it }
    val arr = Array<Pair<Double, Double>>(n) { Pair(0.0, 0.0) }
    val edges = mutableListOf<Triple<Int, Int, Double>>()

    repeat(n) {
        val (x, y) = readln().split(' ').map { it.toDouble() }
        arr[it] = Pair(x, y)
    }

    for (i in 0 until n) {
        for (j in i + 1 until n) {
            val dist = sqrt((arr[i].first - arr[j].first).pow(2) + (arr[i].second - arr[j].second).pow(2))
            edges.add(Triple(i, j, dist))
        }
    }

    edges.sortBy { it.third }
    var cnt = 0
    var answer = 0.0

    edges.forEach { e ->
        if (cnt >= n - 1) return@forEach
        if (find(e.first) != find(e.second)) {
            cnt++
            answer += e.third
            union(e.first, e.second)
        }
    }

    println(String.format("%.2f", answer))
}

fun find(x: Int): Int {
    return if (root[x] == x) x else {
        root[x] = find(root[x])
        root[x]
    }
}

fun union(x: Int, y: Int) {
    val rootX = find(x)
    val rootY = find(y)

    if (rootX < rootY)
        root[rootX] = rootY
    else
        root[rootY] = rootX
}
