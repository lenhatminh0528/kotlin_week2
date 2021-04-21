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
            Restaurant("City House Cafe - Sân Vườn Lãng Mạn","21 Huỳnh Khương An, P. 5","https://images.foody.vn/res/g3/25656/prof/s640x400/foody-mobile-city-house-jpg-354-636133373358973749.jpg"),
            Restaurant("Country House Cafe","18C Phan Văn Trị, P. 10","https://images.foody.vn/res/g1/978/prof/s180x180/foody-mobile-countryhouse-jpg-246-635683259648435945.jpg"),
            Restaurant("Hẻm Spaghetti - Nguyễn Oanh","212/22 Nguyễn Oanh, P. 17","https://images.foody.vn/res/g9/82801/prof/s180x180/foody-upload-api-foody-mobile-gi4-jpg-181001160804.jpg"),
            Restaurant("Lava Coffee - Quang Trung","61 Quang Trung, P. 10","https://images.foody.vn/res/g15/149154/prof/s180x180/foody-upload-api-foody-mobile-4-190111100956.jpg"),
            Restaurant( "Mì Cay Naga - 224 Phạm Văn Đồng", "224 Phạm Văn Đồng, P.1 ", "https://images.foody.vn/res/g20/194867/prof/s180x180/foody-mobile-9zbk996o-jpg-112-636143635496125338.jpg"),
            Restaurant("City House Cafe - Sân Vườn Lãng Mạn","21 Huỳnh Khương An, P. 5","https://images.foody.vn/res/g3/25656/prof/s180x180/foody-mobile-city-house-jpg-354-636133373358973749.jpg"),
            Restaurant("Nhi Nhi Quán - Đặc Sản Phan Rang","125/48 Lê Đức Thọ, P. 17", "https://images.foody.vn/res/g7/66844/prof/s180x180/foody-mobile-bpzy3ni3-jpg-841-636143721820688231.jpg"),
            Restaurant( "Yuri Yuri - Ẩm Thực Hàn Quốc", "358 Nguyễn Văn Nghi, P. 7","https://images.foody.vn/res/g4/33645/prof/s180x180/foody-mobile-bayef6n3-jpg-308-636023635570133301.jpg"),
            Restaurant("Trà Sữa Gong Cha - 貢茶 - Phan Văn Trị","595 Phan Văn Trị, P. 5","https://images.foody.vn/res/g26/252442/prof/s180x180/foody-mobile-12-jpg-323-636148043123028599.jpg"),
            Restaurant("Oasis Cafe","26/14 Phạm Văn Đồng, P. 3","https://images.foody.vn/res/g2/12513/prof/s180x180/foody-mobile-oasis-cafe-tp-hcm.jpg"),
            Restaurant("Buzza Pizza - Emart Gò Vấp","Tầng Trệt Emart Gò Vấp - 366 Phan Văn Trị, P. 5","https://images.foody.vn/res/g20/199622/prof/s180x180/201652411519-mobile-foody-logo-2-640x400.jpg"),
            Restaurant("Dallas Cakes & Coffee - Quang Trung","6 Quang Trung, P. 10","https://images.foody.vn/res/g66/651063/prof/s180x180/foody-mobile-11090821_80492442289-251-636277684533992519.jpg"),
            Restaurant("Hot & Cold - Trà Sữa & Xiên Que - Quang Trung","804 Quang Trung","https://images.foody.vn/res/g7/60915/prof/s180x180/foody-upload-api-foody-mobile-1-190605180036.jpg"),
            Restaurant("Papaxốt - Quang Trung","458 Quang Trung","https://images.foody.vn/res/g12/112585/prof/s180x180/foody-upload-api-foody-mobile-7-190109171633.jpg"),
            Restaurant("SaiGon Chic Cafe","82 Đường Số 27, P. 6","https://images.foody.vn/res/g10/91979/prof/s180x180/foody-mobile-kjuxujry-jpg-982-635838930416211667.jpg"),
            Restaurant("Pizza Hut - Quang Trung","283 Quang Trung","https://images.foody.vn/res/g3/28883/prof/s180x180/foody-mobile-p-jpg-800-635757703358128351.jpg"),
            Restaurant("Bánh Mì Chảo & Món Ngon Hoa Việt - Quán Cô 3 Hậu","36 Đường Số 18, P. 8","https://images.foody.vn/res/g13/129725/prof/s180x180/foody-mobile-foody-quan-co-3-hau--960-635652896252263911.jpg"),
            Restaurant("Kichi Kichi Lẩu Băng Chuyền - Quang Trung","1 Quang Trung","https://images.foody.vn/res/g5/46668/prof/s180x180/foody-mobile-rfokfbsk-jpg-859-635796372049634356.jpg"),
            Restaurant( "The Coffee House - Quang Trung","293 Quang Trung","https://images.foody.vn/res/g16/158187/prof/s180x180/foody-mobile-coffeehousequangtrun-822-635754530645782536.jpg"),
            Restaurant("Cánh Đồng Quán - Lẩu Nướng Tại Bàn - Dương Quảng Hàm","310/14 Dương Quảng Hàm, P. 5","https://images.foody.vn/res/g4/30102/prof/s180x180/foody-mobile-shwadjuj-jpg-413-636204369735412311.jpg")
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