package com.vkartik.genie.data.mapper

import com.vkartik.genie.data.AccountEntity
import com.vkartik.genie.domain.model.Account
import com.vkartik.genie.domain.util.EntityMapper
import javax.inject.Inject

class AccountMapper @Inject constructor() : EntityMapper<AccountEntity, Account> {

    fun entityListToAccountList(entityList: List<AccountEntity>): List<Account> {
        val list: ArrayList<Account> = ArrayList()
        for (entity in entityList) {
            list.add(mapFromEntity(entity))
        }
        return list
    }

    override fun mapFromEntity(entity: AccountEntity): Account {
        return Account(
            name = entity.name,
            userName = entity.userName,
            password = entity.password,
            iconUrl = entity.iconUrl,
            category = entity.category,
            notes = entity.notes
        )
    }

    override fun mapToEntity(domainModel: Account): AccountEntity {
        return AccountEntity(
            name = domainModel.name,
            userName = domainModel.userName,
            password = domainModel.password,
            iconUrl = domainModel.iconUrl,
            notes = domainModel.notes,
            category = domainModel.category
        )
    }

}