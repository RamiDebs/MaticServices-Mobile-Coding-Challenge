package ViewModels

import NetworkHelper.ReposRepository
import Pojo.SingleItem
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val reposRepository: ReposRepository = ReposRepository()

    fun allRepos(page : Int): LiveData<List<SingleItem>> {
        return reposRepository.getMutableLiveData(page)
    }
}