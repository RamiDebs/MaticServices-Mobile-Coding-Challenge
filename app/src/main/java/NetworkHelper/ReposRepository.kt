package NetworkHelper

import CodeUtilities.CodeUtil
import Pojo.RepoItems
import Pojo.SingleItem
import android.util.Log
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ReposRepository {
    private val TAG = "ReposRepository"
    private var mRepoItems  = ArrayList<SingleItem>()
    internal val mutableLiveData = MutableLiveData<List<SingleItem>>()

    fun getMutableLiveData(page : Int): MutableLiveData<List<SingleItem>> {

        val githubAPIServer =
            RetrofitClient.retrofitInstance?.create<GithubAPIServer>(GithubAPIServer::class.java)

        githubAPIServer?.getRepos(CodeUtil.getDaysAgo(),page)?.enqueue(object : Callback<RepoItems> {
            override fun onResponse(call: Call<RepoItems>, response: Response<RepoItems>) {
                Log.d(TAG, "call done. with Response " + response.body())
                val allServerRepoItems = response.body()
                if (allServerRepoItems != null) {
                    mRepoItems  = allServerRepoItems.items as ArrayList<SingleItem>
                    mutableLiveData.value = mRepoItems 
                }
            }

            override fun onFailure(call: Call<RepoItems>?, t: Throwable?) {
                Log.e(TAG, "call failed. with message" + t?.message)
            }
        })
        return mutableLiveData
    }

}