package at.bluesource.choicesdk.location.common


/**
 * Common interface for LocationRequest
 *
 * A data object that contains quality of service parameters for requests to the FusedLocationProviderApi.
 *
 * LocationRequest objects are used to request a quality of service for location updates from the FusedLocationProviderApi.
 *
 * For example, if your application wants high accuracy location it should create a location request with setPriority(int)
 * set to PRIORITY_HIGH_ACCURACY and setInterval(long) to 5 seconds. This would be appropriate for mapping applications that are showing your location in real-time.
 *
 * Activities should strongly consider removing all location request when entering the background
 * (for example at onPause()), or at least swap the request to a larger interval and lower quality.
 *
 * @see com.google.android.gms.location.LocationRequest
 * @see com.huawei.hms.location.LocationRequest
 */
interface LocationRequest {

    val expirationDuration: Long
    val expirationTime: Long
    val fastestInterval: Long
    val interval: Long
    val maxWaitTime: Long
    val numUpdates: Int
    val priority: Int
    val smallestDisplacement: Float
    val needAddress: Boolean
    val language: String?
    val countryCode: String?
    val extras: Map<String, String>?

    /**
     * Builder for LocationRequest
     *
     * numUpdates and expirationTime is set to max value so per default the location request does not expire;
     * interval is set to 5 seconds and the default fastest interval is set to 6x the interval;
     * setting the interval to 0 seconds is not recommended
     */
    @Suppress("unused")
    class Builder {

        private var priority = PRIORITY_HIGH_ACCURACY
        private var interval: Long = 5000
        private var fastestInterval: Long = interval / 6L
        private var expirationDuration: Long = Long.MAX_VALUE
        private var expirationTime: Long = 0
        private var numUpdates: Int = Integer.MAX_VALUE
        private var smallestDisplacement = 0f
        private var maxWaitTime: Long = 0
        private var needAddress = false
        private var language: String? = null
        private var countryCode: String? = null
        private var extras: MutableMap<String, String> = mutableMapOf()

        fun build(): LocationRequest {
            return object : LocationRequest {
                override val expirationDuration: Long
                    get() = this@Builder.expirationDuration
                override val expirationTime: Long
                    get() = this@Builder.expirationTime
                override val fastestInterval: Long
                    get() = this@Builder.fastestInterval
                override val interval: Long
                    get() = this@Builder.interval
                override val maxWaitTime: Long
                    get() = this@Builder.maxWaitTime
                override val numUpdates: Int
                    get() = this@Builder.numUpdates
                override val priority: Int
                    get() = this@Builder.priority
                override val smallestDisplacement: Float
                    get() = this@Builder.smallestDisplacement
                override val needAddress: Boolean
                    get() = this@Builder.needAddress
                override val language: String?
                    get() = this@Builder.language
                override val countryCode: String?
                    get() = this@Builder.countryCode
                override val extras: Map<String, String>?
                    get() = this@Builder.extras
            }
        }

        fun setExpirationDuration(millis: Long): Builder {
            expirationDuration = millis
            return this
        }

        fun setExpirationTime(millis: Long): Builder {
            expirationTime = millis
            return this
        }

        fun setFastestInterval(millis: Long): Builder {
            fastestInterval = millis
            return this
        }

        fun setInterval(millis: Long): Builder {
            interval = millis
            return this
        }

        fun setMaxWaitTime(millis: Long): Builder {
            maxWaitTime = millis
            return this
        }

        /**
         * Numbers smaller than 0 are not allowed, if <= 0 MAX_VALUE is used
         */
        fun setNumUpdates(numUpdates: Int): Builder {
            if (numUpdates <= 0) {
                this.numUpdates = Integer.MAX_VALUE
            } else {
                this.numUpdates = numUpdates
            }
            return this
        }

        fun setPriority(priority: Int): Builder {
            this.priority = priority
            return this
        }

        fun setSmallestDisplacement(smallestDisplacementMeters: Float): Builder {
            smallestDisplacement = smallestDisplacementMeters
            return this
        }

        /**
         * Not available for gms
         */
        fun putExtras(key: String, value: String): Builder {
            extras[key] = value
            return this
        }

        /**
         * Not available for gms
         */
        fun setNeedAddress(needAddress: Boolean): Builder {
            this.needAddress = needAddress
            return this
        }

        /**
         * Not available for gms
         */
        fun setLanguage(language: String): Builder {
            this.language = language
            return this
        }

        /**
         * Not available for gms
         */
        fun setCountryCode(countryCode: String): Builder {
            this.countryCode = countryCode
            return this
        }
    }

    @Suppress("MemberVisibilityCanBePrivate", "unused")
    companion object {

        // Values:
        // HMS: https://developer.huawei.com/consumer/en/doc/HMSCore-References-V5/constant-values-0000001050746179-V5#EN-US_TOPIC_0000001050746179__section54539193202
        // GMS: https://developers.google.com/android/reference/com/google/android/gms/location/LocationRequest
        const val PRIORITY_HIGH_ACCURACY = 100
        const val PRIORITY_BALANCED_POWER_ACCURACY = 102
        const val PRIORITY_LOW_POWER = 104
        const val PRIORITY_NO_POWER = 105
        const val PRIORITY_HD_ACCURACY = 200

        fun createDefault(): LocationRequest {
            return Builder().build()
        }

        internal fun LocationRequest.toGmsLocationRequest(): com.google.android.gms.location.LocationRequest {
            return com.google.android.gms.location.LocationRequest().also {
                it.setSmallestDisplacement(smallestDisplacement)
                    .setNumUpdates(numUpdates)
                    .setMaxWaitTime(maxWaitTime)
                    .setInterval(interval)
                    .setFastestInterval(fastestInterval)
                    .setExpirationTime(expirationTime)
                    .setExpirationDuration(expirationDuration)
                    .setPriority(priority)
            }
        }

        internal fun LocationRequest.toHmsLocationRequest(): com.huawei.hms.location.LocationRequest {
            return com.huawei.hms.location.LocationRequest().also {
                it.setSmallestDisplacement(smallestDisplacement)
                    .setNumUpdates(numUpdates)
                    .setMaxWaitTime(maxWaitTime)
                    .setInterval(interval)
                    .setFastestInterval(fastestInterval)
                    .setExpirationTime(expirationTime)
                    .setExpirationDuration(expirationDuration)
                    .setNeedAddress(needAddress)
                    .setLanguage(language)
                    .setCountryCode(countryCode)
                    .setPriority(priority)

                extras?.forEach { entry ->
                    it.putExtras(entry.key, entry.value)
                }
            }
        }
    }
}