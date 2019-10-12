package com.example.myapplication.usecases.tournament

import com.example.myapplication.entities.TournamentEntity
import com.example.myapplication.entities.UserEntity
import com.example.myapplication.repositories.ArenaTournamentRepository
import com.example.myapplication.usecases.UseCaseWithParams

class GetTournamentsByUser(
    private val repository: ArenaTournamentRepository
) : UseCaseWithParams<GetTournamentsByUser.Params, List<TournamentEntity>> {

    override suspend fun buildAction(params: Params): List<TournamentEntity> {
        val toReturn = mutableListOf<TournamentEntity>()
        var pageNumber = 0
        var pageContent = repository.getTournamentsByUser(params.user.id, pageNumber)
        toReturn.addAll(pageContent)
        pageNumber++
        while (pageContent.isNotEmpty() && pageNumber <= params.maxPage) {
            pageContent = repository.getTournamentsByUser(params.user.id, pageNumber)
            toReturn.addAll(pageContent)
            pageNumber++
        }
        return toReturn
    }

    suspend fun buildAction(page: Int = 1){
        val user = repository.getCurrentUser()
        buildAction(Params(user, page))
    }

    data class Params(val user: UserEntity, val maxPage: Int = 1)

}
