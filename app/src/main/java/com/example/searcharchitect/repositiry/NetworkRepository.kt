package com.example.searcharchitect.repositiry

import com.example.searcharchitect.model.Contact
import com.example.searcharchitect.retrofit.Failure
import com.example.searcharchitect.retrofit.IGoogleSheetsRequest
import com.example.searcharchitect.utility.Either
import javax.inject.Inject

interface INetworkRepository {

    suspend fun getDataVersion(): Either<Failure, Int>
    suspend fun getContactList(): Either<Failure, List<Contact>>

}

class NetworkRepository @Inject constructor(
    private val googleSheets: IGoogleSheetsRequest
) : INetworkRepository {

    override suspend fun getDataVersion(): Either<Failure, Int> {
        return googleSheets.getDataVersion()
    }

    override suspend fun getContactList(): Either<Failure, List<Contact>> {
        return googleSheets.getContactList()
    }

}