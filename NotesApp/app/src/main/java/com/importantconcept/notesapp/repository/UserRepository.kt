package com.importantconcept.notesapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.importantconcept.notesapp.api.UserAPI
import com.importantconcept.notesapp.models.signup.UserRequest
import com.importantconcept.notesapp.models.signup.UserResponse
import com.importantconcept.notesapp.utils.Constants.TAG
import com.importantconcept.notesapp.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

//Repository: access data from remote data source (Retrofit - UserAPI) or local data source
class UserRepository @Inject constructor(private val userAPI: UserAPI) {

    private val _userResponseLiveData = MutableLiveData<NetworkResult<UserResponse>>()
    val userResponseLiveData: LiveData<NetworkResult<UserResponse>> //publically accessible
        get() = _userResponseLiveData

    suspend fun registerUser(userRequest: UserRequest) {

        _userResponseLiveData.postValue(NetworkResult.Loading())

        val response = userAPI.signup(userRequest)
        Log.d(TAG, response.body().toString())

        handleResponse(response)

    }

    suspend fun loginUser(userRequest: UserRequest) {
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = userAPI.signin(userRequest)
        Log.d(TAG, response.body().toString())
        handleResponse(response)
    }

    private fun handleResponse(response: Response<UserResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!)) //!!NotNull
        } else if (response.errorBody() != null) { //errorBody() - error ki json hai
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _userResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

}