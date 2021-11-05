package com.corner.searcharchitect.repositiry

import com.corner.searcharchitect.model.Contact
import com.corner.searcharchitect.model.VkProfileInfo
import com.corner.searcharchitect.retrofit.Failure
import com.corner.searcharchitect.retrofit.IFacebookRequest
import com.corner.searcharchitect.retrofit.IGoogleSheetsRequest
import com.corner.searcharchitect.retrofit.IVkRequest
import com.corner.searcharchitect.utility.Either
import javax.inject.Inject

interface INetworkRepository {

    suspend fun getDataVersion(): Either<Failure, Int>
    suspend fun getContactList(): Either<Failure, List<Contact>>

    suspend fun getFacebookAccessToken(): Either<Failure, String>
    suspend fun getFacebookProfileInfo(userId: String, token: String)

    suspend fun getVkProfileInfoList(domains: List<String>): Either<Failure, List<VkProfileInfo>>

}

class NetworkRepository @Inject constructor(
    private val googleSheets: IGoogleSheetsRequest,
    private val facebook: IFacebookRequest,
    private val vk: IVkRequest
) : INetworkRepository {

    override suspend fun getDataVersion(): Either<Failure, Int> {
        return googleSheets.getDataVersion()
    }

    override suspend fun getContactList(): Either<Failure, List<Contact>> {
        return googleSheets.getContactList()
    }


    override suspend fun getFacebookAccessToken(): Either<Failure, String> {
        return facebook.getAccessToken()
    }

    override suspend fun getFacebookProfileInfo(userId: String, token: String) {
        facebook.getProfileInfo(userId, token)
    }


    override suspend fun getVkProfileInfoList(domains: List<String>): Either<Failure, List<VkProfileInfo>> {
        return vk.getPhotoPreviewList(domains)
    }

}