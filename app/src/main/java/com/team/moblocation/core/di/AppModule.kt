package com.team.moblocation.core.di

import com.team.moblocation.data.auth.AuthRepository
import com.team.moblocation.data.auth.AuthRepositoryImpl
import com.team.moblocation.data.repo.IUserRepo
import com.team.moblocation.data.repo.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//class AppModule {
//}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideAuthRepository(): AuthRepository =
        AuthRepositoryImpl()

    @Provides
    @Singleton
    fun provideUserRepo(): IUserRepo {
        return UserRepo()
    }
}