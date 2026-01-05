package com.team.moblocation.test

import FakeAuthRepository
import androidx.lifecycle.ViewModel
import com.team.moblocation.data.repo.IUserRepo
import com.team.moblocation.data.repo.UserRepo
import com.team.moblocation.ui.screens.auth.AuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.Dispatcher
import org.junit.Test
import org.junit.After
import org.junit.Before
import kotlin.test.assertEquals
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class LoginViewModelTest {
//    val repo = FakeAuthRepository()
    val repo: IUserRepo = mock()
   val viewModel = AuthViewModel(repo)

    @Before
    fun setup() {
        val testDispatcher = UnconfinedTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        whenever(repo.getUser()).thenReturn("keith")
    }

    @After
    fun cleanup(){
        Dispatchers.resetMain()
    }

    @Test
    fun `fetch user returns the mock value` () {
        assertEquals("keith", viewModel.fetchUser())
    }

//    @Test
//    fun `greetings should return hello mocked_value` () {
//        assertEquals("Hello keith", viewModel.greetings)
//    }

    @Test
    fun `fetch use should update greetings stateflow with greetings` () = runTest{
        viewModel.fetchUser()
        val greetings = viewModel.greetings.drop(1).first()
        assertEquals("Hello keith", greetings)
    }

    @Test
    fun `greet function should update the greetings stateflow with Hello $name`() = runTest{
        viewModel.greet("Keith")
        val msg = viewModel.greetings.drop(1).first()
        assertEquals("Hello Keith", msg, "Extra info about test")
    }

    @Test
    fun `Validation should fail for email and password`() {
        assert(viewModel.validate("email", "password") != null)
    }

    @Test
    fun `Validation should fail for email@a,com and pass`() {
        assert(viewModel.validate("email@a.com", "pass") != null)
    }

    @Test
    fun `Validation should pass for email@a,com and password`() {
        assert(viewModel.validate("email@a.com", "password") == null)
    }

    @Test
    fun `Validation should pass for email@gmail,com and password`() {
        assert(viewModel.validate("email@gmail.com", "password") == null)
    }
}