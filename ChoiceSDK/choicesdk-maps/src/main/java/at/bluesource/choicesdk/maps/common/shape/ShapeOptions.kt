package at.bluesource.choicesdk.maps.common.shape

/**
 * Base class for shape options
 */
@Suppress("unused")
abstract class ShapeOptions {
    var clickable = false
    var strokeColor: Int =
        0xff000000.toInt() // black, also toInt workaround for compiler issue: https://youtrack.jetbrains.com/issue/KT-4749
    var strokeWidthInPx: Float = 10f
    var visible = true
    var zIndex: Float = 0f

    open fun clickable(clickable: Boolean): ShapeOptions {
        this.clickable = clickable
        return this
    }

    open fun strokeColor(color: Int): ShapeOptions {
        strokeColor = color
        return this
    }

    open fun strokeWidth(widthInPx: Float): ShapeOptions {
        strokeWidthInPx = widthInPx
        return this
    }

    open fun visible(visible: Boolean): ShapeOptions {
        this.visible = visible
        return this
    }

    open fun zIndex(zIndex: Float): ShapeOptions {
        this.zIndex = zIndex
        return this
    }
}