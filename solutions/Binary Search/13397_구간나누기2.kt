var n = 0
var m = 0
lateinit var list: IntArray

fun main() {
    val input = readln().split(' ').map { it.toInt() }
    n = input[0]
    m = input[1]

    list = readln().split(' ').map { it.toInt() }.toIntArray()

    var left = 0
    var right = list.max() - list.min()

    while (left < right) {
        val mid = (left + right) / 2

        if (check(mid) > m) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    println(right)
}

// 구간 개수 구하기
fun check(k: Int): Int {
    var count = 1
    var max = list[0]
    var min = list[0]

    for (i in 1 until n) {
        if (list[i] > max) {
            if (list[i] - min <= k) { // 구간에 포함
                max = list[i]
            } else { // 다음 구간으로 넘김
                count++
                max = list[i]
                min = list[i]
            }
        } else if (list[i] < min) {
            if (max - list[i] <= k) {
                min = list[i]
            } else {
                count++
                max = list[i]
                min = list[i]
            }
        }
    }

    return count
}
