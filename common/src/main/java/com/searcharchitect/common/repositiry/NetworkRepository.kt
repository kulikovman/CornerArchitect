package com.searcharchitect.common.repositiry

import com.searcharchitect.common.model.Contact
import com.searcharchitect.common.model.VkProfileInfo
import com.searcharchitect.common.retrofit.Failure
import com.searcharchitect.common.retrofit.IGoogleSheetsRequest
import com.searcharchitect.common.retrofit.IVkRequest
import com.searcharchitect.common.utility.Either
import javax.inject.Inject

interface INetworkRepository {

    suspend fun getDataVersion(): Either<Failure, Int>
    suspend fun getContactList(): Either<Failure, List<Contact>>

    suspend fun getVkProfileInfoList(domains: List<String>): Either<Failure, List<VkProfileInfo>>

}

class NetworkRepository @Inject constructor(
    private val googleSheets: IGoogleSheetsRequest,
    private val vk: IVkRequest
) : INetworkRepository {

    override suspend fun getDataVersion(): Either<Failure, Int> {
        return googleSheets.getDataVersion()
    }

    override suspend fun getContactList(): Either<Failure, List<Contact>> {
        return googleSheets.getContactList()
    }


    override suspend fun getVkProfileInfoList(domains: List<String>): Either<Failure, List<VkProfileInfo>> {
        return vk.getPhotoPreviewList(domains)
    }

}