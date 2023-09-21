val vector = mutableListOf<Int>()

fun main() {
    val n = readln().toInt()
    val data = readln().split(' ').map { it.toInt() }.toIntArray()

    vector.add(data[0])
    for (i in 1 until n) {
        if (vector.last() < data[i]) {
            vector.add(data[i])
        } else if (vector.last() > data[i]) {
            val idx = lowerBound(0, vector.size - 1, data[i])
            vector[idx] = data[i]
        }
    }
    println(vector.size)
}

fun lowerBound(left: Int, right: Int, target: Int): Int {
    if (left >= right) return right

    val mid = (left + right)/2

    return if (vector[mid] >= target) {
        lowerBound(left, mid, target)
    } else {
        lowerBound(mid + 1, right, target)
    }
}
