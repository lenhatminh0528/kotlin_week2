package kotlinclass.leminh.kotlin_week2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinclass.leminh.kotlin_week2.data.DataStore

class ProfileViewModel : ViewModel() {
    var user = MutableLiveData<Account>()
    var dataStore = DataStore.instance
    fun editData(email: String, type: Int, data: String){
        dataStore.edit(email,type,data)
        when(type){
            DataStore.TYPE.EMAIL.value -> user.value?.email = data
            DataStore.TYPE.PHONENUMBER.value -> user.value?.phonenumber = data
            DataStore.TYPE.USERNAME.value -> user.value?.fullname = data
        }
        user.postValue(user.value)
    }
    fun setupUserProfile(email:String){
        var user = dataStore.getUserByEmail(email)
        user?.let{ user->
            this.user.value = user
            this.user.postValue(this.user.value)
        }
    }

}