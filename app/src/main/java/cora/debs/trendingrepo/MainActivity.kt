package cora.debs.trendingrepo

import Adapters.RepoDataAdapter
import Pojo.SingleItem
import ViewModels.MainViewModel
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var mainViewModel: MainViewModel? = null
    private val adapterrr = RepoDataAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //creating view holder and RView
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java!!)
        mainRView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = adapterrr

        }
        mainViewModel!!.allRepos(1).observe(this, Observer { items ->
            adapterrr.setItems(items = items as ArrayList<SingleItem>)
            mainRView.scheduleLayoutAnimation()
        })
    }
}
