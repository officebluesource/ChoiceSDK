package at.bluesource.choicesdk.maps.common

data class Point(
    val x: Double,
    val y: Double
) {

    companion object {
        internal fun Point.toGmsPoint(): com.google.maps.android.geometry.Point {
            return com.google.maps.android.geometry.Point(x, y)
        }

        internal fun com.google.maps.android.geometry.Point.toChoicePoint(): Point {
            return Point(x, y)
        }
    }

}