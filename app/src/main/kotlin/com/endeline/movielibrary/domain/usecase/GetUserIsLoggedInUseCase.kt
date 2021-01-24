package com.endeline.movielibrary.domain.usecase

import com.endeline.movielibrary.data.service.UserService
import com.endeline.movielibrary.domain.usecase.types.SingleUseCase
import javax.inject.Inject

class GetUserIsLoggedInUseCase @Inject constructor(private val userService: UserService) :
    SingleUseCase<Boolean> {

    override fun invoke() = userService.getCurrentUser()
}