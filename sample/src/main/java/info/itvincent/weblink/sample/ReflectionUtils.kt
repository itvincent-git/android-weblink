package info.itvincent.weblink.sample

/**
 *
 * @author zhongyongsheng
 */
object ReflectionUtils {

    fun invokeCompanionMethod(c: Class<*>, m: String): Any? {
        val findedMethod = c.declaredClasses.filter { it.simpleName == "Companion" }
                .getOrNull(0)
                ?.getDeclaredMethod(m)
        val companionField: Any?
        try {
            companionField = c.getField("Companion").get(null)
        } catch (e: Exception) {
            return null
        }
        return findedMethod?.invoke(companionField)
    }
}