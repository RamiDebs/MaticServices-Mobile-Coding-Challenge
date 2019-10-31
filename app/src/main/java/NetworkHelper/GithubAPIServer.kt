package NetworkHelper

import Pojo.RepoItems
import retrofit2.Call
import retrofit2.http.GET

interface GithubAPIServer {
    @GET("/search/repositories?q=created:>2019-09-30&sort=stars&order=desc")
    fun getRepos(): Call<RepoItems>
}