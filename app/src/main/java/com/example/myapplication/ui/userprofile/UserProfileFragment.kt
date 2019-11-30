package com.example.myapplication.ui.userprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.ui.BaseFragment
import com.example.myapplication.ui.MainActivity
import com.example.myapplication.usecases.user.logout.SignoutUserUseCase
import kotlinx.android.synthetic.main.fragment_user.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.kodein.di.erased.instance

@ExperimentalCoroutinesApi
class UserProfileFragment : BaseFragment() {

    private val viewModel: UserProfileViewModel by viewModelInstance()
    private val signoutUseCase by instance<SignoutUserUseCase>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_user, container, false)!!


    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserInfo()

        viewModel.user.observe {
            Glide.with(context!!)
                .load(it.image)
                .into(user_image)
            user_nickname.text = it.nickname
        }

        button_logout.setOnClickListener {
            lifecycleScope.launch() {
                logOut()
            }
        }

    }

    private suspend fun logOut() {
        signoutUseCase.buildAction()
        requireActivity().startActivity(MainActivity(requireContext()))
    }

}