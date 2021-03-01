package at.bluesource.choicesdk.maps.common

/**
 * Wrapper class for hms and gms BitmapDescriptor.
 * Has inner classes which hold their specific version.
 *
 * Defines a Bitmap image. For a marker, this class can be used to set the image of the marker icon.
 * For a ground overlay, it can be used to set the image to place on the surface of the earth.
 * To obtain a BitmapDescriptor use the factory class BitmapDescriptorFactory.
 *
 * @see com.google.android.gms.maps.model.BitmapDescriptor
 * @see com.huawei.hms.maps.model.BitmapDescriptor
 */
sealed class BitmapDescriptor {
    internal data class GmsBitmapDescriptor(val value: com.google.android.gms.maps.model.BitmapDescriptor) :
        BitmapDescriptor()

    internal data class HmsBitmapDescriptor(val value: com.huawei.hms.maps.model.BitmapDescriptor) :
        BitmapDescriptor()
}