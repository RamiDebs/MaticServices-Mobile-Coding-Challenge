package cora.debs.trendingrepo

import Adapters.RepoDataAdapter
import Pojo.SingleItem
import ViewModels.MainViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java!!)
       val adapterrr = RepoDataAdapter()

        mainRView.apply {
            layoutManager =  LinearLayoutManager(applicationContext)
            adapter = adapterrr

        }
        mainViewModel!!.allRepos.observe(this, Observer { items ->  adapterrr.setItems(items = items as ArrayList<SingleItem>)
        })
    }
}
