package cora.debs.trendingrepo

import Adapters.RepoDataAdapter
import Pojo.SingleItem
import ViewModels.MainViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var mMainViewModel: MainViewModel? = null
    private val mRecycleViewAdapter = RepoDataAdapter()
    private var mLifeCycleOwner = this
    private var mPageNumber = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
        //adding scroll listener to update the list
        mainRView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    progressBar.visibility = View.VISIBLE
                    updateUI(false)
                }
            }
        })
    }

    fun updateUI(isFirstInsert: Boolean) {
        if (!isFirstInsert) {
            mPageNumber++
        }
        mMainViewModel!!.allRepos(mPageNumber).observe(mLifeCycleOwner, Observer { responseItems ->
            mRecycleViewAdapter .setItems(isFirstInsert,  responseItems as ArrayList<SingleItem>)
            if (isFirstInsert) {
                //stoping the animation and hiding the layout and let the RView show up with the data
                shimmerVewContainer.stopShimmerAnimation()
                shimmerVewContainer.visibility = View.GONE
                mainRView.visibility = View.VISIBLE
                mainRView.animate()
                mainRView.animation = null
            } else {
                progressBar.visibility = View.GONE
            }
        })
    }
}

