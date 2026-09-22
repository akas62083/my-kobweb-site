package com.example.site

import kotlinx.serialization.Serializable

data class UiState(
    val pageState: Pages = Pages.None,
    val isLoading: Boolean = false,
    val sendText: Boolean = false,
    val myData: MyData = MyData(2, 2, 2, 2, 2, 2, 2) // 仮に。将来的には repo から取るかな?
)

sealed interface Pages {
    data class English(
        val status: EnglishStatus
    ): Pages
    data object None: Pages
}

sealed interface EnglishStatus {
    data object Waiting: EnglishStatus
    data class Solving(
        val id: Int,
        val problems: Problems,
        val input: String,
        val score: EnglishScore
    ): EnglishStatus
}
@Serializable
data class Problems(
    val exercises: List<Problem>
)
@Serializable
data class Problem(
    val id: Int,
    val sentence_ja: String,
    val background_ja: String
)
sealed interface EnglishScore {
    data object Waiting: EnglishScore
    @Serializable
    data class Score( // こいつの型は EnglishScore.Score になる。
        val model_answer: String,
        val english_correctness: Evaluation,
        val native_naturalness: Evaluation,
        val native_comment_ja: String
    ): EnglishScore
}
@Serializable
data class Evaluation (
    val score: Int,
    val comment: String
)

//----------request A----------
@Serializable
data class MyData(
    val vo: Int,
    val ex: Int,
    val gr: Int,
    val mo: Int,
    val pr: Int,
    val ss: Int,
    val oa: Int
)
//-----------------------------
//----------request B----------
@Serializable
data class MyAnswer(
    val pj: String,
    val bj: String,
    val ae: String
)
//-----------------------------
