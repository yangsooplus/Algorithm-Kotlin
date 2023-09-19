import java.util.PriorityQueue

fun main() {
    val (n, k) = readln().split(' ').map { it.toInt() }
    val jewelQueue = PriorityQueue<Pair<Int, Int>> { a, b -> b.second - a.second } // 가치가 높은 순
    val sortedJewelList = mutableListOf<Pair<Int, Int>>()
    val bagQueue = PriorityQueue<Int>()

    repeat(n) {
        val (w, v) = readln().split(' ').map { it.toInt() }
        sortedJewelList.add(Pair(w, v))
    }

    sortedJewelList.sortBy { it.first } // 무게 순으로 정렬

    repeat(k) {
        bagQueue.add(readln().toInt())
    }

    var pointer = 0 // sortedJewelList 중 jewelQueue에 들어가려고 대기하는 보석을 가리킴.
    var answer = 0L
    while (bagQueue.isNotEmpty()) {
        val curBag = bagQueue.poll()

        while (pointer < sortedJewelList.size && sortedJewelList[pointer].first <= curBag) {
            jewelQueue.add(sortedJewelList[pointer++])
        }

        answer += jewelQueue.poll()?.second ?: 0 // 주의) queue가 비었을때 poll()은 null 반환. IDE에서 안 잡힘 
    }

    println(answer)
}
