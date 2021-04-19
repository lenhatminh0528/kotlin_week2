package kotlinclass.leminh.kotlin_week2.data

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import kotlinclass.leminh.kotlin_week2.Account
import kotlinclass.leminh.kotlin_week2.Restaurant
import java.util.regex.Pattern

class DataStore private constructor(){
    private var userList = ArrayList<Account>()
    private var restaurantList = ArrayList<Restaurant>()
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

    fun getData(): List<Restaurant> {
        return listOf(
            Restaurant("Du Miên Garden Cafe - Phan Văn Trị","7 Phan Văn Trị, P.10","https://images.foody.vn/res/g4/33760/prof/s180x180/foody-mobile-du-mien-garden-mb-jp-309-635785114907746480.jpg"),
            Restaurant("Country House Cafe","18C Phan Văn Trị, P.10","https://images.foody.vn/res/g1/978/prof/s640x400/foody-mobile-countryhouse-jpg-246-635683259648435945.jpg"),
            Restaurant("Hẻm Spaghetti - Nguyễn Oanh","212/22 Nguyễn Oanh, P. 17","https://images.foody.vn/res/g9/82801/prof/s180x180/foody-upload-api-foody-mobile-gi4-jpg-181001160804.jpg"),
            Restaurant("Lava Coffee - Quang Trung","61 Quang Trung, P. 10","https://images.foody.vn/res/g15/149154/prof/s640x400/foody-upload-api-foody-mobile-4-190111100956.jpg"),
            Restaurant("Mì Cay Naga - 224 Phạm Văn Đồng","224 Phạm Văn Đồng","https://images.foody.vn/res/g20/194867/prof/s640x400/foody-mobile-9zbk996o-jpg-112-636143635496125338.jpg"),
            Restaurant("City House Cafe - Sân Vườn Lãng Mạn","21 Huỳnh Khương An, P. 5","https://images.foody.vn/res/g3/25656/prof/s640x400/foody-mobile-city-house-jpg-354-636133373358973749.jpg")
//            Restaurant("Country House Cafe","",""),
//            Restaurant("Country House Cafe","",""),
//            Restaurant("Country House Cafe","",""),
//            Restaurant("Country House Cafe","",""),
//            Restaurant("Country House Cafe","",""),
//            Restaurant("Country House Cafe","",""),
//            Restaurant("Country House Cafe","",""),
//            Restaurant("Country House Cafe","",""),
//            Restaurant("Country House Cafe","",""),
//            Restaurant("Country House Cafe","","")
        )
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
//    fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun isValidPasswordFormat(password: String): Boolean {
        val passwordREGEX = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[!@#$%^&*()])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$"
        );
        return passwordREGEX.matcher(password).matches()
    }
    fun signUp(name: String, email: String, password: String){
        Log.d("TAG", "name: $name, email: $email, password: ")
        if(name.isEmpty() || email.isEmpty() || password.isEmpty()){
            signUpCallBack.onFailed("empty field!")
        }else{
            if(!isValidEmail(email)){
                signUpCallBack.onFailed("Email's format are incorrect!")
                return
            }
            if(!isValidPasswordFormat(password)){
                signUpCallBack.onFailed("Password's format are incorrect!")
                return
            }
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
        if(userList.isEmpty()){
            signInCallback.onFailed("Empty data!")
        }else{
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