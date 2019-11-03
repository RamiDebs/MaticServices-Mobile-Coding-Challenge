package cora.debs.trendingrepo

import Adapters.RepoDataAdapter
import Pojo.SingleItem
import ViewModels.MainViewModel
import android.app.ProgressDialog
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var mainViewModel: MainViewModel? = null
    private val adapterrr = RepoDataAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // using ShimmerFrameLayout by facebook and starting the animation
        val mShimmerViewContainer : ShimmerFrameLayout = findViewById(R.id.shimmer_view_container)
        mShimmerViewContainer.startShimmerAnimation()

        //creating view holder and RView
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java!!)
        mainRView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = adapterrr
        }

        mainViewModel!!.allRepos(1).observe(this, Observer { items ->
            adapterrr.setItems(items = items as ArrayList<SingleItem>)
            //stoping the animation and hiding the layout and let the RView show up with the data
            mShimmerViewContainer.stopShimmerAnimation()
            mShimmerViewContainer.visibility = View.GONE
            mainRView.visibility = View.VISIBLE
            mainRView.scheduleLayoutAnimation()
        })
    }
}
