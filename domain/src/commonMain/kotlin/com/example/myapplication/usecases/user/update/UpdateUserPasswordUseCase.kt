package com.example.myapplication.usecases.user.update

import com.example.myapplication.repositories.ArenaTournamentRepository
import com.example.myapplication.usecases.UseCaseWithParamsSuspending

class UpdateUserPasswordUseCase(
    private val repository: ArenaTournamentRepository
) : UseCaseWithParamsSuspending<UpdateUserPasswordUseCase.Params, Boolean> {

    override suspend fun buildAction(params: Params) =
        repository.updateCurrentUserPassword(params.password)

    suspend fun buildAction(password: String) =
        buildAction(Params(password))

    data class Params(val password: String)

}

