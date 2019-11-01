package NetworkHelper

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
    private var repoItems = ArrayList<SingleItem>()
    internal val mutableLiveData = MutableLiveData<List<SingleItem>>()

    fun getMutableLiveData(): MutableLiveData<List<SingleItem>> {

        val githubAPIServer =
            RetrofitClient.retrofitInstance?.create<GithubAPIServer>(GithubAPIServer::class.java)

        githubAPIServer?.getRepos()?.enqueue(object : Callback<RepoItems> {
            override fun onResponse(call: Call<RepoItems>, response: Response<RepoItems>) {
                Log.d(TAG, "call done. with Response " + response.body())
                val allServerRepoItems = response.body()
                if (allServerRepoItems != null) {
                    repoItems = allServerRepoItems.items as ArrayList<SingleItem>
                    mutableLiveData.value = repoItems
                }
            }

            override fun onFailure(call: Call<RepoItems>?, t: Throwable?) {
                Log.e(TAG, "call failed. with message" + t?.message)
            }
        })
        return mutableLiveData
    }

    fun getDaysAgo(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -30)
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        //Github API server only accepts date in this format
        return "$year-${if (month<=9) "0"+month else month}-${if (day<=9) "0"+day else day}"
    }
}