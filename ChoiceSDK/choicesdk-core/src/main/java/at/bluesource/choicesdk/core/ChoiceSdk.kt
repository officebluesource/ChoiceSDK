package at.bluesource.choicesdk.core

import android.app.Application


/**
 * Init class for context and to retrieve desired functionality.
 *
 */
class ChoiceSdk {

    companion object {
        private lateinit var appContext: Application
        internal var mobileServicePriorities: List<MobileService> = listOf(MobileService.GMS, MobileService.HMS)

        /**
         * Initialize the ChoiceSdk.
         *
         * @param context - Application context
         */
        @JvmStatic
        fun init(context: Application) {
            this.appContext = context
        }

        /**
         * Initialize the ChoiceSdk with custom mobile service priorities.
         *
         * @param context - Application context
         * @param mobileServicePriorities - Mobile services (GMS, HMS) are prioritized in the order of this list.
         */
        @JvmStatic
        fun init(context: Application, mobileServicePriorities: List<MobileService>) {
            this.appContext = context
            this.mobileServicePriorities = mobileServicePriorities
        }

        /**
         * Returns the application context
         *
         * @return [Application]
         */
        fun getContext(): Application {
            return appContext
        }


    }
}