package com.udacity.nanodegree.politicalpreparedness

import android.app.Application
import com.udacity.nanodegree.politicalpreparedness.database.ElectionDatabase
import com.udacity.nanodegree.politicalpreparedness.election.ElectionsViewModel
import com.udacity.nanodegree.politicalpreparedness.network.CivicsDataSource
import com.udacity.nanodegree.politicalpreparedness.network.CivicsRepository
import com.udacity.nanodegree.politicalpreparedness.representative.RepresentativeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        /**
         * use Koin Library as a service locator
         */
        val module = module {
            viewModel { ElectionsViewModel() }
            viewModel { RepresentativeViewModel(get(), get()) }
            single { CivicsRepository(get()) }
            single { ElectionDatabase.getInstance(this@MyApp).electionDao }
        }

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(module))
        }
    }
}