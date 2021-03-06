package com.example.myapplication.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class OnboardingActivity : BaseFragmentedActivity(R.id.nav_host_onboarding_fragment) {

    companion object : IntentBuilder<OnboardingActivity> {

        override fun buildIntent(context: Context) =
            Intent(context, OnboardingActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
    }

}