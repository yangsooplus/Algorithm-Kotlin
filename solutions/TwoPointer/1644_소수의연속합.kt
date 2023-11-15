fun main() {
    val n = readln().toInt()
    if (n == 1) {
        println(0)
        return
    }

    val primes = getPrimeUntil(n)
    var answer = 0
    var left = 0
    var right = 0
    var currentSum = primes[0]

    while (left <= primes.size) {
        if (currentSum < n) {
            if (++right >= primes.size) break
            currentSum += primes[right]
        } else if (currentSum > n) {
            currentSum -= primes[left++]
        } else {
            answer++
            if (++right >= primes.size) break
            currentSum += primes[right]
        }
    }
    println(answer)
}

fun getPrimeUntil(until: Int): IntArray {
    val nums = BooleanArray(until + 1) { true }
    val prime = mutableListOf<Int>()

    nums[0] = false
    nums[1] = false

    for (i in 2..until) {
        if (nums[i]) {
            prime.add(i)
            var n = 2
            while (n * i <= until) {
                nums[n * i] = false
                n++
            }
        }
    }
    return prime.toIntArray()
}
