class Solution {
    fun solution(survey: Array<String>, choices: IntArray): String {
        var answer: String = ""
        val score: HashMap<Char, Int> = hashMapOf(
            'R' to 0, 'T' to 0, 'C' to 0, 'F' to 0,
            'J' to 0, 'M' to 0, 'A' to 0, 'N' to 0
        )
        
        for (i in 0 until survey.size) {
            when (choices[i]) {
                1 -> score[survey[i][0]] = score[survey[i][0]]!!.plus(3)
                2 -> score[survey[i][0]] = score[survey[i][0]]!!.plus(2)
                3 -> score[survey[i][0]] = score[survey[i][0]]!!.plus(1)
                5 -> score[survey[i][1]] = score[survey[i][1]]!!.plus(1)
                6 -> score[survey[i][1]] = score[survey[i][1]]!!.plus(2)
                7 -> score[survey[i][1]] = score[survey[i][1]]!!.plus(3)
                else -> {}
            }    
        }
        
        answer += if (score['R']!! >= score['T']!!) 'R' else 'T'
        answer += if (score['C']!! >= score['F']!!) 'C' else 'F'
        answer += if (score['J']!! >= score['M']!!) 'J' else 'M'
        answer += if (score['A']!! >= score['N']!!) 'A' else 'N'
        
        return answer
    }
}
