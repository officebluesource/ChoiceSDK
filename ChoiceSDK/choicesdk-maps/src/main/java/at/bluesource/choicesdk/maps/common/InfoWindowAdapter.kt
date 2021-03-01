package at.bluesource.choicesdk.maps.common

import android.view.View
import at.bluesource.choicesdk.maps.common.Marker.Companion.toChoiceMarker
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.huawei.hms.maps.HuaweiMap

/**
 * Common interface for hms/gms InfoWindowAdapter
 *
 * Provides views for customized rendering of info windows.
 *
 * Methods on this provider are called when it is time to show an info window for a marker, regardless of the cause
 * (either a user gesture or a programmatic call to showInfoWindow().
 * Since there is only one info window shown at any one time, a provider may choose to reuse views, or may
 * choose to create new views on each method invocation.
 *
 * @see GoogleMap.InfoWindowAdapter
 * @see HuaweiMap.InfoWindowAdapter
 */
interface InfoWindowAdapter {
    fun getInfoContents(marker: at.bluesource.choicesdk.maps.common.Marker?): View?
    fun getInfoWindow(marker: at.bluesource.choicesdk.maps.common.Marker?): View

    companion object {
        internal fun InfoWindowAdapter.toGmsInfoWindowAdapter(): GoogleMap.InfoWindowAdapter {
            return object : GoogleMap.InfoWindowAdapter {
                override fun getInfoContents(marker: Marker?): View? {
                    return this@toGmsInfoWindowAdapter.getInfoContents(marker?.toChoiceMarker())
                }

                override fun getInfoWindow(marker: Marker?): View {
                    return this@toGmsInfoWindowAdapter.getInfoWindow(marker?.toChoiceMarker())
                }
            }
        }

        internal fun InfoWindowAdapter.toHmsInfoWindowAdapter(): HuaweiMap.InfoWindowAdapter {
            return object : HuaweiMap.InfoWindowAdapter {
                override fun getInfoContents(marker: com.huawei.hms.maps.model.Marker?): View? {
                    return this@toHmsInfoWindowAdapter.getInfoContents(marker?.toChoiceMarker())
                }

                override fun getInfoWindow(marker: com.huawei.hms.maps.model.Marker?): View {
                    return this@toHmsInfoWindowAdapter.getInfoWindow(marker?.toChoiceMarker())
                }
            }
        }
    }
}