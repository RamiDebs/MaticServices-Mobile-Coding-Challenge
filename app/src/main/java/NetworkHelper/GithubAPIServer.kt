package NetworkHelper

import Pojo.RepoItems
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubAPIServer {
    @GET("/search/repositories?&sort=stars&order=desc")
    fun getRepos(@Query("q") date : String ,@Query("page") page: Int): Call<RepoItems>
}