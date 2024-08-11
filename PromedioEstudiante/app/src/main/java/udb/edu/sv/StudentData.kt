package udb.edu.sv

object StudentData {
    var studentName: String = ""
    val notes: MutableList<Double> = mutableListOf()

    fun reset() {
        studentName = ""
        notes.clear()
    }
}
