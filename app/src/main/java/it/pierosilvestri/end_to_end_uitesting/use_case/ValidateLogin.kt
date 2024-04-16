package it.pierosilvestri.end_to_end_uitesting.use_case

import it.pierosilvestri.end_to_end_uitesting.util.UIText

class ValidateLogin {

    operator fun invoke(
        email: String,
        password: String,
    ): Result {
        if(email.isEmpty() && password.isEmpty()){
            return Result.Error(
                message = UIText.DynamicString("Elements cannot be empty")
            )
        }
        if(email.isEmpty()){
            return Result.Error(
                message = UIText.DynamicString("Email cannot be empty")
            )
        }
        if(password.isEmpty()){
            return Result.Error(
                message = UIText.DynamicString("Password cannot be empty")
            )
        }

        return Result.Success()
    }

    sealed class Result {
        class Success : Result()

        data class Error(val message: UIText): Result()
    }

}