package cora.debs.trendingrepo

import Adapters.RepoDataAdapter
import CodeUtilities.CodeUtil
import Pojo.SingleItem
import ViewModels.MainViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shashank.sony.fancytoastlib.FancyToast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import EasterEgg.ShakeDetector
import android.hardware.Sensor
import android.hardware.SensorManager
import EasterEgg.ShakeDetector.OnShakeListener
import android.content.Context
import android.content.Intent

class MainActivity : AppCompatActivity() {
    private var mMainViewModel: MainViewModel? = null
    private val mRecycleViewAdapter = RepoDataAdapter()
    private var mLifeCycleOwner = this
    private var mPageNumber = 1
    private var mSensorManager: SensorManager? = null
    private var mAccelerometer: Sensor? = null
    private var mShakeDetector: ShakeDetector? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!
            .getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mShakeDetector = ShakeDetector()
        mShakeDetector!!.setOnShakeListener(object : OnShakeListener {

            override fun onShake(count: Int) {
            startActivity(Intent(this@MainActivity, EasterEggActivity::class.java))
            }
        })
        // using ShimmerFrameLayout by facebook and starting the animation
        shimmerVewContainer.startShimmerAnimation()

        //creating view holder and RView
        mMainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mainRView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mRecycleViewAdapter 
        }
        //getting data and updating the user interface
        updateUI(true)
    }

    override fun onStart() {
        super.onStart()
        //adding scroll listener to update the list
        mainRView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!mainRView.canScrollVertically(1)) {
                    progressBar.visibility = View.VISIBLE
                    updateUI(false)
                }
            }
        })
    }
    
    public override fun onResume() {
        super.onResume()
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager?.registerListener(
            mShakeDetector,
            mAccelerometer,
            SensorManager.SENSOR_DELAY_UI
        )
    }

    public override fun onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager?.unregisterListener(mShakeDetector)
        super.onPause()
    }
    fun updateUI(isFirstInsert: Boolean) {

            if (CodeUtil.isConnectedToNetwork(this)) {
                if (isFirstInsert) {

                    mMainViewModel!!.allRepos(mPageNumber)
                        .observe(mLifeCycleOwner, Observer { responseItems ->
                            mRecycleViewAdapter.setItems(
                                isFirstInsert,
                                responseItems as ArrayList<SingleItem>
                            )
                            if (isFirstInsert) {
                                //stoping the animation and hiding the layout and let the RView show up with the data
                                shimmerVewContainer.stopShimmerAnimation()
                                shimmerVewContainer.visibility = View.GONE
                                mainRView.visibility = View.VISIBLE
                                mainRView.animate()
                                mainRView.animation = null
                            }
                        })
                } else {
                    mPageNumber++
                    progressBar.visibility = View.GONE
                    mMainViewModel!!.allRepos(mPageNumber)
                    FancyToast.makeText(
                        this,
                        "Data Loaded",
                        FancyToast.LENGTH_LONG,
                        FancyToast.SUCCESS,
                        true
                    ).show()
                }


            } else {

                FancyToast.makeText(
                    this,
                    "No Internet Connection!",
                    FancyToast.LENGTH_LONG,
                    FancyToast.ERROR,
                    true
                ).show()
                //re-run the function after 10 seconds to check if there is internet connection
                Handler().postDelayed({
                    updateUI(isFirstInsert)
                }, 10000)

        }
    }

}

