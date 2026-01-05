package com.team.moblocation.data.repo

class UserRepo: IUserRepo {
    override fun getUser(): String {
        //call actual api or database query to get info
        return "Hello User"
    }
}