package com.dhananjaysingh.techmtask

import android.app.Application
import dagger.Component


class MyApplication : Application(){




    // Reference to the application graph that is used across the whole app
    val appComponent = DaggerApplicationComponent.create()


}

// Definition of the Application graph
@Component
interface ApplicationComponent { fun inject(activity: MainActivity) }