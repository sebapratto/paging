package com.sebapp.challengeteco.ui.principal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.prattosebastian.auth.extra_utils_test.MainCoroutineRule
import com.sebapp.challengeteco.data.network.RetroInstance
import com.sebapp.challengeteco.data.services.RetroService
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 05,julio,2022
 *
 *
 * Created by
 * Sebastian Pratto (Misiones, Arg.)
 */

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var mainActivity: MainActivity
    private lateinit var services: RetroService

    companion object{
        private lateinit var retrofit: Retrofit

        @BeforeClass
        @JvmStatic
        fun setupCommon(){
            retrofit = Retrofit.Builder()
                .baseUrl(RetroInstance.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    @Before
    fun setup(){
        mainActivity = MainActivity()
        services = retrofit.create(RetroService::class.java)

    }

    @Test
    fun `service get first page and check return not null`(){
        runBlocking {
            val result = services.getDataFromAPI(1)
            MatcherAssert.assertThat("Service return null value", result,
                Matchers.`is`(Matchers.notNullValue())
            )
        }
    }

}