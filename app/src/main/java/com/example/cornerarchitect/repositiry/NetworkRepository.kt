package com.example.cornerarchitect.repositiry

import com.example.cornerarchitect.model.Contact
import com.example.cornerarchitect.retrofit.Failure
import com.example.cornerarchitect.retrofit.IGoogleSheetsRequest
import com.example.cornerarchitect.utility.Either
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