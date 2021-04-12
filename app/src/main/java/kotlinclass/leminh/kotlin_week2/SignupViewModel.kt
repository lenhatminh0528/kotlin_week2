package kotlinclass.leminh.kotlin_week2
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinclass.leminh.kotlin_week2.data.DataStore

class SignupViewModel : ViewModel() {
    var user = MutableLiveData<Account>()
    var isSuccess = MutableLiveData<Boolean>()
    var isError = MutableLiveData<String>()

    init{
        user.value = Account()
    }

    fun clear(){
        isError.value = null
        isSuccess.value = null
    }

    fun signUp(){
        Log.d("TAG1", "name: ${user.value?.email}, email: ${user.value?.fullname}, password: ${user.value?.password}")
        var dataStore = DataStore.instance
        dataStore.setSignUpCallback(object : DataStore.SignUpCallback{
            override fun onSuccess() {
                isSuccess.value = true
            }
            override fun onFailed(message: String) {
                isError.value = message
            }
        })
        dataStore.signUp(user.value!!.fullname,user.value!!.email,user.value!!.password)
    }
}