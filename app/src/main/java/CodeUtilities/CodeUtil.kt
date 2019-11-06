package CodeUtilities

import android.content.Context
import android.net.ConnectivityManager
import java.util.*


object CodeUtil {

    fun isConnectedToNetwork(context : Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        return connectivityManager?.activeNetworkInfo?.isConnectedOrConnecting() ?: false
    }

    fun getDaysAgo(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -30)
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        //Github API server only accepts date in this format
        return "created:>$year-${if (month <= 9) "0$month" else month}-${if (day <= 9) "0$day" else day}"
    }

}