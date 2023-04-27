package com.example.taskhumanassignment.domain.manager

import android.util.Log
import com.example.taskhumanassignment.domain.models.*
import com.example.taskhumanassignment.domain.models.networkresponse.AddResultFailure
import com.example.taskhumanassignment.domain.models.networkresponse.AddResultSuccess
import com.example.taskhumanassignment.domain.models.networkresponse.RemoveSkillFailure
import com.example.taskhumanassignment.domain.models.networkresponse.RemoveSkillSuccess
import com.example.taskhumanassignment.domain.models.networkresponse.Result
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkRequestAgent {

    private val TAG = NetworkRequestAgent::class.simpleName

    private const val HTTP_REQUEST_TIMEOUT = 32L
    private const val HTTP_REQUEST_BASE_URL = "https://api-devenv.taskhuman.com/"

    /**
     * Common function for creating HTTPS Request handling Retrofit Client
     *
     * @param gatewayUrl DaaS Server URL
     * @return Retrofit Client.
     */
    private fun getRequestHandlingClient(gatewayUrl: String,
                                         httpRequestTimeout: Long = HTTP_REQUEST_TIMEOUT,
                                         logBody: Boolean = true): Retrofit {
        val client: OkHttpClient = prepareOkHttpClient(httpRequestTimeout, logBody)
        return Retrofit.Builder()
            .baseUrl(gatewayUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    /**
     * OkHttpClient instance configuration function
     *
     * @return Configured OkHttpClient instance.
     */
    private fun prepareOkHttpClient(httpRequestTimeout: Long, logBody: Boolean): OkHttpClient {

        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
        builder.readTimeout(httpRequestTimeout, TimeUnit.SECONDS)
        builder.connectTimeout(httpRequestTimeout, TimeUnit.SECONDS)
        builder.writeTimeout(httpRequestTimeout, TimeUnit.SECONDS)
        builder.retryOnConnectionFailure(true)
        return builder.build()
    }

    /**
     * Used by the [NetworkManager] class to initiate the request to get the list of skills from the network
     * @param resultObserver Observer for asynchronous result delivery
     */
    suspend fun getSkills(resultObserver: NetworkResult) {
        val retrofitClient = getRequestHandlingClient(HTTP_REQUEST_BASE_URL)
        val networkAPI: NetworkRequestAPI = retrofitClient.create(NetworkRequestAPI::class.java)

        val dataCall: Call<ResponseBody> = networkAPI.getSkills(
            RequestHeaderValues.VALUE_ACCEPT,
            RequestHeaderValues.VALUE_AUTHORIZATION
        )

        try {
            dataCall.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    Log.d(TAG, "getSkills(), onResponse == $response")
                    val responseCode = response.code()
                    var getSkillsResponse: Result? = null
                    if (responseCode == ResponseCode.SUCCESS) {
                        try {
                            val converter: Converter<ResponseBody, Result> =
                                retrofitClient.responseBodyConverter(
                                    Result::class.java,
                                    arrayOfNulls<Annotation>(0)
                                )
                            getSkillsResponse = response.body()?.let { converter.convert(it) }
                        }
                        catch (exception: Exception) {
                            //In case of converting exception, we still want to return DaaS response code
                            Log.d(TAG, "getSkills() onResponse() exception == ${exception.message}")
                        }
                    }

                    resultObserver.onGetSkillsResult(
                        getSkillsResponse?.success == true,
                        getSkillsResponse
                    )
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Log.e(TAG, "getSkills() onFailure() exception == ${t?.cause.toString()} ")
                    resultObserver.onGetSkillsResult(false, null)
                }
            })
        } catch (ex: Exception) {
            Log.e(TAG, "getSkills() exception == ${ex.localizedMessage}")
            resultObserver.onGetSkillsResult(false, null)
        }

    }

    suspend fun addSkill(body: String,
                         resultObserver: NetworkResult) {
        val retrofitClient = getRequestHandlingClient(HTTP_REQUEST_BASE_URL)
        val networkAPI: NetworkRequestAPI = retrofitClient.create(NetworkRequestAPI::class.java)

        val requestBody: RequestBody =
            RequestBody.create(
                MediaType.get("application/json"),
                body
            )

        val dataCall: Call<ResponseBody> = networkAPI.addSkill(
            RequestHeaderValues.VALUE_ACCEPT,
            RequestHeaderValues.VALUE_AUTHORIZATION,
            RequestHeaderValues.VALUE_CONTENT_TYPE,
            requestBody
        )

        try {
            dataCall.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    Log.d(TAG, "addSkill(), onResponse == $response")
                    val responseCode = response.code()
                    var addSkillResponse: AddResultSuccess? = null
                    var addResultFailure: AddResultFailure? = null
                    if (responseCode == ResponseCode.SUCCESS) {
                        try {
                            val converter: Converter<ResponseBody, AddResultSuccess> =
                                retrofitClient.responseBodyConverter(
                                    AddResultSuccess::class.java,
                                    arrayOfNulls<Annotation>(0)
                                )
                            addSkillResponse = response.body()?.let { converter.convert(it) }
                        }
                        catch (exception: Exception) {
                            //In case of converting exception, we still want to return DaaS response code
                            Log.d(TAG, "addSkill() onResponse() exception == ${exception.message}")
                        }
                        resultObserver.onAddSkillResult(
                            addSkillResponse?.success == true,
                            addSkillResponse,
                            null,
                            null
                        )
                    } else {

                        try {
                            val converter: Converter<ResponseBody, AddResultFailure> =
                                retrofitClient.responseBodyConverter(
                                    AddResultFailure::class.java,
                                    arrayOfNulls<Annotation>(0)
                                )
                            addResultFailure = response.body()?.let { converter.convert(it) }
                        }
                        catch (exception: Exception) {
                            //In case of converting exception, we still want to return DaaS response code
                            Log.d(TAG, "addSkill() onResponse() exception == ${exception.message}")
                        }
                        resultObserver.onAddSkillResult(
                            false,
                            null,
                            responseCode,
                            addResultFailure?.errors?.get(0)?.reason
                        )
                    }
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Log.e(TAG, "addSkill() onFailure() exception == ${t?.cause.toString()} ")
                    resultObserver.onAddSkillResult(
                        false,
                        null,
                        -1,
                        null
                    )
                }
            })
        } catch (ex: Exception) {
            Log.e(TAG, "addSkill() exception == ${ex.localizedMessage}")
            resultObserver.onAddSkillResult(
                false,
                null,
                -1,
                null
            )
        }
    }


    suspend fun removeSkill(body: String,
                         resultObserver: NetworkResult) {
        val retrofitClient = getRequestHandlingClient(HTTP_REQUEST_BASE_URL)
        val networkAPI: NetworkRequestAPI = retrofitClient.create(NetworkRequestAPI::class.java)

        val requestBody: RequestBody =
            RequestBody.create(
                MediaType.get("application/json"),
                body
            )

        val dataCall: Call<ResponseBody> = networkAPI.removeSkill(
            RequestHeaderValues.VALUE_ACCEPT,
            RequestHeaderValues.VALUE_AUTHORIZATION,
            RequestHeaderValues.VALUE_CONTENT_TYPE,
            requestBody
        )

        try {
            dataCall.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    Log.d(TAG, "removeSkill(), onResponse == $response")
                    val responseCode = response.code()
                    var removeSkillResponse: RemoveSkillSuccess? = null
                    var removeResultFailure: RemoveSkillFailure? = null
                    if (responseCode == ResponseCode.SUCCESS) {
                        try {
                            val converter: Converter<ResponseBody, RemoveSkillSuccess> =
                                retrofitClient.responseBodyConverter(
                                    RemoveSkillSuccess::class.java,
                                    arrayOfNulls<Annotation>(0)
                                )
                            removeSkillResponse = response.body()?.let { converter.convert(it) }
                        }
                        catch (exception: Exception) {
                            //In case of converting exception, we still want to return DaaS response code
                            Log.d(TAG, "removeSkill() onResponse() exception == ${exception.message}")
                        }
                        resultObserver.onRemoveSkillResult(
                            removeSkillResponse?.success == true,
                            removeSkillResponse,
                            null,
                            null
                        )
                    } else {

                        try {
                            val converter: Converter<ResponseBody, RemoveSkillFailure> =
                                retrofitClient.responseBodyConverter(
                                    RemoveSkillFailure::class.java,
                                    arrayOfNulls<Annotation>(0)
                                )
                            removeResultFailure = response.body()?.let { converter.convert(it) }
                        }
                        catch (exception: Exception) {
                            //In case of converting exception, we still want to return DaaS response code
                            Log.d(TAG, "removeSkill() onResponse() exception == ${exception.message}")
                        }
                        resultObserver.onRemoveSkillResult(
                            false,
                            null,
                            responseCode,
                            removeResultFailure?.errors?.get(0)?.reason
                        )
                    }
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Log.e(TAG, "removeSkill() onFailure() exception == ${t?.cause.toString()} ")
                    resultObserver.onAddSkillResult(
                        false,
                        null,
                        -1,
                        null
                    )
                }
            })
        } catch (ex: Exception) {
            Log.e(TAG, "removeSkill() exception == ${ex.localizedMessage}")
            resultObserver.onAddSkillResult(
                false,
                null,
                -1,
                null
            )
        }
    }

}