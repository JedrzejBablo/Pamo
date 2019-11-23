package com.example.bmi_kotlin

class Questions {
    var mQuestions = arrayOf(
        "Prawidłowe ciśnienie u człowieka wynosi?",
        "Jakie powinno być prawidłowe tętno człowieka?",
        "Ile wynosi prawidłowy poziom cholesterolu?",
        "Który hormon występuje w trzustce?",
        "Ile wynosi prawidłowa tolerancja glukozy w teście obciążeniowym?",
        "Dializa jest wykonywana na:"
    )
    private val mChoices =
        arrayOf(
            arrayOf(
                "120/80", "140/90", "80/120", "90/140"
            ),
            arrayOf(
                "90-120 skurczów/min", "60-80 skurczów/min", "40-60 skurczów/min", "120-150 skurczów/min"
            ),
            arrayOf(
                "do 120 mg/dl", "do 210 mg/dl", "do 280 mg/dl", "do 200 mg/dl"
            ),
            arrayOf(
                "adrenalina", "insulina", "oksytocyna", "testosteron"
            ),
            arrayOf(
                "od 140 do 200 mg/dL", "od 200 do 300 mg/dL", "poniżej 140 mg/dL", "powyżej 140 mg/dL"
            ),
            arrayOf(
                "nerkach", "trzustce", "sercu", "wątrobie"
            )
        )
    private val mCorrectAnswer = arrayOf(
        "120/80", "60-80 skurczów/min", "do 200 mg/dl", "insulina", "poniżej 140 mg/dL", "nerkach"
    )
    fun getQuestion(a: Int): String? {
        return mQuestions[a]
    }
    fun getChoice1(a: Int): String? {
        return mChoices[a][0]
    }

    fun getChoice2(a: Int): String? {
        return mChoices[a][1]
    }

    fun getChoice3(a: Int): String? {
        return mChoices[a][2]
    }

    fun getChoice4(a: Int): String? {
        return mChoices[a][3]
    }

    fun getCorrectAnwer(a: Int): String? {
        return mCorrectAnswer[a]
    }
}