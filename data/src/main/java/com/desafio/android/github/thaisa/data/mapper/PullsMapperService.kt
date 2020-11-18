package com.desafio.android.github.thaisa.data.mapper

import com.desafio.android.github.thaisa.data.services.response.pulls.PullsResponse
import com.desafio.android.github.thaisa.domain.entities.Pulls
class PullsMapperService : BaseMapperRepository<List<PullsResponse>, List<Pulls>> {
    override fun transform(type: List<PullsResponse>): List<Pulls> {

        val list = mutableListOf<Pulls>()
        type?.forEach {
            list.add(
                Pulls(
                    it.user.login,
                    it.title,
                    it.created_at,
                    it.body
                )
            )

        }
        return list
    }

}