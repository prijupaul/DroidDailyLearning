package au.com.learning.droid.dailylearning.preconditions

class Preconditions {

    private val list = listOf<String>("One","Two","Three","Four","Five")

    fun isArrayGreaterthan() {

        require(list.size >= 5) {
            "Array should have atleast 5 elements"
        }

        lateinit var list1 : List<String>

        requireNotNull(list1) {
            "list should not be null"
        }
    }

    fun isCheckNull() {

        val isnull = arrayOf(1,2,4,5,6)
        checkNotNull(isnull)
    }

}