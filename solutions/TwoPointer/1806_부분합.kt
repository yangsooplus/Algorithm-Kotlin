fun main() {
    val (n, s) = readln().split(' ').map { it.toInt() }
    val arr = readln().split(' ').map { it.toInt() }.toIntArray()
    var left = 0
    var right = 0
    var sum = arr[0]
    var answer = Int.MAX_VALUE

    while (left < n) {
        if (sum < s) {
            if (++right >= n) break
            sum += arr[right]
        } else {
            answer = minOf(answer, right - left + 1)
            sum -= arr[left++]
        }
    }

    if (answer == Int.MAX_VALUE) answer = 0

    println(answer)
}
