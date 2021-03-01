package at.bluesource.choicesdk.maps.hms

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import at.bluesource.choicesdk.core.ChoiceSdk
import at.bluesource.choicesdk.maps.common.BitmapDescriptor
import at.bluesource.choicesdk.maps.common.BitmapDescriptorFactory

/**
 * Wrapper class for hms version of BitmapDescriptorFactory
 *
 * @see com.huawei.hms.maps.model.BitmapDescriptorFactory
 */
internal class HmsBitmapDescriptorFactory : BitmapDescriptorFactory {

    override fun defaultMarker(hue: Float): BitmapDescriptor {
        return BitmapDescriptor.HmsBitmapDescriptor(
            com.huawei.hms.maps.model.BitmapDescriptorFactory.defaultMarker(
                hue
            )
        )
    }

    override fun defaultMarker(): BitmapDescriptor {
        return BitmapDescriptor.HmsBitmapDescriptor(com.huawei.hms.maps.model.BitmapDescriptorFactory.defaultMarker())
    }

    override fun fromAsset(assetName: String): BitmapDescriptor {
        return BitmapDescriptor.HmsBitmapDescriptor(
            com.huawei.hms.maps.model.BitmapDescriptorFactory.fromAsset(
                assetName
            )
        )
    }

    override fun fromBitmap(bitmap: Bitmap): BitmapDescriptor {
        return BitmapDescriptor.HmsBitmapDescriptor(
            com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(
                bitmap
            )
        )
    }

    override fun fromFile(fileName: String): BitmapDescriptor {
        return BitmapDescriptor.HmsBitmapDescriptor(
            com.huawei.hms.maps.model.BitmapDescriptorFactory.fromFile(
                fileName
            )
        )
    }

    override fun fromPath(absolutePath: String): BitmapDescriptor {
        return BitmapDescriptor.HmsBitmapDescriptor(
            com.huawei.hms.maps.model.BitmapDescriptorFactory.fromPath(
                absolutePath
            )
        )
    }

    override fun fromResource(resourceId: Int): BitmapDescriptor {
        // Workaround for NullPointerException, see https://developer.huawei.com/consumer/en/doc/development/HMSCore-Guides-V5/faq-0000001050166999-V5#EN-US_TOPIC_0000001050166999__section6660927203220
        com.huawei.hms.maps.MapsInitializer.initialize(ChoiceSdk.getContext())

        return BitmapDescriptor.HmsBitmapDescriptor(
            com.huawei.hms.maps.model.BitmapDescriptorFactory.fromResource(
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
            BitmapDescriptor.HmsBitmapDescriptor(
                com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(
                    bitmap
                )
            )
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: HmsBitmapDescriptorFactory? = null

        /**
         * Returns a new instance
         * Thread safe.
         * throws [UnsupportedOperationException] if no underlying api has been found
         */
        @Throws(UnsupportedOperationException::class)
        fun getInstance(): HmsBitmapDescriptorFactory {
            return INSTANCE
                ?: synchronized(this) {
                    HmsBitmapDescriptorFactory().also {
                        INSTANCE = it
                    }
                }
        }
    }
}
