package at.bluesource.choicesdk.maps.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import at.bluesource.choicesdk.core.MobileService
import at.bluesource.choicesdk.core.MobileServicesDetector
import at.bluesource.choicesdk.maps.R
import at.bluesource.choicesdk.maps.common.Map.Companion.toChoiceMap
import at.bluesource.choicesdk.maps.common.MapOptions.Companion.toGmsMapOptions
import at.bluesource.choicesdk.maps.common.MapOptions.Companion.toHmsMapOptions
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * A simple map fragment wrapper for hms and gms
 * Automatically returns the needed fragment depending on what mobile service is available.
 *
 * @see com.google.android.gms.maps.SupportMapFragment
 * @see com.huawei.hms.maps.SupportMapFragment
 */
open class MapFragment(
    private val options: MapOptions?
) : Fragment() {

    private val disposables = CompositeDisposable()

    private val mapRelay: BehaviorRelay<Map> = BehaviorRelay.create()

    fun getMapAsync(callback: OnMapReadyCallback) {
        disposables.add(mapRelay.firstElement()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { map ->
                callback.onMapReady(map)
            })
    }

    fun getMapObservable(): Observable<Map> {
        return mapRelay
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val mapFragment: Fragment = when (MobileServicesDetector.getAvailableMobileService()) {
            MobileService.GMS -> {
                val instance = if (options == null) {
                    com.google.android.gms.maps.SupportMapFragment.newInstance()
                } else {
                    com.google.android.gms.maps.SupportMapFragment.newInstance(options.toGmsMapOptions())
                }

                instance.getMapAsync {
                    mapRelay.accept(it.toChoiceMap())
                }

                instance
            }

            MobileService.HMS -> {
                val instance = if (options == null) {
                    com.huawei.hms.maps.SupportMapFragment.newInstance()
                } else {
                    com.huawei.hms.maps.SupportMapFragment.newInstance(options.toHmsMapOptions())
                }

                instance.getMapAsync {
                    mapRelay.accept(it.toChoiceMap())
                }

                instance
            }
        }

        replaceFragment(mapFragment)
    }

    /**
     * Replace everything inside a FrameLayout with instance
     */
    private fun replaceFragment(instance: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.mapFrameLayout, instance)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.choicesdk_fragment_map, container, false)
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    companion object {

        @JvmStatic
        fun newInstance(): MapFragment {
            return newInstance(null)
        }

        @JvmStatic
        fun newInstance(options: MapOptions? = null): MapFragment {
            return MapFragment(options)
        }
    }
}