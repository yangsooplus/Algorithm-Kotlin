lateinit var union: IntArray
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    union = IntArray(n) { it }

    repeat(m) {
        val (u, v) = readln().split(" ").map { it.toInt() }
        if (findRoot(u) == findRoot(v)) {
            println(it + 1)
            return
        } else {
            unionRoot(u, v)
        }
    }
    println(0)
}

fun findRoot(x: Int): Int {
    return if (x == union[x]) x else {
        union[x] = findRoot(union[x])
        union[x]
    }
}

fun unionRoot(x: Int, y: Int) {
    val rootX = findRoot(x)
    val rootY = findRoot(y)

    if (rootX < rootY) {
        union[rootY] = rootX
    } else {
        union[rootX] = rootY
    }
}
