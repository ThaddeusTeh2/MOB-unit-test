import com.team.moblocation.data.auth.AuthRepository
import com.team.moblocation.domain.auth.AuthResult
import com.team.moblocation.domain.auth.LoginRequest
import com.team.moblocation.domain.auth.RegisterRequest

class FakeAuthRepository() : AuthRepository {

    override suspend fun login(request: LoginRequest): AuthResult {

        if(request.email == "computerainbows@gmail.com" && request.password == "12345678") {
            return AuthResult.Success
        }
        return AuthResult.Error("wrong password or email")
    }

    override suspend fun register(request: RegisterRequest): AuthResult {
        if(request.email == "computerainbows@gmail.com" && request.password == "12345678") {
            return AuthResult.Success
        }
        return AuthResult.Error("2 wrong password or email")
    }
}