var n = 0
var c = 0
val list = mutableListOf<Int>()


fun main() {
    val input = readln().split(' ').map { it.toInt() }
    n = input[0]
    c = input[1]
    repeat(n) {
        list.add(readln().toInt())
    }
    list.sort()

    var left = 1
    var right = list[n - 1] - list[0] + 1
    
    // upper bound
    while (left < right) {
        val mid = (left + right) / 2

        if (canInstall(mid) >= c) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    println(right - 1)
}

// 몇 개 설치 가능한지
fun canInstall(dist: Int): Int {
    var prevHouseIndex = 0
    var count = 1
    for (i in 1 until n) {
        if (list[i] - list[prevHouseIndex] >= dist) {
            count++
            prevHouseIndex = i
        }
    }
    return count
}
