package at.bluesource.choicesdk.core

import android.app.Application


/**
 * Init class for context and to retrieve desired functionality.
 *
 */
class ChoiceSdk {

    companion object {
        private lateinit var appContext: Application

        /**
         * Initialize the ChoiceSdk with the application context
         *
         * @param context - Application context
         */
        @JvmStatic
        fun init(context: Application) {
            appContext = context
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