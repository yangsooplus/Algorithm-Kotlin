fun main() {
    val (n, d, k, c) = readln().split(' ').map { it.toInt() }
    val sushi = IntArray(n)
    val window = IntArray(d + 1)

    repeat(n) {
        sushi[it] = readln().toInt()
    }

    val queue = ArrayDeque<Int>()
    var answer = 0
    var unique = 0

    repeat(k) {
        queue.addLast(sushi[it])
        if (window[sushi[it]] == 0) unique++
        window[sushi[it]]++
    }


    for (i in k until sushi.size + k) {
        val p = if (i > sushi.size - 1) i - sushi.size else i
        val pop = queue.removeFirst()
        window[pop]--
        if (window[pop] == 0) unique--

        if (window[sushi[p]] == 0) unique++
        queue.addLast(sushi[p])
        window[sushi[p]]++

        answer = if (window[c] == 0) {
            maxOf(answer, unique + 1)
        } else {
            maxOf(answer, unique)
        }

        if (answer == k + 1) {
            println(answer)
            return
        }
    }

    println(answer)
}
