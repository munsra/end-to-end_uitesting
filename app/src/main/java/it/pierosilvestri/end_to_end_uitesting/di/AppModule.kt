package it.pierosilvestri.end_to_end_uitesting.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import it.pierosilvestri.end_to_end_uitesting.use_case.ValidateLogin

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {
    // We use ViewModelScoped because it's used only in the viewModel
    @Provides
    @ViewModelScoped
    fun provideValidateLogin(): ValidateLogin {
        return ValidateLogin()
    }
}