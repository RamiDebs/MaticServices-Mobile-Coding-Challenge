package ViewModels

import NetworkHelper.ReposRepository
import Pojo.SingleItem
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val reposRepository: ReposRepository = ReposRepository()

    val allRepos: LiveData<List<SingleItem>>
        get() = reposRepository.getMutableLiveData()

}