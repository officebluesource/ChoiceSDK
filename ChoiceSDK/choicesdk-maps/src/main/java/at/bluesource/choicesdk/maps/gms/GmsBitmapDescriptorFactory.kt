package at.bluesource.choicesdk.maps.gms

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import at.bluesource.choicesdk.maps.common.BitmapDescriptor
import at.bluesource.choicesdk.maps.common.BitmapDescriptorFactory

/**
 * Wrapper class for gms version of BitmapDescriptorFactory
 *
 * @see com.google.android.gms.maps.model.BitmapDescriptorFactory
 */
internal class GmsBitmapDescriptorFactory : BitmapDescriptorFactory {

    override fun defaultMarker(hue: Float): BitmapDescriptor {
        return BitmapDescriptor.GmsBitmapDescriptor(
            com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker(
                hue
            )
        )
    }

    override fun defaultMarker(): BitmapDescriptor {
        return BitmapDescriptor.GmsBitmapDescriptor(com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker())
    }

    override fun fromAsset(assetName: String): BitmapDescriptor {
        return BitmapDescriptor.GmsBitmapDescriptor(
            com.google.android.gms.maps.model.BitmapDescriptorFactory.fromAsset(
                assetName
            )
        )
    }

    override fun fromBitmap(bitmap: Bitmap): BitmapDescriptor {
        return BitmapDescriptor.GmsBitmapDescriptor(
            com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap(
                bitmap
            )
        )
    }

    override fun fromFile(fileName: String): BitmapDescriptor {
        return BitmapDescriptor.GmsBitmapDescriptor(
            com.google.android.gms.maps.model.BitmapDescriptorFactory.fromFile(
                fileName
            )
        )
    }

    override fun fromPath(absolutePath: String): BitmapDescriptor {
        return BitmapDescriptor.GmsBitmapDescriptor(
            com.google.android.gms.maps.model.BitmapDescriptorFactory.fromPath(
                absolutePath
            )
        )
    }

    override fun fromResource(resourceId: Int): BitmapDescriptor {
        return BitmapDescriptor.GmsBitmapDescriptor(
            com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(
                resourceId
            )
        )
    }

    override fun fromVector(context: Context, vectorResId: Int): BitmapDescriptor? {
        return ContextCompat.getDrawable(context, vectorResId)?.run {
            setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            val bitmap =
                Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888)
            draw(Canvas(bitmap))
            BitmapDescriptor.GmsBitmapDescriptor(
                com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap(
                    bitmap
                )
            )
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: GmsBitmapDescriptorFactory? = null

        /**
         * Returns a new instance
         * Thread safe.
         * throws [UnsupportedOperationException] if no underlying api has been found
         */
        @Throws(UnsupportedOperationException::class)
        fun getInstance(): GmsBitmapDescriptorFactory {
            return INSTANCE
                ?: synchronized(this) {
                    GmsBitmapDescriptorFactory().also {
                        INSTANCE = it
                    }
                }
        }
    }
}