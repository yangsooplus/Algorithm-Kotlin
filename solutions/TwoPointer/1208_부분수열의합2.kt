lateinit var arr: LongArray
fun main() {
    val (n, s) = readln().split(' ').map { it.toInt() }
    arr = readln().split(' ').map { it.toLong() }.toLongArray()

    val listA = mutableListOf<Long>()
    val listB = mutableListOf<Long>()


    subSequence(listA, 0, n / 2, 0)
    subSequence(listB, n / 2, n, 0)

    listA.sort()
    listB.sort()

    var pA = 0
    var pB = listB.size - 1
    var answer = 0L

    while (pA < listA.size && pB >= 0) {
        val sum = listA[pA] + listB[pB]

        if (sum > s) {
            pB--
        } else if (sum < s) {
            pA++
        } else {
            var cntA = 0L
            var cntB = 0L
            val a = listA[pA]
            val b = listB[pB]

            while (pA < listA.size && listA[pA] == a) {
                cntA++
                pA++
            }
            while ( pB >= 0 && listB[pB] == b) {
                cntB++
                pB--
            }
            answer += cntA * cntB
        }
    }

    if (s == 0) answer-- // 공집합 + 공집합
    println(answer)
}

fun subSequence(list: MutableList<Long>, start: Int, end: Int, sum: Long) {
    if (start == end) {
        list.add(sum)
        return
    }

    subSequence(list, start + 1, end, sum)
    subSequence(list, start + 1, end, sum + arr[start])
}
