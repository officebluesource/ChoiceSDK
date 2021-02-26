package at.bluesource.choicesdk.maps.common.shape

/**
 * Base interface for shapes
 */
interface Shape {
    var clickable: Boolean
    var strokeColor: Int
    var strokeWidth: Float
    var visible: Boolean
    var zIndex: Float
    var tag: Any?
}