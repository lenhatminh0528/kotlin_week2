package kotlinclass.leminh.kotlin_week2.data

import android.util.Log
import kotlinclass.leminh.kotlin_week2.Account

class DataStore private constructor(){
    private var userList = ArrayList<Account>()
    private lateinit var signUpCallBack: SignUpCallback
    private lateinit var signInCallback: SignInCallback

    enum class TYPE(val value: Int){
        USERNAME(1)
        , EMAIL(2)
        , PHONENUMBER(3)
    }

    companion object{
        var instance = DataStore()
    }

    interface SignUpCallback{
        fun onSuccess()
        fun onFailed(message: String)
    }
    interface SignInCallback{
        fun onSuccess()
        fun onFailed(message: String)
    }

    fun setSignUpCallback(it: SignUpCallback){
        signUpCallBack = it
    }

    fun setSignInCallback(it: SignInCallback){
        signInCallback = it
    }

    fun signUp(name: String, email: String, password: String){
        Log.d("TAG", "name: $name, email: $email, password: ")
        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            signUpCallBack.onFailed("empty field!")
        }else{
            for(user in userList){
                if(user.email.equals(email)){
                    signUpCallBack.onFailed("User has been registered!")
                    return
                }
            }
            val user = Account(name,email,password,"")
            userList.add(user)
            signUpCallBack.onSuccess()
        }
    }
    fun signIn(email:String, password:String){
        if(email.isEmpty() or password.isEmpty()){
            signInCallback.onFailed("Empty field!")
        }else{
            for(user in userList){
                if(user.email.equals(email) and user.password.equals(password)){
                    signInCallback.onSuccess()
                }else{
                    signInCallback.onFailed("Email or password are incorrect!")
                }
            }
        }
    }

    fun edit(email:String,type: Int,data: String){
        for(user in userList){
            if(user.email.equals(email)){
                when(type){
                    TYPE.EMAIL.value -> user.email = data
                    TYPE.PHONENUMBER.value -> user.phonenumber = data
                    TYPE.USERNAME.value -> user.fullname = data
                }
            }
        }
    }

    fun getUserByEmail(email:String): Account?{
        for(user in userList){
            if(user.email.equals(email)){
                return user
            }
        }
        return null
    }

}