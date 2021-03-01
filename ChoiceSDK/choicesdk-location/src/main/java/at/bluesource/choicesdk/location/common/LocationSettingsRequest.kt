package at.bluesource.choicesdk.location.common

import at.bluesource.choicesdk.location.common.LocationRequest.Companion.toGmsLocationRequest
import at.bluesource.choicesdk.location.common.LocationRequest.Companion.toHmsLocationRequest


/**
 * Common interface for LocationSettingsRequest
 *
 * Specifies the types of location services the client is interested in using.
 * Use [LocationSettingsRequest.Builder] to construct this object.
 *
 * @see com.google.android.gms.location.LocationSettingsRequest
 * @see com.huawei.hms.location.LocationSettingsRequest
 */
interface LocationSettingsRequest {

    val needBle: Boolean
    val alwaysShow: Boolean
    val locationRequests: MutableList<LocationRequest>

    /**
     * A builder that builds [LocationSettingsRequest].
     *
     * @see com.google.android.gms.location.LocationSettingsRequest.Builder
     * @see com.huawei.hms.location.LocationSettingsRequest.Builder
     */
    @Suppress("unused")
    class Builder {
        private var needBle: Boolean = false
        private var alwaysShow: Boolean = false
        private var locationRequests: MutableList<LocationRequest> = mutableListOf()

        fun addAllLocationRequests(requests: Collection<LocationRequest>): Builder {
            locationRequests.addAll(requests)
            return this
        }

        fun addLocationRequest(request: LocationRequest): Builder {
            locationRequests.add(request)
            return this
        }

        fun build(): LocationSettingsRequest {
            return object :
                LocationSettingsRequest {
                override val needBle: Boolean
                    get() = this@Builder.needBle
                override val alwaysShow: Boolean
                    get() = this@Builder.alwaysShow
                override val locationRequests: MutableList<LocationRequest>
                    get() = this@Builder.locationRequests
            }
        }

        fun setAlwaysShow(alwaysShow: Boolean): Builder {
            this.alwaysShow = alwaysShow
            return this
        }

        fun setNeedBle(needBle: Boolean): Builder {
            this.needBle = needBle
            return this
        }
    }

    companion object {

        internal fun LocationSettingsRequest.toGmsLocationSettingsRequest(): com.google.android.gms.location.LocationSettingsRequest {
            val builder = com.google.android.gms.location.LocationSettingsRequest.Builder()

            builder.setNeedBle(needBle)
            builder.setAlwaysShow(alwaysShow)
            builder.addAllLocationRequests(locationRequests.map { it.toGmsLocationRequest() })
            return builder.build()
        }

        internal fun LocationSettingsRequest.toHmsLocationSettingsRequest(): com.huawei.hms.location.LocationSettingsRequest {
            val builder = com.huawei.hms.location.LocationSettingsRequest.Builder()

            builder.setNeedBle(needBle)
            builder.setAlwaysShow(alwaysShow)
            builder.addAllLocationRequests(locationRequests.map { it.toHmsLocationRequest() })
            return builder.build()
        }
    }
}