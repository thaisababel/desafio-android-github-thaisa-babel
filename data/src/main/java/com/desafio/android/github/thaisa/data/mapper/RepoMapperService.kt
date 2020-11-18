package com.desafio.android.github.thaisa.data.mapper

import com.desafio.android.github.thaisa.data.services.response.repo.RepoResponse
import com.desafio.android.github.thaisa.domain.entities.Repo

open class RepoMapperService : BaseMapperRepository<RepoResponse, List<Repo>> {
    override fun transform(type: RepoResponse): MutableList<Repo> {
        val list = mutableListOf<Repo>()

        type.items?.forEach {
            list.add(
                Repo(
                    it.name,
                    it.description,
                    it.owner.login,
                    it.owner.avatar_url,
                    it.stargazers_count,
                    it.forks
                )
            )

        }
        return list
    }


}