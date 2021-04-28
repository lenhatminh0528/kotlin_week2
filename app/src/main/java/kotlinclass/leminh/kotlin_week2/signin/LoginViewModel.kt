package kotlinclass.leminh.kotlin_week2.signin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinclass.leminh.kotlin_week2.Account
import kotlinclass.leminh.kotlin_week2.data.DataStore

class LoginViewModel : ViewModel() {
    var user = MutableLiveData<Account>()
    var isSuccess = MutableLiveData<Boolean?>()
    var isError = MutableLiveData<String?>()

    init{
        user.value = Account()
    }
    fun clear(){
        isError.value = null
        isSuccess.value = null
    }
    fun signIn(){
        var dataStore = DataStore.instance
        dataStore.setSignInCallback(object : DataStore.SignInCallback{
            override fun onFailed(message: String) {
                isError.value = message
            }

            override fun onSuccess() {
                isSuccess.value = true
            }
        })
        dataStore.signIn(user.value!!.email,user.value!!.password)
    }
}